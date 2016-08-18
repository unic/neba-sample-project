package to.adapt.neba.impl.contact;

import org.apache.felix.scr.annotations.*;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import to.adapt.neba.api.contact.Contact;

import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static org.apache.sling.event.jobs.consumer.JobConsumer.JobResult.ASYNC;
import static org.apache.sling.event.jobs.consumer.JobConsumer.PROPERTY_TOPICS;
import static to.adapt.neba.impl.contact.Constants.JOB_PROPERTY_CONTACT;
import static to.adapt.neba.impl.contact.Constants.TOPIC_CONTACT_REQUEST;

/**
 * Demonstrates how to handle a job for a specific topic. It also demonstrates
 * the best-practice of <em>asynchronous job execution</em>. By default, job execution is synchronous - if a job is created
 * by a synchronous event handler (and event handlers are synchronous by default), any cause - such as a JCR resource creation - will
 * block until the job is completed.
 *
 * @author Olaf Otto
 */
@Component(metatype = true, label = "NEBA sample project contact email sender")
@Service
@Properties({
        @Property(name = PROPERTY_TOPICS, value = TOPIC_CONTACT_REQUEST, propertyPrivate = true)
})
public class ContactEmailSender implements JobConsumer {
    private final ExecutorService executorService = newSingleThreadExecutor();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Property(
            label = "Sender",
            description = "The email address that shall be used to send contact requests",
            value = "sender@example.invalid")
    private static final String PROPERTY_FROM = "mail.from";

    @Property(
            label = "Recipient",
            description = "The email address contact requests shall be sent to.",
            value = "recipient@example.invalid")
    private static final String PROPERTY_TO = "mail.to";

    /**
     * The {@link JavaMailSender} is a spring service exposed as an OSGi service
     * in the blueprint context.xml.
     */
    @Reference
    private JavaMailSender mailSender;

    private String recipient;
    private String sender;

    @Activate
    protected void activate(Map<String, Object> properties) {
        this.recipient = (String) properties.get(PROPERTY_TO);
        this.sender = (String) properties.get(PROPERTY_FROM);
    }

    @Override
    public JobConsumer.JobResult process(Job job) {
        final JobConsumer.AsyncHandler handler = (JobConsumer.AsyncHandler) job.getProperty(PROPERTY_JOB_ASYNC_HANDLER);

        // Perform sending the E-Mail asynchronously
        executorService.submit(() -> {
            try {
                Contact contact = job.getProperty(JOB_PROPERTY_CONTACT, Contact.class);

                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);
                helper.setTo(this.recipient);
                helper.setFrom(this.sender);

                helper.setSubject("Contact request from " + contact.getName());
                helper.setReplyTo(contact.getEmail());
                helper.setText(contact.getMessage());

                mailSender.send(message);
            } catch (Exception e) {
                logger.error("Unable to send contact mail.", e);
                handler.failed();
            }

            handler.ok();
            return null;
        });

        return ASYNC;
    }

    @Deactivate
    protected void deactivate() {
        executorService.shutdownNow();
    }
}

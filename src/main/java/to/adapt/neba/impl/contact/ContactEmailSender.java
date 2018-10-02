package to.adapt.neba.impl.contact;

import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;
import static org.apache.sling.event.jobs.consumer.JobConsumer.JobResult.ASYNC;
import static org.slf4j.LoggerFactory.getLogger;
import static to.adapt.neba.impl.contact.Constants.JOB_PROPERTY_CONTACT;

/**
 * Demonstrates how to handle a job for a specific topic. It also demonstrates
 * the best-practice of <em>asynchronous job execution</em>. By default, job execution is synchronous - if a job is created
 * by a synchronous event handler (and event handlers are synchronous by default), any cause - such as a JCR resource creation - will
 * block until the job is completed.
 */
@Component(service = JobConsumer.class)
@Designate(ocd = ContactEmailSender.Configuration.class)
public class ContactEmailSender implements JobConsumer {
    private final ExecutorService executorService = newSingleThreadExecutor();
    private final Logger logger = getLogger(getClass());

    /**
     * The {@link JavaMailSender} is a spring service exposed as an OSGi service
     * in the blueprint context.xml.
     */
    @Reference
    private JavaMailSender mailSender;

    private Configuration config;

    @Activate
    protected void activate(Configuration configuration) {
        this.config = configuration;
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
                helper.setFrom(this.config.mailFrom());
                helper.setTo(this.config.mailTo());

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

    @ObjectClassDefinition(name = "NEBA sample project contact email sender")
    public @interface Configuration {
        @AttributeDefinition(
                name = "Sender",
                description = "The email address that shall be used to send contact requests",
                defaultValue = "sender@example.invalid")
        String mailFrom();

        @AttributeDefinition(
                name = "Recipient",
                description = "The email address contact requests shall be sent to.",
                defaultValue = "sender@example.invalid")
        String mailTo();
    }
}

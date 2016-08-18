package to.adapt.neba.impl.contact;

import org.apache.sling.event.jobs.JobManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import to.adapt.neba.api.contact.Contact;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static to.adapt.neba.impl.contact.Constants.JOB_PROPERTY_CONTACT;
import static to.adapt.neba.impl.contact.Constants.TOPIC_CONTACT_REQUEST;

/**
 * Demonstrates how Spring MVC, Spring data binding
 * and Spring's validation support and Sling cooperate when using NEBA.
 *
 * @author Olaf Otto
 */
@Controller
@RequestMapping("/contact")
public class ContactController {
    /**
     * The Job manager is a sling service imported
     * in the blueprint context.xml
     */
    @Resource
    private JobManager jobManager;

    /**
     * Upon a GET request, simply provide the form view. This
     * is the usual way of loading a Spring-backed form.
     */
    @RequestMapping(method = GET)
    public String load() {
        return formView();
    }

    /**
     * Upon a POST request, process the submitted form data. This
     * is the usual way of handling a Spring-backed form submission.
     * The {@link Valid} annotation causes Spring to apply its configured
     * validation implementation, in this case the {@link org.hibernate.validator.HibernateValidator},
     * as configured via the standard java service config in META-INF/services (see there).
     */
    @RequestMapping(method = POST)
    public String add(@Valid Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return formView();
        }

        Map<String, Object> payload = new HashMap<>();
        payload.put(JOB_PROPERTY_CONTACT, contact);

        /*
         * Create a job handling the contact request. Jobs have a
         * guarantee of processing and are better suited
         * to handle unreliable tasks, such as sending emails.
         */
        jobManager.addJob(TOPIC_CONTACT_REQUEST, payload);

        return successView();
    }

    /**
     * Regardless whether a GET or POST request is used, always use
     * {@link Contact} as the backing model for the form.
     */
    public @ModelAttribute Contact contact() {
        return new Contact();
    }

    private String formView() {
        return "neba-sample/components/contact/form";
    }

    private String successView() {
        return "neba-sample/components/contact/success";
    }
}

package to.adapt.neba.impl.contact;

import org.apache.sling.event.jobs.JobManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Demonstrates how Spring MVC databinding and Sling cooperate when using NEBA.
 *
 * @author Olaf Otto
 */
@Controller
@RequestMapping("/contact")
public class ContactController {
    @Resource
    private JobManager jobManager;

    @RequestMapping(method = GET)
    public String load() {
        return formView();
    }

    @RequestMapping(method = POST)
    public String add(@Valid Contact contact, BindingResult result) {
        if (result.hasErrors()) {
            return "neba-sample/components/contact/form";
        }

        jobManager.addJob("neba-sample/contact/request", contact.toMap());
        return "neba-sample/components/contact/success";
    }

    public @ModelAttribute Contact contact() {
        return new Contact();
    }

    private String formView() {
        return "neba-sample/components/contact/form";
    }
}

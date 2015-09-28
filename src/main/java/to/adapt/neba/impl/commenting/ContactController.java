package to.adapt.neba.impl.commenting;

import io.neba.api.annotations.ResourceParam;
import org.apache.sling.event.jobs.JobManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Demonstrates how Spring MVC and Sling cooperate when using NEBA.
 *
 * @author Olaf Otto
 */
@Controller
public class ContactController {
    @Resource
    private JobManager jobManager;

    @RequestMapping(value = "/contact", method = POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void add(Comment comment) {
        Map<String, Object> properties = comment.toMap();
        jobManager.addJob("adaptto/contact/request", properties);
    }

    @ModelAttribute
    public Comment prepareFormModel(@ResourceParam Comment comment) {
        return comment;
    }
}

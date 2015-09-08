package to.adapt.neba.impl;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.event.jobs.JobManager;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import java.util.HashMap;
import java.util.Map;

import static org.apache.sling.api.SlingConstants.PROPERTY_PATH;
import static org.apache.sling.api.SlingConstants.TOPIC_RESOURCE_ADDED;
import static org.osgi.service.event.EventConstants.EVENT_FILTER;
import static org.osgi.service.event.EventConstants.EVENT_TOPIC;

/**
 * Creates a new job when a resource is created below the contact inbox resource. Contrary to events, jobs have a guarantee of processing,
 * thus a job is more suitable to perform unreliable tasks, such as sending notification mail or invoking thirdparty APIs (e.g., twitter).
 *
 * @author Olaf Otto
 */
@Service
@Component(immediate = true)
@Properties({
        @Property(name = EVENT_TOPIC, value = TOPIC_RESOURCE_ADDED),
        @Property(name = EVENT_FILTER, value = "(&" +
                "(path=" + Constants.CONTACT_INBOX + "/*)" +
                "(resourceType=adaptto/components/contactRequest))")
})
public class ContactRequestJobGenerator implements EventHandler {
    @Reference
    private JobManager jobManager;
    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void handleEvent(Event event) {
        Map<String, Object> properties = new HashMap<>();
        properties.put(Constants.JOB_CONTACT_REQUEST_PATH, event.getProperty(PROPERTY_PATH));
        jobManager.addJob(Constants.JOB_TOPIC_CONTACT_REQUEST, properties);
    }
}

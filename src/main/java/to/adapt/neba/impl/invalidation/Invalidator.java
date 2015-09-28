package to.adapt.neba.impl.invalidation;

import org.apache.felix.scr.annotations.*;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

import static org.apache.sling.api.SlingConstants.PROPERTY_PATH;
import static org.apache.sling.api.SlingConstants.TOPIC_RESOURCE_CHANGED;
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
        @Property(name = EVENT_TOPIC, value = TOPIC_RESOURCE_CHANGED),
        @Property(name = EVENT_FILTER, value = "(path=/content/adaptto/*)")
})
public class Invalidator implements EventHandler {
    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public void handleEvent(Event event) {
        ResourceResolver resolver = null;
        try {
            resolver = resourceResolverFactory.getAdministrativeResourceResolver(null);
            Resource resource = resolver.getResource((String) event.getProperty(PROPERTY_PATH));

            if (resource == null) {
                return;
            }

            Invalidatable invalidatable = resource.adaptTo(Invalidatable.class);
            if (invalidatable == null) {
                return;
            }

            // FIXME: Invalidate something.

        } catch (LoginException e) {
            throw new RuntimeException(e);
        } finally {
            if (resolver != null) {
                resolver.close();
            }
        }
    }
}

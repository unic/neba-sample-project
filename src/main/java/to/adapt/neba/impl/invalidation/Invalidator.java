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
 * Demonstrates how NEBA leverages the resource model -&gt; resource type relationship to select the most specific
 * model when adapting to an interface. In this particular use case, the "Invalidator" may obtain a number of resources
 * that must be invalidated - e.g. flushed from a cache - when another resource changes.
 *
 * @author Olaf Otto
 */
@Service
@Component(immediate = true)
@Properties({
        @Property(name = EVENT_TOPIC, value = TOPIC_RESOURCE_CHANGED),
        @Property(name = EVENT_FILTER, value = "(path=/content/neba-sample/*)")
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

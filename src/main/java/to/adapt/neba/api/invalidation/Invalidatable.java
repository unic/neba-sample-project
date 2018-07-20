package to.adapt.neba.api.invalidation;

import org.apache.sling.api.resource.Resource;

import java.util.Collection;

/**
 * Something that exposes a number of resources to be invalidated during an event, such as resource creation.
 */
public interface Invalidatable {
    Collection<Resource> getResources();
}

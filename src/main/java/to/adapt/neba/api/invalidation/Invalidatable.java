package to.adapt.neba.api.invalidation;

import io.neba.api.annotations.ResourceModel;
import org.apache.sling.api.resource.Resource;

import java.util.Collection;

/**
 * Something that exposes a number of resources to be invalidated during an event, such as resource creation.
 * <p>
 * This interface may be implemented by multiple models for different resources. Then, when a resource is adapted to
 * this interface, the suitable model - i.e. the model {@link ResourceModel#value()} responsible for the resource's type} and
 * implementing this interface is instantiated, mapped and returned by the {@link Resource#adaptTo(Class) adaptation}.
 * </p>
 */
public interface Invalidatable {
    Collection<Resource> getResources();
}

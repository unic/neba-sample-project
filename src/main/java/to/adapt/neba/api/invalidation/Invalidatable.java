package to.adapt.neba.api.invalidation;

import org.apache.sling.api.resource.Resource;

import java.util.Collection;

/**
 * @author Olaf Otto
 */
public interface Invalidatable {
    Collection<Resource> getResources();
}

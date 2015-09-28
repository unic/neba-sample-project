package to.adapt.neba.impl.invalidation;

import org.apache.sling.api.resource.Resource;

import java.util.Collection;

/**
 * @author Olaf Otto
 */
public interface Invalidatable {
    Collection<Resource> getResources();
}

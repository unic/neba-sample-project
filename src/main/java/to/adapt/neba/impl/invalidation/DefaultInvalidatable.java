package to.adapt.neba.impl.invalidation;

import io.neba.api.annotations.ResourceModel;
import io.neba.api.annotations.This;
import org.apache.sling.api.resource.Resource;

import java.util.Collection;

import static java.util.Collections.singleton;

/**
 * @author Olaf Otto
 */
@ResourceModel(types = "nt:base")
public class DefaultInvalidatable implements Invalidatable {
    @This
    private Resource resource;

    @Override
    public Collection<Resource> getResources() {
        return singleton(resource);
    }
}

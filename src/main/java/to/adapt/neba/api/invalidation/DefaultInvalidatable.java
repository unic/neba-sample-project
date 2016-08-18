package to.adapt.neba.api.invalidation;

import io.neba.api.annotations.ResourceModel;
import io.neba.api.annotations.This;
import org.apache.sling.api.resource.Resource;

import java.util.Collection;

import static java.util.Collections.singleton;

/**
 * Demonstrates how models for node types at the root of the resource type hierarchy
 * apply to any resource type, unless there is an implementation for a more specific resource type.
 *
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

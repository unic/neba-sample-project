package to.adapt.neba.api.models.neba;

import io.neba.api.annotations.ResourceModel;
import io.neba.api.annotations.This;
import org.apache.sling.api.resource.Resource;
import to.adapt.neba.api.invalidation.Invalidatable;

import java.util.Collection;

import static java.util.Collections.singleton;

/**
 * Demonstrates how models for node types at the root of the resource type hierarchy
 * apply to any resource type, unless there is an implementation for a more specific resource type.
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

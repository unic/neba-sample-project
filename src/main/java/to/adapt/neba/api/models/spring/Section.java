package to.adapt.neba.api.models.spring;

import io.neba.api.annotations.ResourceModel;
import org.apache.sling.api.resource.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import to.adapt.neba.api.annotations.Siblings;
import to.adapt.neba.api.invalidation.Invalidatable;

import java.util.Collection;
import java.util.List;

/**
 * Demonstrates how a Spring bean can be used as a NEBA resource model.
 */
@Component
@Scope("prototype")
@ResourceModel("neba-sample/components/section")
public class Section implements Invalidatable {
    /**
     * Demonstrates the support for custom annotations created using meta annotations,
     * see {@link Siblings}.
     */
    @Siblings
    private List<Resource> siblings;

    @Override
    public Collection<Resource> getResources() {
        return siblings;
    }
}

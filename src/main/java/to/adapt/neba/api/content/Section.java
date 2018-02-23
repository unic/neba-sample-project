package to.adapt.neba.api.content;

import io.neba.api.annotations.ResourceModel;
import org.apache.sling.api.resource.Resource;
import to.adapt.neba.api.invalidation.Invalidatable;

import java.util.Collection;
import java.util.List;

/**
 * @author Olaf Otto
 */
@ResourceModel(types = "neba-sample/components/section")
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

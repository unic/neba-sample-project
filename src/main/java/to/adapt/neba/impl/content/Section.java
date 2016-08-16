package to.adapt.neba.impl.content;

import io.neba.api.annotations.Children;
import io.neba.api.annotations.Path;
import io.neba.api.annotations.ResourceModel;
import org.apache.sling.api.resource.Resource;
import to.adapt.neba.impl.invalidation.Invalidatable;

import java.util.Collection;
import java.util.List;

/**
 * @author Olaf Otto
 */
@ResourceModel(types = "neba-sample/components/section")
public class Section implements Invalidatable {
    @Path("..")
    @Children
    private List<Resource> siblings;

    @Override
    public Collection<Resource> getResources() {
        return siblings;
    }
}

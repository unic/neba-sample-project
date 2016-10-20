package to.adapt.neba.api.content;

import io.neba.api.annotations.Children;
import io.neba.api.annotations.Path;
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
     * Shows how the {@link Path} annotation can be used
     * to specify a relative path to refer to a resource in the content tree.
     *
     * NEBA will navigate to the parent node
     * (@{@link Path}("..")), then select the children (@{@link Children})
     * and finally provide them as a list of resources. Note that NEBA
     * automatically provides such collections as lazy-loading proxies.
     */
    @Path("..")
    @Children
    private List<Resource> siblings;

    @Override
    public Collection<Resource> getResources() {
        return siblings;
    }
}

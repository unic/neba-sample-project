package to.adapt.neba.impl.content;

import io.neba.api.annotations.Path;
import io.neba.api.annotations.ResourceModel;
import org.apache.sling.api.resource.Resource;
import to.adapt.neba.impl.invalidation.Invalidatable;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Olaf Otto
 */
@ResourceModel(types = "adaptto/components/header")
public class Header implements Invalidatable{
    private String title;
    @Path("..")
    private Resource page;

    public String getTitle() {
        return title;
    }

    @Override
    public Collection<Resource> getResources() {
        return singletonList(page);
    }
}

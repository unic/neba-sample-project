package to.adapt.neba.impl.content;

import io.neba.api.annotations.Path;
import io.neba.api.annotations.Reference;
import io.neba.api.annotations.ResourceModel;
import io.neba.api.resourcemodels.Optional;
import org.apache.sling.api.resource.Resource;
import to.adapt.neba.impl.invalidation.Invalidatable;

import java.util.Collection;

import static java.util.Collections.singletonList;

/**
 * @author Olaf Otto
 */
@ResourceModel(types = "neba-sample/components/header")
public class Header implements Invalidatable {
    private String title, text;

    @Reference
    @Path("link")
    private Optional<Page> page;

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    @Override
    public Collection<Resource> getResources() {
        return singletonList(page.get().getResource());
    }
}

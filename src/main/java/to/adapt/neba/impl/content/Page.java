package to.adapt.neba.impl.content;

import io.neba.api.annotations.ResourceModel;
import io.neba.api.annotations.This;
import io.neba.api.resourcemodels.Optional;
import org.apache.sling.api.resource.Resource;

import java.util.List;

/**
 * @author Olaf Otto
 */
@ResourceModel(types = "adaptto/components/page")
public class Page {
    private Header header;
    private Optional<Page> child;

    @This
    private Resource resource;

    @Sections
    private List<Section> sections;

    public Header getHeader() {
        return header;
    }

    public String getPath() {
        return resource.getPath();
    }
}

package to.adapt.neba.impl.content;

import io.neba.api.annotations.ResourceModel;
import io.neba.api.annotations.This;
import org.apache.sling.api.resource.Resource;

import java.util.List;

/**
 * @author Olaf Otto
 */
@ResourceModel(types = "neba-sample/components/page")
public class Page {
    private Header header;

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

    public Resource getResource() {
        return resource;
    }
}

package to.adapt.neba.api.content;

import io.neba.api.annotations.Children;
import io.neba.api.annotations.Path;
import io.neba.api.annotations.ResourceModel;
import io.neba.api.annotations.This;
import org.apache.sling.api.resource.Resource;
import to.adapt.neba.impl.content.Section;

import java.util.List;

/**
 * Models a page resource types, demonstrates mapping child resources using NEBA.
 */
@ResourceModel(types = "neba-sample/components/page")
public class Page {
    /**
     * Since this field has a type that cannot be mapped from a resource property,
     * NEBA will attempt to resolve a child resource named like this field ("header") and adapt it to
     * the field's type, i.e. to {@link Header}. The way the header is looked up can, for instance, be
     * altered using the {@link io.neba.api.annotations.Path} annotation.
     */
    private Header header;

    /**
     * {@link This} conveniently injects the resource adapted to the current model.
     */
    @This
    private Resource resource;

    @Path("section")
    @Children
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

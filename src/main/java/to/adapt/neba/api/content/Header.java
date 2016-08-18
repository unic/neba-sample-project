package to.adapt.neba.api.content;

import io.neba.api.annotations.Path;
import io.neba.api.annotations.Reference;
import io.neba.api.annotations.ResourceModel;
import io.neba.api.resourcemodels.Optional;
import org.apache.sling.api.resource.Resource;
import to.adapt.neba.api.invalidation.Invalidatable;

import java.util.Collection;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * @author Olaf Otto
 */
@ResourceModel(types = "neba-sample/components/header")
public class Header implements Invalidatable {
    /**
     * Since this field is neither final or static nor annotated
     * with @Inject or {@link io.neba.api.annotations.Unmapped}, NEBA
     * will attempt to resolve the property "title" and inject it into this field.
     * If the property "title" does not exist, the field's value is not changed, thus
     * allowing specification of a default value.
     */
    private String title = "Default title";

    /**
     * The property "link", as denoted by the {@link Path} annotation,
     * may contain a path pointing to another page., i.e. a reference to
     * another piece of content. This is expressed with the
     * {@link Reference} annotation.
     * Finally, using {@link Optional} makes sure this 1:1 relationship is
     * lazy-loaded.
     *
     * When requested, e.g. using {@link Optional#get()}, NEBA will resolve the path contained in
     * the property "link", and, if the path resolves to a resource, adapt the resolved resource
     * to {@link Page}.
     */
    @Reference
    @Path("link")
    private Optional<Page> page;

    @Override
    public Collection<Resource> getResources() {
        return page.isPresent() ? singletonList(page.get().getResource()) : emptyList();
    }

    public Page getLinkedPage() {
        return page.orElse(null);
    }

    public String getTitle() {
        return title;
    }
}

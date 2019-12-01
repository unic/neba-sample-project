package to.adapt.neba.api.models.neba;

import io.neba.api.annotations.Path;
import io.neba.api.annotations.ResourceModel;

/**
 * This model for JCR user nodes is used to render a JSON view. See also
 * SLING-INF/content/config/io.neba.core.resourcemodels.views.json.JsonViewServlets~nebaSample.config and
 * to.adapt.neba.impl.userinfo.{@link to.adapt.neba.impl.userinfo.UserInfoServlet}
 */
@ResourceModel("rep:User")
public class User {
    @Path("rep:principalName")
    private String name;

    public String getName() {
        return name;
    }
}

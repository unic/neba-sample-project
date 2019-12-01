package to.adapt.neba.impl.userinfo;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import javax.annotation.Nonnull;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.Iterator;

import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

/**
 * Finds the current users' JCR user node and redirects to a NEBA JSON view of that model.
 * See also SLING-INF/static-resources/js/user.js
 *
 * @see to.adapt.neba.api.models.neba.User
 */
@Component(service = Servlet.class)
@SlingServletPaths("/bin/neba-sample/userinfo")
public class UserInfoServlet extends SlingSafeMethodsServlet {
    @Override
    protected void doGet(@Nonnull SlingHttpServletRequest request, @Nonnull SlingHttpServletResponse response) throws IOException {
        Iterator<Resource> users = request.getResourceResolver().findResources(
                "SELECT [jcr:primaryType] FROM [rep:User] WHERE [rep:authorizableId] = '" + request.getRemoteUser() + "'",
                "JCR-SQL2");
        if (users.hasNext()) {
            response.addHeader("Cache-Control", "no-store, no-cache, max-age=0");
            response.sendRedirect(users.next().getPath() + ".neba.json");
            return;
        }

        response.sendError(SC_NOT_FOUND, "No resource was found for user " + request.getRemoteUser() + ".");
    }
}

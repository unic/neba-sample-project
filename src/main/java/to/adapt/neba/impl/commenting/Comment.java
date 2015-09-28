package to.adapt.neba.impl.commenting;

import io.neba.api.annotations.Path;
import io.neba.api.annotations.ResourceModel;
import io.neba.api.annotations.Unmapped;
import to.adapt.neba.impl.content.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Olaf Otto
 */
@ResourceModel(types = "adaptto/components/contact")
public class Comment {
    @Unmapped
    private String name, email, message;

    @Path("..")
    private Page page;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("email", email);
        map.put("message", message);
        map.put("page", page.getPath());
        return map;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

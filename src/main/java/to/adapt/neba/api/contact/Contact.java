package to.adapt.neba.api.contact;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * This model is used by the {@link to.adapt.neba.impl.contact.ContactController} to
 * back a form and validate the form data.
 *
 * @author Olaf Otto
 */
public class Contact implements Serializable {
    @NotBlank
    private String name;

    @Email @NotBlank
    private String email;

    @NotBlank
    private String message;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMessage() {
        return message;
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

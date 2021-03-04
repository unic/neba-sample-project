package to.adapt.neba.api.contact;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * This model is used by the {@link to.adapt.neba.impl.contact.ContactController} to
 * back a form and validate the form data. It si also used as a payload in the
 * job properties for the {@link to.adapt.neba.impl.contact.ContactEmailSender}, and is
 * thus {@link Serializable} since job properties are serialized to the repository.
 * Since de-serialization is happening in Sling's job services, this model's package must
 * be exported for Sling to be able to deserialize the payload.
 */
public class Contact implements Serializable {
    @NotBlank
    private String name;

    @Email
    @NotBlank
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

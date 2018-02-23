package to.adapt.neba.api.content;

import io.neba.api.annotations.Children;
import io.neba.api.annotations.Path;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Demonstrates how annotations can be composed (meta-annotations) into new semantic annotations.
 *
 * Shows how the {@link Path} annotation can be used
 * to specify a relative path to refer to a resource in the content tree.
 *
 * NEBA will navigate to the parent node
 * (@{@link Path}("..")), then select the children (@{@link Children})
 * and finally provide them as a list of resources. Note that NEBA
 * automatically provides such collections as lazy-loading proxies.

 * @author Olaf Otto
 */
@Path("..")
@Children
@Retention(RUNTIME)
@Target(FIELD)
public @interface Siblings {
}

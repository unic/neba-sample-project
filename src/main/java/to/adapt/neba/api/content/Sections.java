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
 * @author Olaf Otto
 */
@Path("section")
@Children
@Retention(RUNTIME)
@Target(FIELD)
public @interface Sections {
}

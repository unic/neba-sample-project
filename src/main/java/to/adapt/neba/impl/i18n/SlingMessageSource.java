package to.adapt.neba.impl.i18n;

import org.apache.sling.i18n.ResourceBundleProvider;
import org.eclipse.gemini.blueprint.extensions.annotation.ServiceReference;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.springframework.context.support.AbstractApplicationContext.MESSAGE_SOURCE_BEAN_NAME;

/**
 * Demonstrates how to link Spring's message sources to Sling's resource bundle mechanism.
 *
 * @author Olaf Otto
 */
@Service(MESSAGE_SOURCE_BEAN_NAME)
public class SlingMessageSource extends AbstractMessageSource {
    /**
     * Explicitly filter the available service to make sure only the sling resource bundle provider is imported, not the AEM one.
     */
    @ServiceReference(filter = "(component.name=org.apache.sling.i18n.impl.JcrResourceBundleProvider)")
    private ResourceBundleProvider resourceBundleProvider;

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        ResourceBundle resourceBundle = getResourceBundle(locale);
        String resolvedValue = resourceBundle.getString(code);
        if (code.equals(resolvedValue)) {
            return null;
        }
        return new MessageFormat(resolvedValue, locale);
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        String resolvedValue = getResourceBundle(locale).getString(code);
        if (code.equals(resolvedValue)) {
            return null;
        }
        return resolvedValue;
    }

    private ResourceBundle getResourceBundle(Locale locale) {
        return resourceBundleProvider.getResourceBundle(locale);
    }
}

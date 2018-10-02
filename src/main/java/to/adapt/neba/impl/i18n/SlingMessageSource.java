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
 */
@Service(MESSAGE_SOURCE_BEAN_NAME)
public class SlingMessageSource extends AbstractMessageSource {
    /**
     * Explicitly filter the available service to make sure only the sling resource bundle provider is imported, not a custom one, e.g. from Adobe AEM.
     */
    @ServiceReference(filter = "(component.name=org.apache.sling.i18n.impl.JcrResourceBundleProvider)")
    private ResourceBundleProvider resourceBundleProvider;

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String resolvedValue = resolveCodeWithoutArguments(code, locale);
        if (resolvedValue == null) {
            return null;
        }
        return new MessageFormat(resolvedValue, locale);
    }

    /**
     * Sling's resource bundles fall back to returning the key in case no value could be resolved. The message source
     * would thus treat the key as a resolved value and not use Spring's own fallback mechanisms, e.g. default texts defined in a view.
     * Thus, rather than returning the key when a value cannot be resolved, this method returns null.
     */
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

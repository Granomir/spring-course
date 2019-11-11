package students_testing_app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocaleMessageSource {
    private MessageSource messageSource;
    private Locale currentLocale;

    @Autowired
    public LocaleMessageSource(MessageSource messageSource, @Value("${locale}") String locale) {
        this.messageSource = messageSource;
        currentLocale = getCurrentLocale(locale);
    }

    private Locale getCurrentLocale(String locale) {
        String[] split = locale.split("_");
        return new Locale(split[0], split[1]);
    }

    public String getMessage(String code, String... variables) {
        return messageSource.getMessage(code, variables, currentLocale);
    }
}

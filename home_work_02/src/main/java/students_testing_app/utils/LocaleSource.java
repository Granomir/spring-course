package students_testing_app.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@PropertySource("classpath:application.properties")
public class LocaleSource {

    @Value("${locale}")
    private String locale;

    public Locale getLocale() {
        String[] split = locale.split("_");
        return new Locale(split[0], split[1]);
    }
}

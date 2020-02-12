package com.patrushev.home_work_04.utils;

import com.patrushev.home_work_04.ApplicationSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocaleMessageSource {
    private MessageSource messageSource;
    private Locale currentLocale;

    @Autowired
    public LocaleMessageSource(MessageSource messageSource, ApplicationSettings settings) {
        this.messageSource = messageSource;
        currentLocale = getCurrentLocale(settings.getLocale());
    }

    private Locale getCurrentLocale(String locale) {
        String[] split = locale.split("_");
        return new Locale(split[0], split[1]);
    }

    public String getMessage(String code, String... variables) {
        return messageSource.getMessage(code, variables, currentLocale);
    }

    public void setLocalization(String locale) {
        if (locale.equals("eng")) {
            currentLocale = new Locale("en", "EU");
        } else if (locale.equals("rus")) {
            currentLocale = new Locale("ru", "RU");
        }
    }
}

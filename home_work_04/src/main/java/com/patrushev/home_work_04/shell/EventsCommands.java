package com.patrushev.home_work_04.shell;

import com.patrushev.home_work_04.ApplicationSettings;
import com.patrushev.home_work_04.TestingService;
import com.patrushev.home_work_04.UserInputService;
import com.patrushev.home_work_04.utils.LocaleMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class EventsCommands {

    private final ApplicationSettings settings;
    private final TestingService testingService;
    private final UserInputService userInputService;
    private final LocaleMessageSource messageSource;

    @ShellMethod(value = "Login command.", key = {"l", "login"})
    public String login(@ShellOption(arity = 2) String adminCredentials) {
        String[] credentials = adminCredentials.split(",");
        if (credentials[0].equals(settings.getAdminName()) & credentials[1].equals(settings.getAdminPassword())) {
            testingService.performTest();
            return null;
        }
        return "Wrong credentials!";
    }

    @ShellMethod(value = "Locale command.", key = {"lang"})
    public String lang(@ShellOption String lang) {
        if (lang.equals("rus") || lang.equals("eng")) {
            messageSource.setLocalization(lang);
            return String.format("Language set to %s", lang);
        } else {
            return "Unsupported language!";
        }
    }

}

package students_testing_app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import students_testing_app.UserInteractingService;
import students_testing_app.model.Question;
import students_testing_app.model.Student;
import students_testing_app.utils.LocaleSource;

import java.util.Locale;

@Service
public class UserConsoleInteractingServiceImpl implements UserInteractingService {
    private MessageSource messageSource;
    private Locale locale;

    @Autowired
    public UserConsoleInteractingServiceImpl(MessageSource messageSource, LocaleSource localeSource) {
        this.messageSource = messageSource;
        locale = localeSource.getLocale();
    }

    @Override
    public void showQuestion(Question question) {
        System.out.println(messageSource.getMessage("q.num", new String[] {question.getQuestionNumber()}, locale));
        System.out.println(question.getQuestionText());
        System.out.println(messageSource.getMessage("ans.vars", null, locale));
        int count = 1;
        for (String variant : question.getVariants()) {
            System.out.println(String.format("%d. %s", count, variant));
            count++;
        }
        System.out.println(messageSource.getMessage("do.in", null, locale));
    }

    @Override
    public void greetUser() {
        System.out.println(messageSource.getMessage("greet.user", null, locale));
    }

    @Override
    public void askUserName() {
        System.out.println(messageSource.getMessage("ask.name", null, locale));
    }

    @Override
    public void askUserSurname() {
        System.out.println(messageSource.getMessage("ask.sur", null, locale));
    }

    @Override
    public void showResult(Student student) {
        System.out.println(messageSource.getMessage("shw.reslt", new String[] {student.getName(), student.getSurname(), String.valueOf(student.getPoints())}, locale));
    }
}

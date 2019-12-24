package students_testing_app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import students_testing_app.UserInteractingService;
import students_testing_app.model.Question;
import students_testing_app.model.Student;
import students_testing_app.utils.LocaleMessageSource;

@Service
public class UserConsoleInteractingServiceImpl implements UserInteractingService {
    private LocaleMessageSource localeMessageSource;

    @Autowired
    public UserConsoleInteractingServiceImpl(LocaleMessageSource localeMessageSource) {
        this.localeMessageSource = localeMessageSource;
    }

    @Override
    public void showQuestion(Question question) {
        System.out.println(localeMessageSource.getMessage("q.num", question.getQuestionNumber()));
        System.out.println(question.getQuestionText());
        System.out.println(localeMessageSource.getMessage("ans.vars"));
        int count = 1;
        for (String variant : question.getVariants()) {
            System.out.println(String.format("%d. %s", count, variant));
            count++;
        }
        System.out.println(localeMessageSource.getMessage("do.in"));
    }

    @Override
    public void greetUser() {
        System.out.println(localeMessageSource.getMessage("greet.user"));
    }

    @Override
    public void askUserName() {
        System.out.println(localeMessageSource.getMessage("ask.name"));
    }

    @Override
    public void askUserSurname() {
        System.out.println(localeMessageSource.getMessage("ask.sur"));
    }

    @Override
    public void showResult(Student student) {
        System.out.println(localeMessageSource.getMessage("shw.reslt", student.getName(), student.getSurname(), String.valueOf(student.getPoints())));
    }
}

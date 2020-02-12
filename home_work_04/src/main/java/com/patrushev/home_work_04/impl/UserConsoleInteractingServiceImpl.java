package com.patrushev.home_work_04.impl;

import com.patrushev.home_work_04.UserInteractingService;
import com.patrushev.home_work_04.model.Question;
import com.patrushev.home_work_04.model.Student;
import com.patrushev.home_work_04.utils.LocaleMessageSource;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserConsoleInteractingServiceImpl implements UserInteractingService {
    private final LocaleMessageSource localeMessageSource;

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

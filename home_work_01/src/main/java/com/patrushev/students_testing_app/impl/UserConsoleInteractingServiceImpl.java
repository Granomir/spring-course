package com.patrushev.students_testing_app.impl;

import com.patrushev.students_testing_app.UserInteractingService;
import com.patrushev.students_testing_app.model.Question;
import com.patrushev.students_testing_app.model.Student;

public class UserConsoleInteractingServiceImpl implements UserInteractingService {
    public void showQuestion(Question question) {
        System.out.println();
    }

    public void greetUser() {
        System.out.println("Добрый день! Ваша задача - ответить на 5 вопросов с двумя вариантами ответов.");
    }

    public void askUserName() {
        System.out.println("Введите ваше имя:");
    }

    public void askUserSurname() {
        System.out.println("Введите вашу фамилию:");
    }

    public void showResult(Student student) {

    }
}

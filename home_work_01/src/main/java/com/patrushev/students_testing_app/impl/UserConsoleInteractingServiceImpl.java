package com.patrushev.students_testing_app.impl;

import com.patrushev.students_testing_app.UserInteractingService;
import com.patrushev.students_testing_app.model.Question;
import com.patrushev.students_testing_app.model.Student;

public class UserConsoleInteractingServiceImpl implements UserInteractingService {
    @Override
    public void showQuestion(Question question) {
        System.out.println("Вопрос №" + question.getQuestionNumber() + ":");
        System.out.println(question.getQuestionText());
        System.out.println("Варианты ответа:");
        int count = 1;
        for (String variant : question.getVariants()) {
            System.out.println(count + ". " + variant);
            count++;
        }
        System.out.println("Введите номер правильного ответа");
    }

    @Override
    public void greetUser() {
        System.out.println("Добрый день! Ваша задача - ответить на 5 вопросов с двумя вариантами ответов.");
    }

    @Override
    public void askUserName() {
        System.out.println("Введите ваше имя:");
    }

    @Override
    public void askUserSurname() {
        System.out.println("Введите вашу фамилию:");
    }

    @Override
    public void showResult(Student student) {
        System.out.println("Студент " + student.getName() + " " + student.getSurname() + " правильно ответил на " + student.getPoints() + " вопросов");
    }
}

package students_testing_app.impl;

import org.springframework.stereotype.Service;
import students_testing_app.UserInteractingService;
import students_testing_app.model.Question;
import students_testing_app.model.Student;

@Service
public class UserConsoleInteractingServiceImpl implements UserInteractingService {
    @Override
    public void showQuestion(Question question) {
        System.out.println(String.format("Вопрос № %s:", question.getQuestionNumber()));
        System.out.println(question.getQuestionText());
        System.out.println("Варианты ответа:");
        int count = 1;
        for (String variant : question.getVariants()) {
            System.out.println(String.format("%d. %s", count, variant));
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
        System.out.println(String.format("Студент %s %s правильно ответил на %d вопросов", student.getName(), student.getSurname(), student.getPoints()));
    }
}

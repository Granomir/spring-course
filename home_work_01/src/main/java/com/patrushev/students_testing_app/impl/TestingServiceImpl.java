package com.patrushev.students_testing_app.impl;

import com.patrushev.students_testing_app.QuestionsService;
import com.patrushev.students_testing_app.TestingService;
import com.patrushev.students_testing_app.UserInputService;
import com.patrushev.students_testing_app.UserInteractingService;
import com.patrushev.students_testing_app.model.Question;
import com.patrushev.students_testing_app.model.Student;

public class TestingServiceImpl implements TestingService {
    private UserInteractingService userInteractingService;
    private UserInputService userInputService;
    private Student student;
    private QuestionsService questionsService;

    public void performTest() {
        prepareUser();
        questionsService.prepareQuestions();
        Question question;
        while ((question = questionsService.getNextQuestion()) != null) {
            askStudent(question);
        }
    }

    private void askStudent(Question question) {
        userInteractingService.showQuestion(question);
        String userAnswer = userInputService.getUserInput();
        if (userAnswer.equals(question.getRightAnswer())) {
            student.addPoint();
        }
    }

    private void prepareUser() {
        userInteractingService.greetUser();
        userInteractingService.askUserName();
        student.setName(userInputService.getUserInput());
        userInteractingService.askUserSurname();
        student.setSurname(userInputService.getUserInput());
    }

    public void showResult() {
        userInteractingService.showResult(student);
    }
}

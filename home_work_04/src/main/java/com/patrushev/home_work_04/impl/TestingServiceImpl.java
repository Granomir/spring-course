package com.patrushev.home_work_04.impl;

import com.patrushev.home_work_04.QuestionsService;
import com.patrushev.home_work_04.TestingService;
import com.patrushev.home_work_04.UserInputService;
import com.patrushev.home_work_04.UserInteractingService;
import com.patrushev.home_work_04.model.Question;
import com.patrushev.home_work_04.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestingServiceImpl implements TestingService {
    private UserInteractingService userInteractingService;
    private UserInputService userInputService;
    private Student student;
    private QuestionsService questionsService;

    @Autowired
    public TestingServiceImpl(UserInteractingService userInteractingService, UserInputService userInputService, QuestionsService questionsService) {
        this.userInteractingService = userInteractingService;
        this.userInputService = userInputService;
        this.questionsService = questionsService;
        student = new Student();
    }

    @Override
    public void performTest() {
        prepareUser();
        questionsService.prepareQuestions();
        Question question;
        while ((question = questionsService.getNextQuestion()) != null) {
            askStudent(question);
        }
        userInputService.close();
        showResult();
    }

    private void askStudent(Question question) {
        userInteractingService.showQuestion(question);
        String userAnswer = userInputService.getUserInput();
        if (userAnswer.equals(question.getRightVariantNumber())) {
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

    @Override
    public void showResult() {
        userInteractingService.showResult(student);
    }
}

package students_testing_app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import students_testing_app.QuestionsService;
import students_testing_app.TestingService;
import students_testing_app.UserInputService;
import students_testing_app.UserInteractingService;
import students_testing_app.model.Question;
import students_testing_app.model.Student;

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

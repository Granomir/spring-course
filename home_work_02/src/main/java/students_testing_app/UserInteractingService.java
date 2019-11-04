package students_testing_app;

import students_testing_app.model.Question;
import students_testing_app.model.Student;

public interface UserInteractingService {
    void showQuestion(Question question);

    void greetUser();

    void askUserName();

    void askUserSurname();

    void showResult(Student student);
}

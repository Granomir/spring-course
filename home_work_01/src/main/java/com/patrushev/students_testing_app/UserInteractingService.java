package com.patrushev.students_testing_app;

import com.patrushev.students_testing_app.model.Question;
import com.patrushev.students_testing_app.model.Student;

public interface UserInteractingService {
    void showQuestion(Question question);

    void greetUser();

    void askUserName();

    void askUserSurname();

    void showResult(Student student);
}

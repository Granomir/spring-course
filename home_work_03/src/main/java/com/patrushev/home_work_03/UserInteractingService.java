package com.patrushev.home_work_03;

import com.patrushev.home_work_03.model.Question;
import com.patrushev.home_work_03.model.Student;

public interface UserInteractingService {
    void showQuestion(Question question);

    void greetUser();

    void askUserName();

    void askUserSurname();

    void showResult(Student student);
}

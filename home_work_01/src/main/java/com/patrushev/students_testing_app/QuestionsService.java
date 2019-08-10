package com.patrushev.students_testing_app;

import com.patrushev.students_testing_app.model.Question;

public interface QuestionsService {
    void prepareQuestions();

    Question getNextQuestion();
}

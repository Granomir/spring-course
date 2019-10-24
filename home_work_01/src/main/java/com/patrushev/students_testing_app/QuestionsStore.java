package com.patrushev.students_testing_app;

import com.patrushev.students_testing_app.model.Question;

import java.util.List;

public interface QuestionsStore {
    List<Question> getQuestions();
}

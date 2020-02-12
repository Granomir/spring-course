package com.patrushev.home_work_04;

import com.patrushev.home_work_04.model.Question;

public interface QuestionsService {
    void prepareQuestions();

    Question getNextQuestion();
}

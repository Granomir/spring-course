package com.patrushev.home_work_03;

import com.patrushev.home_work_03.model.Question;

public interface QuestionsService {
    void prepareQuestions();

    Question getNextQuestion();
}

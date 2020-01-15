package com.patrushev.home_work_03;

import com.patrushev.home_work_03.model.Question;

import java.util.List;

public interface QuestionsStore {
    List<Question> getQuestions();
}

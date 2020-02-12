package com.patrushev.home_work_04;

import com.patrushev.home_work_04.model.Question;

import java.util.List;

public interface QuestionsStore {
    List<Question> getQuestions();
}

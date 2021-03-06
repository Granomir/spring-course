package com.patrushev.students_testing_app.impl;

import com.patrushev.students_testing_app.QuestionsService;
import com.patrushev.students_testing_app.QuestionsStore;
import com.patrushev.students_testing_app.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsServiceImpl implements QuestionsService {
    private QuestionsStore questionsStore;
    private int count = 0;
    private List<Question> questionList;

    public QuestionsServiceImpl(QuestionsStore questionsStore) {
        this.questionsStore = questionsStore;
        questionList = new ArrayList<>();
    }

    @Override
    public void prepareQuestions() {
        questionList.addAll(questionsStore.getQuestions());
    }

    @Override
    public Question getNextQuestion() {
        if (count < questionList.size()) {
            Question nextQuestion = questionList.get(count);
            count++;
            return nextQuestion;
        } else {
            return null;
        }
    }
}

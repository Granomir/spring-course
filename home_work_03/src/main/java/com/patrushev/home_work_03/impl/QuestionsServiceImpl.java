package com.patrushev.home_work_03.impl;

import com.patrushev.home_work_03.QuestionsService;
import com.patrushev.home_work_03.QuestionsStore;
import com.patrushev.home_work_03.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {
    private QuestionsStore questionsStore;
    private int count = 0;
    private List<Question> questionList;

    @Autowired
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

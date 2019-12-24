package com.patrushev.home_work_03;

import com.patrushev.home_work_03.model.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuestionsStoreTest {

    @Autowired
    private QuestionsStore questionsStore;

    @Test
    void getQuestions() {
        final List<Question> questions = questionsStore.getQuestions();
        assertEquals(1, questions.size());
        final Question question = new Question("1", "вопрос1", new String[]{"ответ1", "ответ2"}, "1");
        assertEquals(question, questions.get(0));
    }
}
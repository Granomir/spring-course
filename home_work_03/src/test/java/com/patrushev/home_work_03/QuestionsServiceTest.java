package com.patrushev.home_work_03;

import com.patrushev.home_work_03.model.Question;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class QuestionsServiceTest {

    @MockBean
    private QuestionsStore questionsStore;

    @Autowired
    private QuestionsService questionsService;

    @Test
    void getNextQuestionShouldBeOnlyOneAndCorrect() {
        final Question question = new Question("1", "вопрос1", new String[]{"ответ1", "ответ2"}, "1");
        given(questionsStore.getQuestions()).willReturn(Lists.newArrayList(question));
        questionsService.prepareQuestions();
        assertEquals(question, questionsService.getNextQuestion());
        assertNull(questionsService.getNextQuestion());
    }
}
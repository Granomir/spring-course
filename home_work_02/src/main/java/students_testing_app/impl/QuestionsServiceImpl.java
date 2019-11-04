package students_testing_app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import students_testing_app.QuestionsService;
import students_testing_app.QuestionsStore;
import students_testing_app.model.Question;

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

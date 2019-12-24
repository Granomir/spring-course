package students_testing_app;

import students_testing_app.model.Question;

import java.util.List;

public interface QuestionsStore {
    List<Question> getQuestions();
}

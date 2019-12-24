package students_testing_app;

import students_testing_app.model.Question;

public interface QuestionsService {
    void prepareQuestions();

    Question getNextQuestion();
}

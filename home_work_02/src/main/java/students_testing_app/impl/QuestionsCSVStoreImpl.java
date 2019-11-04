package students_testing_app.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import students_testing_app.QuestionsStore;
import students_testing_app.model.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class QuestionsCSVStoreImpl implements QuestionsStore {

    @Value("${csv.file}")
    private String questionsFileSource;

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(questionsFileSource)))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);
            for (CSVRecord record : records) {
                addQuestion(questionList, record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionList;
    }

    private void addQuestion(List<Question> questionList, CSVRecord record) {
        String questionNumber = record.get("number");
        String questionText = record.get("question");
        String firstVariant = record.get("firstVariant");
        String secondVariant = record.get("secondVariant");
        String rightVariant = record.get("rightVariant");
        questionList.add(new Question(questionNumber, questionText, new String[]{firstVariant, secondVariant}, rightVariant));
    }
}

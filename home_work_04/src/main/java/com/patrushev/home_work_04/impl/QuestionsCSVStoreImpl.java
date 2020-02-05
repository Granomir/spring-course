package com.patrushev.home_work_04.impl;

import com.patrushev.home_work_04.ApplicationSettings;
import com.patrushev.home_work_04.QuestionsStore;
import com.patrushev.home_work_04.model.Question;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class QuestionsCSVStoreImpl implements QuestionsStore {

    private String questionsFileSource;

    public QuestionsCSVStoreImpl(ApplicationSettings settings) {
        questionsFileSource = String.format("%s_%s.csv", settings.getCsvPrefix(), settings.getLocale());
    }

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

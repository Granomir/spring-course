package com.patrushev.students_testing_app.impl;

import com.patrushev.students_testing_app.QuestionsStore;
import com.patrushev.students_testing_app.model.Question;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class QuestionsCSVStoreImpl implements QuestionsStore {
    private Properties config;

    public QuestionsCSVStoreImpl() {
        this.config = new Properties();
        try (InputStream configSource = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            config.load(Objects.requireNonNull(configSource));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(config.getProperty("csv.file"))))) {
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

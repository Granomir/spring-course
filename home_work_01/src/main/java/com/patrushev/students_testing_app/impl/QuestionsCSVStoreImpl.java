package com.patrushev.students_testing_app.impl;

import com.patrushev.students_testing_app.QuestionsStore;
import com.patrushev.students_testing_app.model.Question;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class QuestionsCSVStoreImpl implements QuestionsStore {
    private Properties config;

    public QuestionsCSVStoreImpl() {
        this.config = new Properties();
        try (FileInputStream fis = new FileInputStream("home_work_01\\src\\main\\resources\\config.properties")) {
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();
        Iterable<CSVRecord> records;
        try (Reader fileReader = new FileReader(config.getProperty("csv.path"))) {
            String[] headers = new String[]{"number", "question", "firstVariant", "secondVariant", "rightVariant"};
            records = CSVFormat.DEFAULT
                    .withHeader(headers)
                    .withFirstRecordAsHeader()
                    .parse(fileReader);
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

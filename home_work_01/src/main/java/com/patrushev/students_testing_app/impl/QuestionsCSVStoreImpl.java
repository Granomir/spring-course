package com.patrushev.students_testing_app.impl;

import com.patrushev.students_testing_app.QuestionsStore;
import com.patrushev.students_testing_app.model.Question;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionsCSVStoreImpl implements QuestionsStore {
    @Override
    public List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<>();
        Iterable<CSVRecord> records;
        try (Reader fileReader = new FileReader("resources/questions.csv")) {
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

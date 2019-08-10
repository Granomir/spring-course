package com.patrushev.students_testing_app.model;

public class Question {
    private String questionNumber;
    private String questionText;
    private String[] strings;
    private String rightVariantNumber;

    public Question(String questionNumber, String questionText, String[] strings, String rightVariantNumber) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.strings = strings;
        this.rightVariantNumber = rightVariantNumber;
    }

    public String getRightAnswer() {
        return null;
    }
}

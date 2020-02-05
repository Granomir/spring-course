package com.patrushev.home_work_04.model;

import java.util.Arrays;
import java.util.Objects;

public class Question {
    private String questionNumber;
    private String questionText;
    private String[] variants;
    private String rightVariantNumber;

    public Question(String questionNumber, String questionText, String[] variants, String rightVariantNumber) {
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.variants = variants;
        this.rightVariantNumber = rightVariantNumber;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getVariants() {
        return variants;
    }

    public String getRightVariantNumber() {
        return rightVariantNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (!Objects.equals(questionNumber, question.questionNumber))
            return false;
        if (!Objects.equals(questionText, question.questionText))
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(variants, question.variants)) return false;
        return Objects.equals(rightVariantNumber, question.rightVariantNumber);
    }

    @Override
    public int hashCode() {
        int result = questionNumber != null ? questionNumber.hashCode() : 0;
        result = 31 * result + (questionText != null ? questionText.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(variants);
        result = 31 * result + (rightVariantNumber != null ? rightVariantNumber.hashCode() : 0);
        return result;
    }
}

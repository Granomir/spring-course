package students_testing_app.model;

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
}

package edu.gsu.csci5338.geoquizelite;

public class Question {
    private String someString;
    private boolean answerTrue;
    private boolean answered = false;
    private String answer;

    public Question(String question, boolean answerTrue){
        someString = question;
        this.answerTrue = answerTrue;
    }

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String question) {
        someString = question;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        this.answerTrue = answerTrue;
    }

    public boolean wasQuestionAnswered() {
        return answered;
    }

    public void setAnswered() {
        this.answered = true;
    }

    public String userAnswered() {
        return answer;
    }

    public void setAnswer(String mAnswer) {
        this.answer = mAnswer;
    }
}

package edu.gsu.csci5338.geoquizelite;

public class Question {
    private String someString;
    private boolean mAnswerTrue;
    private boolean mAnswered;
    private String mAnswer;

    public Question(String question, boolean answerTrue){
        someString = question;
        mAnswerTrue = answerTrue;
    }

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String question) {
        someString = question;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean ismAnswered() {
        return mAnswered;
    }

    public void setmAnswered() {
        this.mAnswered = true;
    }

    public String ismAnswer() {
        return mAnswer;
    }

    public void setmAnswer(String mAnswer) {
        this.mAnswer = mAnswer;
    }
}

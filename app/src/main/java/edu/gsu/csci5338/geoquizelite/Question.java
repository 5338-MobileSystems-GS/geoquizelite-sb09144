package edu.gsu.csci5338.geoquizelite;

public class Question {
    private String someString;
    private boolean mAnswerTrue;

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
}

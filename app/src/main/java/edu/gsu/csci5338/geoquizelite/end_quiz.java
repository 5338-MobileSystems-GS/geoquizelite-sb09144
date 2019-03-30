package edu.gsu.csci5338.geoquizelite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class end_quiz extends AppCompatActivity {

    private TextView correct;
    private TextView incorrect;
    private TextView cheated;
    private TextView unanswered;



   // int totalScorePercent = /10;

//    String correctString = "Total Score: " + b.get("totalScore");
   /* String incorrectString = "Incorrect Number: " + Integer.toString(incorrectNumber);
    String cheatedString = "Cheated Number: " + Integer.toString(cheatedNumber);
    String unAnsweredString = "Unanswered Questions: " + Integer.toString(unAnsweredNumber);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent iit = getIntent();
        Bundle b = iit.getExtras();
        int calculateScore = ((Integer) b.get("totalScore") * 100 ) / 10;
        String correctString = "Total Score: " + calculateScore + "%";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_quiz);
        /////
        correct = (TextView) findViewById(R.id.correct_answer_number);
       /* incorrect = (TextView) findViewById(R.id.incorrect_answer_number);
        cheated = (TextView) findViewById(R.id.cheat_number);
        unanswered = (TextView) findViewById(R.id.unanswered_number);*/

        correct.setText(correctString);
       /* incorrect.setText(incorrectString);
        cheated.setText(cheatedString);
        unanswered.setText(unAnsweredString);*/
    }
}

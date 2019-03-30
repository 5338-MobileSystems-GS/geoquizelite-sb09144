package edu.gsu.csci5338.geoquizelite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.*;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public int numScore = 0;
    public int totalAnswered = 0;
    public boolean cheated;
    public ArrayList<Question> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private static final int REQUEST_CODE_CHEAT = 0;
    private boolean mIsCheater;
    private boolean userPressedTrue;
    boolean whatIsAnswer;
    Context context = null;
    Toast toast;

    // data is passed into the constructor
    RecyclerViewAdapter(Context context, ArrayList<Question> data) {
        mInflater = LayoutInflater.from(context);
        mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Question question = mData.get(position);
        Log.d("onBindViewHolder", ""+position);
        holder.myTextView.setText(question.getSomeString());
        holder.setIndex(position);


        if(!question.ismAnswered()){
            holder.trueButton.setBackgroundColor(WHITE);
            holder.falseButton.setBackgroundColor(WHITE);
            holder.cheatButton.setBackgroundColor(WHITE);
            holder.cheatButton.setClickable(true);
            holder.trueButton.setClickable(true);
            holder.falseButton.setClickable(true);
        }//Boolean.parseBoolean(question.ismAnswer())
        else if (question.ismAnswered()){
            if(question.ismAnswer().contains("true")){
                if(question.isAnswerTrue()){
                    holder.trueButton.setBackgroundColor(GREEN);
                    holder.falseButton.setClickable(false);
                    holder.trueButton.setClickable(false);
                    holder.cheatButton.setBackgroundColor(GRAY);
                    holder.cheatButton.setClickable(false);
                    holder.falseButton.setBackgroundColor(GRAY);
                }else{
                    holder.trueButton.setBackgroundColor(RED);
                    holder.falseButton.setClickable(false);
                    holder.trueButton.setClickable(false);
                    holder.cheatButton.setBackgroundColor(GRAY);
                    holder.cheatButton.setClickable(false);
                    holder.falseButton.setBackgroundColor(GRAY);
                }
            }else if(question.ismAnswer().contains("false")){
                if(!question.isAnswerTrue()){
                    holder.falseButton.setBackgroundColor(GREEN);
                    holder.falseButton.setClickable(false);
                    holder.trueButton.setClickable(false);
                    holder.cheatButton.setBackgroundColor(GRAY);
                    holder.cheatButton.setClickable(false);
                    holder.trueButton.setBackgroundColor(GRAY);
                }else{
                    holder.falseButton.setBackgroundColor(RED);
                    holder.falseButton.setClickable(false);
                    holder.trueButton.setClickable(false);
                    holder.cheatButton.setBackgroundColor(GRAY);
                    holder.cheatButton.setClickable(false);
                    holder.trueButton.setBackgroundColor(GRAY);
                }
            }
        }

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        private int index;
        Button trueButton, falseButton, cheatButton;
        void setIndex(int index){ this.index = index; }


        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.questionTextView);
            trueButton= itemView.findViewById(R.id.trueButton);
            falseButton= itemView.findViewById(R.id.falseButton);
            cheatButton = itemView.findViewById(R.id.cheatButton);
            Log.d("in ViewHolder: ", ""+index);
            trueButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    trueButton.setClickable(true);
                    falseButton.setClickable(true);
                    userPressedTrue = true;
                    whatIsAnswer = mData.get(index).isAnswerTrue();
                    Log.d("True button", "onClick: " + index);
                    if(userPressedTrue == whatIsAnswer) {
                        numScore++;
                        totalAnswered++;
                        mData.get(index).setmAnswered();  //
                        mData.get(index).setmAnswer("true"); //
                        trueButton.setBackgroundColor(GREEN);
                        falseButton.setClickable(false);
                        trueButton.setClickable(false);
                        cheatButton.setClickable(false);
                        cheatButton.setBackgroundColor(GRAY);
                        falseButton.setBackgroundColor(GRAY);
                    }
                    else{
                        totalAnswered++;
                        mData.get(index).setmAnswered();  //
                        mData.get(index).setmAnswer("true"); //
                        trueButton.setBackgroundColor(RED);
                        falseButton.setClickable(false);
                        trueButton.setClickable(false);
                        cheatButton.setClickable(false);
                        cheatButton.setBackgroundColor(GRAY);
                        falseButton.setBackgroundColor(GRAY);
                    }
                    if(totalAnswered == 10){
                        Intent intent = new Intent(v.getContext(), end_quiz.class);
                        intent.putExtra("totalScore", numScore);
                        v.getContext().startActivity(intent);
                    }
                }
            });
            falseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    whatIsAnswer = mData.get(index).isAnswerTrue();
                    userPressedTrue = false;
                    trueButton.setClickable(true);
                    falseButton.setClickable(true);
                    Log.d("false button", "onClick: " + index);
                    if(userPressedTrue == whatIsAnswer){
                        numScore++;
                        totalAnswered++;
                        mData.get(index).setmAnswered();  //
                        mData.get(index).setmAnswer("false"); //
                        falseButton.setBackgroundColor(GREEN);
                        cheatButton.setBackgroundColor(GRAY);
                        cheatButton.setClickable(false);
                        falseButton.setClickable(false);
                        trueButton.setClickable(false);
                        trueButton.setBackgroundColor(GRAY);
                    }
                    else{
                        totalAnswered++;
                        mData.get(index).setmAnswered();  //
                        mData.get(index).setmAnswer("false"); //
                        falseButton.setBackgroundColor(RED);
                        falseButton.setClickable(false);
                        cheatButton.setBackgroundColor(GRAY);
                        cheatButton.setClickable(false);
                        trueButton.setClickable(false);
                        trueButton.setBackgroundColor(GRAY);
                    }
                    if(totalAnswered == 10){
                        Intent intent = new Intent(v.getContext(), end_quiz.class);
                        intent.putExtra("totalScore", numScore);
                        v.getContext().startActivity(intent);
                    }
                }
            });
            cheatButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    totalAnswered++;
                    mData.get(index).ismAnswered();
                    falseButton.setBackgroundColor(GRAY);
                    falseButton.setClickable(false);
                    trueButton.setBackgroundColor(GRAY);
                    cheatButton.setClickable(false);
                    trueButton.setClickable(false);
                    cheatButton.setBackgroundColor(YELLOW);
                   // c.cheatClicked(v, index);
                    boolean answerIsTrue = mData.get(index).isAnswerTrue();
                    Intent i = new Intent (v.getContext(), CheatActivity.class);

                    //startActivity(i);
                   v.getContext().startActivity(i);
                    if(totalAnswered == 10){
                        Intent intent = new Intent(v.getContext(), end_quiz.class);
                        intent.putExtra("totalScore", numScore);
                        v.getContext().startActivity(intent);
                    }
                }
            });
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
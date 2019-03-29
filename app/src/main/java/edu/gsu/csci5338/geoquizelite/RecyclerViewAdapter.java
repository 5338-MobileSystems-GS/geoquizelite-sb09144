package edu.gsu.csci5338.geoquizelite;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.graphics.Color.GRAY;
import static android.graphics.Color.*;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Question> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private boolean userPressedTrue;
    boolean whatIsAnswer;
    Toast toast;

    // data is passed into the constructor
    RecyclerViewAdapter(Context context, List<Question> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Question question = mData.get(position);
        holder.myTextView.setText(question.getSomeString());
        holder.setIndex(position);

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

            trueButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        trueButton.setClickable(true);
                        falseButton.setClickable(true);
                        userPressedTrue = true;
                        whatIsAnswer = mData.get(index).isAnswerTrue();
                        if(userPressedTrue == whatIsAnswer) {
                            trueButton.setBackgroundColor(GREEN);
                            falseButton.setClickable(false);
                            trueButton.setClickable(false);
                            falseButton.setBackgroundColor(GRAY);
                        }
                        else{
                            trueButton.setBackgroundColor(RED);
                            falseButton.setClickable(false);
                            trueButton.setClickable(false);
                            falseButton.setBackgroundColor(GRAY);
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
                    if(userPressedTrue == whatIsAnswer){
                        falseButton.setBackgroundColor(GREEN);
                        falseButton.setClickable(false);
                        trueButton.setClickable(false);
                        trueButton.setBackgroundColor(GRAY);
                    }
                    else{
                        falseButton.setBackgroundColor(RED);
                        falseButton.setClickable(false);
                        trueButton.setClickable(false);
                        trueButton.setBackgroundColor(GRAY);
                    }
                }
            });
            itemView.setOnClickListener(this);
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
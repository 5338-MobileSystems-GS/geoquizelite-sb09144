package edu.gsu.csci5338.geoquizelite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    RecyclerViewAdapter adapter;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to populate the RecyclerView with
        ArrayList<Question> questionBank = new ArrayList<>();
        Question q = null;

        q = new Question("Seattle is more northerly than New York City", true);
        questionBank.add(q);

        q = new Question("Rhode Island has a greater population than Mongolia", false);
        questionBank.add(q);

        q = new Question("Memphis is the state capital of Tennessee", false);
        questionBank.add(q);

        q = new Question("The currency of Switzerland is the Euro", false);
        questionBank.add(q);

        q = new Question("China borders the same number of countries as Russia", true);
        questionBank.add(q);

        q = new Question("The Indian Ocean is the third largest ocean in the world", true);
        questionBank.add(q);

        q = new Question("There are more countries in Africa than Asia", true);
        questionBank.add(q);

        q = new Question("Brasilia is the capital city of Brazil", true);
        questionBank.add(q);

        q = new Question("Mount Kilimanjaro is higher than Denali", false);
        questionBank.add(q);

        q = new Question("The Sahara Desert has a greater area than USA", false);
        questionBank.add(q);

        dbManager = new DBManager(this);
        dbManager.open();


        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, questionBank);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
       // Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}
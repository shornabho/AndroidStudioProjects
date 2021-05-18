package com.mcs.android.testio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mcs.android.testio.adapters.QuestionRecyclerViewAdapter;
import com.mcs.android.testio.adapters.TestRecyclerViewAdapter;
import com.mcs.android.testio.models.Question;
import com.mcs.android.testio.models.Response;
import com.mcs.android.testio.models.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class TestActivity extends AppCompatActivity {
    private RecyclerView questionRecyclerView;
    private QuestionRecyclerViewAdapter questionRecyclerViewAdapter;
    private Button submitButton;
    public ArrayList<Question> questionArrayList;

    private static int lastResponseId = 0;
    public ArrayList<Response> responseArrayList = new ArrayList<Response>();
    private Intent resultsIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        questionRecyclerView = (RecyclerView) findViewById(R.id.questionRecyclerView);
        questionRecyclerView.setHasFixedSize(true);
        questionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        questionArrayList = new ArrayList<Question>();

        questionArrayList.add(new Question("01", "01", "Who is the Prime Minister of India?", Arrays.asList("Narendra Modi", "Arvind Kejriwal", "Mamata Banerjee", "Pinarayi Vijayan"), "Narendra Modi"));
        questionArrayList.add(new Question("02", "01", "Which one of the following river flows between Vindhyan and Satpura ranges?", Arrays.asList("Narmada", "Mahanadi", "Son", "Netravati"), "Narmada"));
        questionArrayList.add(new Question("03", "01", "The Central Rice Research Station is situated in?", Arrays.asList("Chennai", "Cuttack", "Bangalore", "Quilon"), "Cuttack"));
        questionArrayList.add(new Question("04", "01", "Which among the following headstreams meets the Ganges in last?", Arrays.asList("Alaknanda", "Pindar", "Mandakini", "Bhagirathi"), "Bhagirathi"));
        questionArrayList.add(new Question("05", "01", "Who among the following wrote Sanskrit grammar?", Arrays.asList("Kalidasa", "Charak", "Panini", "Aryabhatt"), "Panini"));

        for (Question question : questionArrayList) {
            responseArrayList.add(new Response(++lastResponseId, question.getTestId(), "User 1", question.getId(), "", new Date()));
        }

        questionRecyclerViewAdapter = new QuestionRecyclerViewAdapter(this, questionArrayList);
        questionRecyclerView.setAdapter(questionRecyclerViewAdapter);

        submitButton = (Button) findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultsIntent = new Intent(getApplicationContext(), ResultsActivity.class);
                Bundle extras = new Bundle();
                extras.putParcelableArrayList("questionArrayList", questionArrayList);
                extras.putParcelableArrayList("responseArrayList", responseArrayList);
                startActivity(resultsIntent, extras);
            }
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        finish();
//    }
}
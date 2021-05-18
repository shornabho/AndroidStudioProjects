package com.mcs.android.testio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mcs.android.testio.models.Question;
import com.mcs.android.testio.models.Response;

import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    private List<Question> questionArrayList;
    private List<Response> responseArrayList;

    private TextView tvCorrectAnswers, tvWrongAnswers, tvMarksScored, tvTotalMarks;
    private Button doneButton;

    private int registerNumber = 20;

    private SharedPreferences sharedPreferences;

    public static final int MARKS_PER_QUESTION = 5;

    private int correctAnswers;
    private int wrongAnswers;
    private int totalQuestions;
    private int totalScore;
    private int totalMarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Log.i("ActivityLaunch", "ResultsActivity launched");

        sharedPreferences = getSharedPreferences("com.mcs.android.testio", MODE_PRIVATE);

        questionArrayList = getIntent().getParcelableArrayListExtra("questionArrayList");
        responseArrayList = getIntent().getParcelableArrayListExtra("responseArrayList");

        calculateResults();
        storeInSharedPreferences();

        tvCorrectAnswers = (TextView) findViewById(R.id.tvCorrectAnswers);
        tvWrongAnswers = (TextView) findViewById(R.id.tvWrongAnswers);
        tvMarksScored = (TextView) findViewById(R.id.tvMarksScored);
        tvTotalMarks = (TextView) findViewById(R.id.tvTotalMarks);

        tvCorrectAnswers.setText(correctAnswers);
        tvWrongAnswers.setText(wrongAnswers);
        tvMarksScored.setText(sharedPreferences.getInt("Score_" + registerNumber, 0));
        tvTotalMarks.setText(totalMarks);

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ResultsActivity.this, "Finishing test...", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void calculateResults() {
        totalQuestions = questionArrayList.size();
        totalMarks = totalQuestions * MARKS_PER_QUESTION;

        for (int i = 0; i < totalQuestions; i++) {
            Question currentQuestion = questionArrayList.get(i);
            Response currentResponse = responseArrayList.get(i);

            if (currentQuestion.getCorrectAnswer().equals(currentResponse.getAnswer())) {
                // Answer is correct
                correctAnswers++;
            }
            else {
                wrongAnswers++;
            }
        }

        totalScore = correctAnswers * MARKS_PER_QUESTION;
    }

    private void storeInSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Score_" + registerNumber, totalScore);
        editor.apply();
    }
}
package com.mcs.android.cricketscorecard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.mcs.android.cricketscorecard.constants.DBConstants;
import com.mcs.android.cricketscorecard.helpers.DBHelper;
import com.mcs.android.cricketscorecard.models.Ball;
import com.mcs.android.cricketscorecard.models.Match;

import java.util.List;

public class MatchSummaryActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    private List<Ball> ballsList;

    private int matchId;
    private Match currentMatch;
    private TextView tvMatchName;
    private TextView tvMatchId;
    private TextView tvMatchSummary;
    private TextView tvMatchScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_summary);

        matchId = getIntent().getExtras().getInt("matchId");

        dbHelper = new DBHelper(getApplicationContext());

        currentMatch = dbHelper.getMatchById(matchId);

        tvMatchId = findViewById(R.id.tvMatchId);
        tvMatchName = findViewById(R.id.tvMatchName);
        tvMatchSummary = findViewById(R.id.tvMatchSummary);
        tvMatchScore = findViewById(R.id.tvMatchScore);

        tvMatchId.setText("Match ID: " + currentMatch.getMatchId());
        tvMatchName.setText(currentMatch.getMatchName());

        ballsList = dbHelper.getAllBallsInMatch(matchId);

        String matchSummary = "";

        int totalRuns = 0, totalWickets = 0, totalBalls = 0;
        int ballCounter = 0;

        for (Ball ball : ballsList) {
            String ballSummary = "";

            totalRuns += ball.getRuns();
            totalWickets += ball.getBallType() == DBConstants.BALL_TYPE_OUT ? 1 : 0;
            totalBalls += (ball.getBallType().equals(DBConstants.BALL_TYPE_NO_BALL) || ball.getBallType().equals(DBConstants.BALL_TYPE_WIDE_BALL) || ball.getBallType().equals(DBConstants.BALL_TYPE_EXTRAS)) ? 0 : 1;

            ballSummary += "Ball " + (++ballCounter) + ", ";
            ballSummary += ball.getBallType() + ", ";
            ballSummary += "Runs: " + ball.getRuns();

            matchSummary += ballSummary + "\n";
        }

        tvMatchSummary.setText(matchSummary);
        tvMatchScore.setText(totalRuns + "/" + totalWickets + " in " + (totalBalls / 6) + "." + (totalBalls % 6));
    }

}
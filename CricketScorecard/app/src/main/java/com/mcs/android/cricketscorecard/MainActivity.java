package com.mcs.android.cricketscorecard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mcs.android.cricketscorecard.constants.DBConstants;
import com.mcs.android.cricketscorecard.helpers.DBHelper;
import com.mcs.android.cricketscorecard.models.Ball;
import com.mcs.android.cricketscorecard.models.Match;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DBHelper dbHelper;
    private TextView tvOvers, tvRuns, tvWickets;
    private Button btnRuns1, btnRuns2, btnRuns3, btnRuns4, btnRuns6;
    private Button btnWideBall, btnNoBall, btnOut;
    private EditText etExtras;
    private ImageButton ibAddExtras;
    private Button btnDeleteData, btnNewMatch, btnGetMatchSummaries;

    private Match activeMatch;

    private int runs, balls, wickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(getApplicationContext());

        tvOvers = findViewById(R.id.tvOvers);
        tvRuns = findViewById(R.id.tvRuns);
        tvWickets = findViewById(R.id.tvWickets);
        btnRuns1 = findViewById(R.id.btnRuns1);
        btnRuns2 = findViewById(R.id.btnRuns2);
        btnRuns3 = findViewById(R.id.btnRuns3);
        btnRuns4 = findViewById(R.id.btnRuns4);
        btnRuns6 = findViewById(R.id.btnRuns6);
        btnWideBall = findViewById(R.id.btnWideBall);
        btnNoBall = findViewById(R.id.btnNoBall);
        btnOut = findViewById(R.id.btnOut);
        etExtras = findViewById(R.id.etExtras);
        ibAddExtras = findViewById(R.id.ibAddExtras);
        btnDeleteData = findViewById(R.id.btnDeleteData);
        btnNewMatch = findViewById(R.id.btnNewMatch);
        btnGetMatchSummaries = findViewById(R.id.btnGetMatchSummaries);

        btnRuns1.setOnClickListener(this);
        btnRuns2.setOnClickListener(this);
        btnRuns3.setOnClickListener(this);
        btnRuns4.setOnClickListener(this);
        btnRuns6.setOnClickListener(this);
        btnWideBall.setOnClickListener(this);
        btnNoBall.setOnClickListener(this);
        btnOut.setOnClickListener(this);
        ibAddExtras.setOnClickListener(this);
        btnDeleteData.setOnClickListener(this);
        btnNewMatch.setOnClickListener(this);
        btnGetMatchSummaries.setOnClickListener(this);

        resetScores();
        setButtonsEnabled(false);
    }

    private void setButtonsEnabled(boolean status) {
        btnRuns1.setEnabled(status);
        btnRuns2.setEnabled(status);
        btnRuns3.setEnabled(status);
        btnRuns4.setEnabled(status);
        btnRuns6.setEnabled(status);
        btnWideBall.setEnabled(status);
        btnNoBall.setEnabled(status);
        btnOut.setEnabled(status);
        ibAddExtras.setEnabled(status);
        btnDeleteData.setEnabled(status);
    }

    private void resetScores() {
        tvRuns.setText("0");
        tvWickets.setText("0");
        tvOvers.setText("0.0");

        balls = 0;
        runs = 0;
        wickets = 0;
    }

    private void updateScores(int balls, int runs, int wickets) {
        this.balls += balls;
        this.runs += runs;
        this.wickets += wickets;

        tvOvers.setText(this.balls/6 + "." + this.balls % 6);
        tvRuns.setText(String.valueOf(this.runs));
        tvWickets.setText(String.valueOf(this.wickets));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNewMatch:
                // Set an EditText view to get user input
                final EditText input = new EditText(this);

                new AlertDialog.Builder(this)
                        .setTitle("Start a new match")
                        .setMessage("Enter the match name")
                        .setView(input)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                String matchName = input.getText().toString().trim();
                                if (!matchName.isEmpty())
                                {
                                    Match match = new Match(matchName);
                                    int id = dbHelper.addMatch(match);
                                    match = dbHelper.getMatchById(id);
                                    Log.i("CreateMatch", "matchId: " + match.getMatchId() + " matchName: " + match.getMatchName() + " matchCreatedAt: " + match.getCreatedAt());
                                    activeMatch = match;
                                }
                                setButtonsEnabled(true);
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                    // Do nothing.

                                }
                }).show();
                break;

            case R.id.btnDeleteData:
                dbHelper.deleteMatchById(activeMatch.getMatchId());
                resetScores();
                setButtonsEnabled(false);
                break;

            case R.id.btnRuns1:
                dbHelper.addBall(new Ball(activeMatch.getMatchId(), DBConstants.BALL_TYPE_SINGLES, 1));
                updateScores(1, 1, 0);
                break;
            case R.id.btnRuns2:
                dbHelper.addBall(new Ball(activeMatch.getMatchId(), DBConstants.BALL_TYPE_DOUBLES, 2));
                updateScores(1, 2, 0);
                break;
            case R.id.btnRuns3:
                dbHelper.addBall(new Ball(activeMatch.getMatchId(), DBConstants.BALL_TYPE_TRIPLES, 3));
                updateScores(1, 3, 0);
                break;
            case R.id.btnRuns4:
                dbHelper.addBall(new Ball(activeMatch.getMatchId(), DBConstants.BALL_TYPE_BOUNDARY, 4));
                updateScores(1, 4, 0);
                break;
            case R.id.btnRuns6:
                dbHelper.addBall(new Ball(activeMatch.getMatchId(), DBConstants.BALL_TYPE_BOUNDARY, 6));
                updateScores(1, 6, 0);
                break;
            case R.id.btnWideBall:
                dbHelper.addBall(new Ball(activeMatch.getMatchId(), DBConstants.BALL_TYPE_WIDE_BALL, 1));
                updateScores(0, 1, 0);
                break;
            case R.id.btnNoBall:
                dbHelper.addBall(new Ball(activeMatch.getMatchId(), DBConstants.BALL_TYPE_NO_BALL, 1));
                updateScores(0, 1, 0);
                break;
            case R.id.btnOut:
                dbHelper.addBall(new Ball(activeMatch.getMatchId(), DBConstants.BALL_TYPE_OUT, 0));
                updateScores(1, 0, 1);
                break;
            case R.id.ibAddExtras:
                int extrasRuns = Integer.parseInt(etExtras.getText().toString().trim());
                dbHelper.addBall(new Ball(activeMatch.getMatchId(), DBConstants.BALL_TYPE_EXTRAS, extrasRuns));
                updateScores(0, extrasRuns, 0);
                etExtras.setText("");
                break;

            case R.id.btnGetMatchSummaries:
                setButtonsEnabled(false);
                resetScores();
                Intent matchSummariesActivityIntent = new Intent(getApplicationContext(), MatchesActivity.class);
                startActivity(matchSummariesActivityIntent);
                break;

        }
    }
}
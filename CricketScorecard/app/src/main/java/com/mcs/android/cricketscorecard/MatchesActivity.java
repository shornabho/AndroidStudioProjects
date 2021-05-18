package com.mcs.android.cricketscorecard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mcs.android.cricketscorecard.adapters.MatchesRecyclerViewAdapter;
import com.mcs.android.cricketscorecard.helpers.DBHelper;
import com.mcs.android.cricketscorecard.models.Match;

import java.util.List;

public class MatchesActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private RecyclerView rvMatches;
    private MatchesRecyclerViewAdapter matchesRecyclerViewAdapter;
    private List<Match> matchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        dbHelper = new DBHelper(getApplicationContext());

        rvMatches = findViewById(R.id.rvMatches);
        rvMatches.setLayoutManager(new LinearLayoutManager(this));

        matchList = dbHelper.getAllMatches();

        matchesRecyclerViewAdapter = new MatchesRecyclerViewAdapter(this, matchList);
        rvMatches.setAdapter(matchesRecyclerViewAdapter);
    }
}
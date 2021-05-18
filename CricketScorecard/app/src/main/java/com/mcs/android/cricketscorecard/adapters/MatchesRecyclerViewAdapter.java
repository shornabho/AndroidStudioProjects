package com.mcs.android.cricketscorecard.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcs.android.cricketscorecard.MatchSummaryActivity;
import com.mcs.android.cricketscorecard.R;
import com.mcs.android.cricketscorecard.models.Match;

import java.util.List;

public class MatchesRecyclerViewAdapter extends RecyclerView.Adapter<MatchesRecyclerViewAdapter.ViewHolder> {

    private Activity activity;
    private List<Match> matchList;

    public MatchesRecyclerViewAdapter(Activity activity, List<Match> matchList) {
        this.activity = activity;
        this.matchList = matchList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Match match = matchList.get(position);

        holder.tvMatchItem.setText(match.getMatchName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent matchSummaryIntent = new Intent(activity, MatchSummaryActivity.class);
                matchSummaryIntent.putExtra("matchId", match.getMatchId());
                activity.startActivity(matchSummaryIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMatchItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMatchItem = itemView.findViewById(R.id.tvMatchItem);
        }
    }
}

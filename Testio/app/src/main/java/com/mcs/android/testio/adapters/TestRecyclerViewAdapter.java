package com.mcs.android.testio.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcs.android.testio.R;
import com.mcs.android.testio.TestActivity;
import com.mcs.android.testio.models.Test;

import java.util.ArrayList;

public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.ViewHolder> {

    private Activity activity;
    private ArrayList<Test> testArrayList;

    public TestRecyclerViewAdapter(Activity activity, ArrayList<Test> testArrayList) {
        this.activity = activity;
        this.testArrayList = testArrayList;
    }

    @NonNull
    @Override
    public TestRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestRecyclerViewAdapter.ViewHolder holder, int position) {
        Test currentTest = testArrayList.get(position);

        holder.tvTestTitle.setText(currentTest.getTitle());
        holder.tvCreator.setText(currentTest.getCreator());
        holder.tvCreatedAt.setText(currentTest.getCreatedAt().toString());
    }

    @Override
    public int getItemCount() {
        return testArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTestTitle;
        private TextView tvCreator;
        private TextView tvCreatedAt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTestTitle = (TextView) itemView.findViewById(R.id.tvTestTitle);
            tvCreator = (TextView) itemView.findViewById(R.id.tvCreator);
            tvCreatedAt = (TextView) itemView.findViewById(R.id.tvCreatedAt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent startTestIntent = new Intent(activity.getApplicationContext(), TestActivity.class);
//                    // Add bundles here
                    activity.startActivity(startTestIntent);
                }
            });
        }
    }
}

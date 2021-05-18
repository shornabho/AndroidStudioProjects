package com.mcs.android.asynctask.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcs.android.asynctask.R;
import com.mcs.android.asynctask.models.Comment;

import java.util.ArrayList;

public class CommentsRecyclerViewAdapter extends RecyclerView.Adapter<CommentsRecyclerViewAdapter.ViewHolder> {

    private Activity activity;
    private ArrayList<Comment> commentsList;

    public CommentsRecyclerViewAdapter(Activity activity, ArrayList<Comment> commentsList) {
        this.activity = activity;
        this.commentsList = commentsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = commentsList.get(position);

        holder.commentAuthorName.setText(comment.getAuthorName());
        holder.commentAuthorEmail.setText(comment.getAuthorEmail());
        holder.commentBody.setText(comment.getBody());
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView commentAuthorName;
        private TextView commentAuthorEmail;
        private TextView commentBody;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            commentAuthorName = (TextView) itemView.findViewById(R.id.commentAuthorName);
            commentAuthorEmail = (TextView) itemView.findViewById(R.id.commentAuthorEmail);
            commentBody = (TextView) itemView.findViewById(R.id.commentBody);
        }
    }
}

package com.mcs.android.asynctask.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcs.android.asynctask.PostActivity;
import com.mcs.android.asynctask.R;
import com.mcs.android.asynctask.models.Post;

import java.util.ArrayList;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private Activity activity;
    private ArrayList<Post> postsList;

    public PostsRecyclerViewAdapter(Activity activity, ArrayList<Post> postsList) {
        this.activity = activity;
        this.postsList = postsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
            return new PostViewHolder(view);
        }
        else
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PostViewHolder) {
            populateItemRows((PostViewHolder) holder, position);
        }
        else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return postsList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView postTitle;
        private TextView postAuthor;
        private TextView postBody;
        public View itemView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            postTitle = (TextView) itemView.findViewById(R.id.postTitle);
            postAuthor = (TextView) itemView.findViewById(R.id.postAuthor);
            postBody = (TextView) itemView.findViewById(R.id.postBody);

            this.itemView = itemView;
        }
    }

    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder holder, int position) {
        // Progress bar will be displayed
    }

    private void populateItemRows(PostViewHolder holder, int position) {
        Post post = postsList.get(position);

        holder.postTitle.setText(post.getTitle());
        holder.postAuthor.setText("User " + post.getUserId());
        holder.postBody.setText(post.getBody());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postIntent = new Intent(activity.getApplicationContext(), PostActivity.class);
                postIntent.putExtra("post", post);

                activity.startActivity(postIntent);
            }
        });
    }
}

package com.mcs.android.firebasechat.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcs.android.firebasechat.ChatActivity;
import com.mcs.android.firebasechat.R;
import com.mcs.android.firebasechat.models.User;

import java.util.List;

public class AllChatsRecyclerViewAdapter extends RecyclerView.Adapter<AllChatsRecyclerViewAdapter.ViewHolder> {

    private Activity activity;
    private List<User> usersList;

    public AllChatsRecyclerViewAdapter(Activity activity, List<User> usersList) {
        this.activity = activity;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public AllChatsRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllChatsRecyclerViewAdapter.ViewHolder holder, int position) {
        User user = usersList.get(position);

        holder.tvDisplayName.setText(user.getName());
        holder.tvEmail.setText(user.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startChatIntent = new Intent(activity, ChatActivity.class);
                startChatIntent.putExtra("user", user);
                activity.startActivity(startChatIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDisplayName;
        private TextView tvEmail;
        private View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.itemView = itemView;
            tvDisplayName = itemView.findViewById(R.id.tvDisplayName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}

package com.mcs.android.firebasechat.adapters;

import android.app.Activity;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcs.android.firebasechat.R;
import com.mcs.android.firebasechat.models.ChatMessage;
import com.mcs.android.firebasechat.models.User;

import java.util.Date;
import java.util.List;

public class ChatMessageRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Activity activity;
    private List<ChatMessage> chatMessageList;
    private String currentUserUid;

    public static final int VIEW_TYPE_SENT_MESSAGE = 0;
    public static final int VIEW_TYPE_RECEIVED_MESSAGE = 1;

    public ChatMessageRecyclerViewAdapter(Activity activity, List<ChatMessage> chatMessageList, String currentUserUid) {
        this.activity = activity;
        this.chatMessageList = chatMessageList;
        this.currentUserUid = currentUserUid;
    }

    @Override
    public int getItemViewType(int position) {
        return chatMessageList.get(position).getSenderUid().equals(currentUserUid) ? VIEW_TYPE_SENT_MESSAGE : VIEW_TYPE_RECEIVED_MESSAGE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT_MESSAGE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sent_message_item, parent, false);
            return new SentViewHolder(view);
        }
        else if (viewType == VIEW_TYPE_RECEIVED_MESSAGE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.received_message_item, parent, false);
            return new ReceivedViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessageList.get(position);
        String messageTime = "";

//        String messageTime = DateUtils.formatSameDayTime(, (new Date()).getTime(), DateUtils.FORMAT_NUMERIC_DATE, DateUtils.FORMAT_).toString();

        if (DateUtils.isToday(chatMessage.getSentAt().getTime())) {
            messageTime = DateUtils.formatDateTime(activity.getApplicationContext(), chatMessage.getSentAt().getTime(), DateUtils.FORMAT_SHOW_TIME);
            Log.i("isToday", "Called");
        }
        else {
            messageTime = DateUtils.formatDateTime(activity.getApplicationContext(), chatMessage.getSentAt().getTime(), DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE |
                    DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_ABBREV_ALL);
        }
        if (holder instanceof SentViewHolder) {
            ((SentViewHolder) holder).tvSentMessage.setText(chatMessage.getBody());
            ((SentViewHolder) holder).tvSentAt.setText(messageTime);
        }
        else {
            ((ReceivedViewHolder) holder).tvReceivedMessage.setText(chatMessage.getBody());
            ((ReceivedViewHolder) holder).tvReceivedAt.setText(messageTime);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessageList.size();
    }

    public class SentViewHolder extends RecyclerView.ViewHolder {
        private TextView tvSentMessage;
        private TextView tvSentAt;

        public SentViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSentMessage = itemView.findViewById(R.id.tvSentMessage);
            tvSentAt = itemView.findViewById(R.id.tvSentAt);
        }
    }

    public class ReceivedViewHolder extends RecyclerView.ViewHolder {

        private TextView tvReceivedMessage;
        private TextView tvReceivedAt;

        public ReceivedViewHolder(@NonNull View itemView) {
            super(itemView);

            tvReceivedMessage = itemView.findViewById(R.id.tvReceivedMessage);
            tvReceivedAt = itemView.findViewById(R.id.tvReceivedAt);
        }
    }
}

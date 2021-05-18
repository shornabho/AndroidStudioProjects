package com.mcs.android.firebasechat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mcs.android.firebasechat.adapters.ChatMessageRecyclerViewAdapter;
import com.mcs.android.firebasechat.models.ChatMessage;
import com.mcs.android.firebasechat.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private TextView tvChatName;
    private EditText etMessage;
    private ImageButton ibSend;
    private RecyclerView rvChatMessages;
    private ChatMessageRecyclerViewAdapter chatMessageRecyclerViewAdapter;

    private DatabaseReference databaseRef;
    private DatabaseReference chatsRef;

    private FirebaseUser currentUser;
    private String currentUserUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        User otherUser = getIntent().getExtras().getParcelable("user");

        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseRef = FirebaseDatabase.getInstance().getReference();

        currentUserUid = currentUser.getUid();

        // Find which uid is smaller and which is larger
        String smallerUid = currentUserUid;
        String largerUid = otherUser.getUid();

        if (smallerUid.compareTo(largerUid) > 0) {
            String temp = smallerUid;
            smallerUid = largerUid;
            largerUid = temp;
        }

        currentUser.getUid().compareTo(otherUser.getUid());
        chatsRef = databaseRef.child("chats").child(smallerUid + "_" + largerUid);

        tvChatName = findViewById(R.id.tvChatName);
        etMessage = findViewById(R.id.etMessage);
        ibSend = findViewById(R.id.ibSend);
        rvChatMessages = findViewById(R.id.rvChatMessages);

        tvChatName.setText(otherUser.getName());

        ibSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = etMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    // If message is not empty
                    String id = chatsRef.push().getKey();
                    ChatMessage chatMessage = new ChatMessage(id, currentUser.getUid(), message, new Date());
                    chatsRef.child(id).setValue(chatMessage);
                    etMessage.setText("");
                }
            }
        });

        rvChatMessages.setLayoutManager(new LinearLayoutManager(this));

        List<ChatMessage> chatMessageList = new ArrayList<ChatMessage>();
        chatMessageRecyclerViewAdapter = new ChatMessageRecyclerViewAdapter(this, chatMessageList, currentUserUid);
        rvChatMessages.setAdapter(chatMessageRecyclerViewAdapter);

        chatsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatMessageList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    chatMessageList.add(dataSnapshot.getValue(ChatMessage.class));
                }
                chatMessageRecyclerViewAdapter.notifyDataSetChanged();
                rvChatMessages.scrollToPosition(chatMessageList.size() - 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        etMessage.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!chatMessageList.isEmpty()) {
                    rvChatMessages.scrollToPosition(chatMessageList.size() - 1);
                    rvChatMessages.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            rvChatMessages.smoothScrollToPosition(chatMessageList.size() - 1);
                        }
                    }, 500);
                }
            }
        });
    }
}
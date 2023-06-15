package com.kis.mymessangerjava.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kis.mymessangerjava.Pojo.Message;
import com.kis.mymessangerjava.R;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private String CURRENT_USER_ID = "currentUser";
    private String OTHER_USER_ID = "otherUser";

    TextView title_tv;
    View status_view;
    RecyclerView chat_rv;
    EditText edit_mes_tv;
    ImageView imageView;

    private String currentUserId;
    private String otherUserId;
    private MessagesAdapter messagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initViews();

        currentUserId = getIntent().getStringExtra(CURRENT_USER_ID);
        otherUserId = getIntent().getStringExtra(OTHER_USER_ID);

        messagesAdapter = new MessagesAdapter(currentUserId);
        chat_rv.setAdapter(messagesAdapter);
        List<Message> messageList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Message message = new Message(
                    "Text" + i,
                    currentUserId,
                    otherUserId
            );
            messageList.add(message);
        }

        for (int i = 0; i < 10; i++) {
            Message message = new Message(
                    "Text" + i,
                    otherUserId,
                    currentUserId
            );
            messageList.add(message);
        }
        messagesAdapter.setMessages(messageList);

    }


    private void initViews() {
        title_tv = findViewById(R.id.title_tv);
        status_view = findViewById(R.id.status_view);
        chat_rv = findViewById(R.id.chat_rv);
        edit_mes_tv = findViewById(R.id.edit_mes_tv);
        imageView = findViewById(R.id.imageView);
    }

    public Intent newIntent(Context context, String currentUserId, String otherUserId) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(CURRENT_USER_ID, currentUserId);
        intent.putExtra(OTHER_USER_ID, otherUserId);
        return intent;
    }

}
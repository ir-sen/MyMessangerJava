package com.kis.mymessangerjava.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kis.mymessangerjava.Pojo.Message;
import com.kis.mymessangerjava.Pojo.User;
import com.kis.mymessangerjava.R;
import com.kis.mymessangerjava.view_models.ChatViewModel;
import com.kis.mymessangerjava.view_models.ChatViewModelFactory;

import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private String CURRENT_USER_ID = "currentUser";
    private String OTHER_USER_ID = "otherUser";

    TextView title_tv;
    View status_view;
    RecyclerView chat_rv;
    EditText edit_mes_tv;
    ImageView send_img;

    private String currentUserId;
    private String otherUserId;
    private MessagesAdapter messagesAdapter;

    private ChatViewModel viewModel;
    private ChatViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initViews();

        currentUserId = getIntent().getStringExtra(CURRENT_USER_ID);
        otherUserId = getIntent().getStringExtra(OTHER_USER_ID);

        viewModelFactory = new ChatViewModelFactory(currentUserId, otherUserId);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(ChatViewModel.class);

        messagesAdapter = new MessagesAdapter(currentUserId);
        chat_rv.setAdapter(messagesAdapter);

        observeViewMdel();
        send_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message message = new Message(
                        edit_mes_tv.getText().toString().trim(),
                        currentUserId,
                        otherUserId
                );
                viewModel.sendMessanges(message);
            }
        });
//        List<Message> messageList = new ArrayList<>();
//
//        for (int i = 0; i < 10; i++) {
//            Message message = new Message(
//                    "Text" + i,
//                    currentUserId,
//                    otherUserId
//            );
//            messageList.add(message);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            Message message = new Message(
//                    "Text" + i,
//                    otherUserId,
//                    currentUserId
//            );
//            messageList.add(message);
//        }

    }

    private void observeViewMdel() {
        viewModel.getMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                messagesAdapter.setMessages(messages);
            }
        });

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if (errorMessage != null) {
                    Toast.makeText(ChatActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getMessageSend().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean send) {
                if (send) {
                    edit_mes_tv.setText("");
                }
            }
        });
        viewModel.getOtherUsers().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                String userInfo = String.format("%s, %s", user.getName(), user.getLastName());
                title_tv.setText(userInfo);
            }
        });
    }


    private void initViews() {
        title_tv = findViewById(R.id.title_tv);
        status_view = findViewById(R.id.status_view);
        chat_rv = findViewById(R.id.chat_rv);
        edit_mes_tv = findViewById(R.id.edit_mes_tv);
        send_img = findViewById(R.id.send_img);
    }

    public Intent newIntent(Context context, String currentUserId, String otherUserId) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(CURRENT_USER_ID, currentUserId);
        intent.putExtra(OTHER_USER_ID, otherUserId);
        return intent;
    }

}
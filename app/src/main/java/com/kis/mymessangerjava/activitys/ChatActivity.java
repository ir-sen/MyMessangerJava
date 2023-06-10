package com.kis.mymessangerjava.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kis.mymessangerjava.R;

public class ChatActivity extends AppCompatActivity {

    TextView title_tv;
    View status_view;
    RecyclerView chat_rv;
    EditText edit_mes_tv;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initViews();
    }


    private void initViews() {
        title_tv = findViewById(R.id.title_tv);
        status_view = findViewById(R.id.status_view);
        chat_rv = findViewById(R.id.chat_rv);
        edit_mes_tv = findViewById(R.id.edit_mes_tv);
        imageView = findViewById(R.id.imageView);
    }

}
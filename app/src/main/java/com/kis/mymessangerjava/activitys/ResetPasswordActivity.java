package com.kis.mymessangerjava.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.kis.mymessangerjava.R;

public class ResetPasswordActivity extends AppCompatActivity {


    static final String KEY_RESET_EMAIL = "resetEmail";
    private EditText emailEditText;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        initTools();
        String last_email = getIntent().getStringExtra(KEY_RESET_EMAIL);
        emailEditText.setText(last_email);
        sendBtn.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            // send to resed
        });
    }

    private void initTools() {
        emailEditText = findViewById(R.id.edit_email);
        sendBtn = findViewById(R.id.btn_send);
    }

    public static Intent newIntent(Context context, String email) {
        Intent intent = new Intent(context, ResetPasswordActivity.class);
        intent.putExtra(KEY_RESET_EMAIL, email);
        return intent;
    }
}
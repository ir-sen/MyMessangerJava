package com.kis.mymessangerjava.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kis.mymessangerjava.R;
import com.kis.mymessangerjava.view_models.ResetPasswordViewModel;

public class ResetPasswordActivity extends AppCompatActivity {


    static final String KEY_RESET_EMAIL = "resetEmail";
    private EditText emailEditText;
    private Button sendBtn;

    private ResetPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        viewModel = new ViewModelProvider(this).get(ResetPasswordViewModel.class);
        initTools();
        observeViewModel();
        String last_email = getIntent().getStringExtra(KEY_RESET_EMAIL);
        emailEditText.setText(last_email);
        sendBtn.setOnClickListener(view -> {
            String email = emailEditText.getText().toString().trim();
            // send to resed
            viewModel.resetPassword(email);
        });
    }

    private void observeViewModel() {
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    Toast.makeText(ResetPasswordActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getSuccess().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(ResetPasswordActivity.this,
                            "The reset link has successfully sent",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
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
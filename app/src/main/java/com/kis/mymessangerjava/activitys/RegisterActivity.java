package com.kis.mymessangerjava.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.kis.mymessangerjava.R;
import com.kis.mymessangerjava.databinding.ActivityRegisterBinding;
import com.kis.mymessangerjava.view_models.SingInViewModel;

public class RegisterActivity extends AppCompatActivity {

    private final String TAG = "RegisterActivityTAG";

    private Button registerBtn;
    private ActivityRegisterBinding binding;
    private SingInViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_register);
        viewModel = new ViewModelProvider(this).get(SingInViewModel.class);
        registerBtn = findViewById(R.id.registerAcceptBtn);
        registerBtn.setOnClickListener(v -> {
            String email = binding.emailRegTv.getText().toString();
            String password = binding.passwordRegTv.getText().toString();
            Log.d(TAG, "email " + email + "password " + password);
            registerFunction(
                    email,
                    password
            );
        });

    }

    private void registerFunction(String email, String password) {
        viewModel.singIn(email, password);

    }


    public Intent newIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }



}
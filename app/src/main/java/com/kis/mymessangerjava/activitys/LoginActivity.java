package com.kis.mymessangerjava.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.kis.mymessangerjava.Pojo.UserThis;
import com.kis.mymessangerjava.databinding.ActivityMainBinding;
import com.kis.mymessangerjava.view_models.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "mainActivityTag";

    private com.kis.mymessangerjava.databinding.ActivityMainBinding binding;

    private LoginViewModel viewModel;
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        // init view model
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        listenersClick();
        observeViewModel();

    }


    private void listenersClick() {
        UserThis user = new UserThis();

        binding.sendBtn.setOnClickListener( v -> {

            user.setName(binding.emailEt.getText().toString());
            user.setPassword(binding.passwordEt.getText().toString());
            viewModel.login(user.getName(), user.getPassword());

            Log.d(TAG, "This is name " + user.getName() + " password " + user.getPassword());
        });
        // register activity listener
        binding.registerBtn.setOnClickListener(v -> {
            startActivity(new RegisterActivity().newIntent(this));
        });
        // listener login activity
        binding.resetPassword.setOnClickListener(v -> {
            Intent intent = ResetPasswordActivity.newIntent(
                    LoginActivity.this,
                    binding.emailEt.getText().toString()
            );
            startActivity(intent);

        });
    }
    // observe changes in error
    private void observeViewModel() {
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String errorMessage) {
                if (errorMessage != null) {
                    Toast.makeText(LoginActivity.this,
                            errorMessage,
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    // observe listener user if he authorize
        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
//                if he authorize go to
                if (firebaseUser != null) {
                    Intent intent = new UsersActivity().newIntent(LoginActivity.this);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }






}
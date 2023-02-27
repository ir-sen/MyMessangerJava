package com.kis.mymessangerjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kis.mymessangerjava.Pojo.UserThis;
import com.kis.mymessangerjava.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "mainActivityTag";
    private FirebaseAuth mAuth;

    private ActivityMainBinding binding;


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

        FirebaseApp.initializeApp(getApplication());
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Log.d(TAG, "auth already");
        } else {
            Log.d(TAG, "not already");
        }

        UserThis user = new UserThis();

        binding.sendBtn.setOnClickListener( v -> {
            user.setName(binding.emailEt.getText().toString());
            user.setPassword(binding.passwordEt.getText().toString());
            Log.d(TAG, "This is name " + user.getName() + " password " + user.getPassword());
        });

        binding.registerBtn.setOnClickListener(v -> {
            startActivity(new RegisterActivity().newIntent(this));
        });



//        startActivity(new RegisterActivity().newIntent(this));
    }



    private void initAccaunt() {

    }


}
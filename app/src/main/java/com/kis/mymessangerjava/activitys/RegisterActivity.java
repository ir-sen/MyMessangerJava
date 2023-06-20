package com.kis.mymessangerjava.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.kis.mymessangerjava.R;
import com.kis.mymessangerjava.databinding.ActivityRegisterBinding;
import com.kis.mymessangerjava.view_models.RegistrationViewModel;
import com.kis.mymessangerjava.view_models.SingInViewModel;

public class RegisterActivity extends AppCompatActivity {

    private final String TAG = "RegisterActivityTAG";

    private Button registerBtn;


    private EditText email;
    private EditText password;
    private EditText name;
    private EditText lastName;
    private EditText ageEt;


    private RegistrationViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        viewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        registerBtn = findViewById(R.id.registerAcceptBtn);
        initViewElements();
        observeViewModel();
        registerBtn.setOnClickListener(v -> registerUser());

    }

    private void registerUser() {
        String emailW = email.getText().toString();
        String passwordW = password.getText().toString();
        String nameW = name.getText().toString();
        String lastNameW = lastName.getText().toString();
        String ageGet = ageEt.getText().toString().trim();
        if (!ageGet.equals("")) {
            int age = Integer.parseInt(ageGet);
            Log.d(TAG, "email " + email + "password " + password);
            viewModel.signUp(emailW, passwordW, nameW, lastNameW, age);
        }
    }

    private void initViewElements() {
        email = findViewById(R.id.emailRegTv);
        password = findViewById(R.id.passwordRegTv);
        name = findViewById(R.id.firstNameRegTv);
        lastName = findViewById(R.id.lastNameRegTv);
        ageEt = findViewById(R.id.ageRegTv);

    }

    private void observeViewModel() {
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s != null) {
                    Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    Intent intent = new UsersActivity().newIntent(RegisterActivity.this, firebaseUser.getUid());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }




    public Intent newIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }



}
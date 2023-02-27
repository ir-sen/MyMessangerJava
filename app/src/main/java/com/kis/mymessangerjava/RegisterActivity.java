package com.kis.mymessangerjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    private final String TAG = "ActivityResult";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }


    public Intent newIntent(Context context) {
        return new Intent(context, RegisterActivity.class);
    }



}
package com.kis.mymessangerjava.view_models;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SingInViewModel extends AndroidViewModel {


    public SingInViewModel(@NonNull Application application) {
        super(application);
    }

    private final String TAG = "mainActivityTag";
    private FirebaseAuth mAuth;



    public void singIn(String email, String password) {
        mAuth = FirebaseAuth.getInstance();

//        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
//
//            }
//        }).addOnFailureListener(Throwable::printStackTrace);
//        my account
//        irsen.kan@mail.com
//        234341
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser != null){
                    Log.d(TAG, "auth user autorization");
                } else {
                    Log.d(TAG, "current user null:");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, e.toString());
            }
        });
    }


}

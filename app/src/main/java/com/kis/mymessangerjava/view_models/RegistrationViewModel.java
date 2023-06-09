package com.kis.mymessangerjava.view_models;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kis.mymessangerjava.Pojo.User;

public class RegistrationViewModel extends ViewModel {

    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference usersReferences;

    private MutableLiveData<String> error = new MutableLiveData<>();

    public LiveData<String> getError() {
        return error;
    }

    private MutableLiveData<FirebaseUser> user = new MutableLiveData<>();

    public MutableLiveData<FirebaseUser> getUser() {
        return user;
    }

    public FirebaseAuth getAuthUser() {
        return auth;
    }


    public RegistrationViewModel() {

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        usersReferences = firebaseDatabase.getReference("users");


        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    user.setValue(firebaseAuth.getCurrentUser());
                }
            }
        });

    }

    public void signUp(
            String email,
            String password,
            String name,
            String lastName,
            int age
    ) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {
                error.setValue(e.getMessage());
            }
        })
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser firebaseUser = authResult.getUser();
                    if (firebaseUser == null) { return;}
                    User user = new User(
                            firebaseUser.getUid(),
                            name,
                            lastName,
                            age,
                            false
                            );
                    usersReferences.child(user.getId()).setValue(user);
            }
        });
    }
}

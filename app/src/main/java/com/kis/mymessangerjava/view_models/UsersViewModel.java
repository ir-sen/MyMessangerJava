package com.kis.mymessangerjava.view_models;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kis.mymessangerjava.Pojo.User;

import java.util.ArrayList;
import java.util.List;

public class UsersViewModel  extends ViewModel {

    private FirebaseAuth auth;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference usersReferences;

    private MutableLiveData<FirebaseUser> user = new MutableLiveData<>();

    private MutableLiveData<List<User>> users = new MutableLiveData<>();

    public UsersViewModel() {
        auth = FirebaseAuth.getInstance();

        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    user.setValue(firebaseAuth.getCurrentUser());
                }
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        usersReferences = firebaseDatabase.getReference("users");
        usersReferences.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FirebaseUser currentUser = auth.getCurrentUser();
                if (currentUser == null) {
                    return;
                }
                List<User> usersDB = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user == null) { return;}
                    if (!user.getId().equals(currentUser.getUid())) {
                        usersDB.add(user);
                    }
                }
                users.setValue(usersDB);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public LiveData<FirebaseUser> getUser() {
        return user;
    }

    public MutableLiveData<List<User>> getUsers() { return users; }

    public void logOut() {
        auth.signOut();
    }
}

package com.kis.mymessangerjava.view_models;

import static com.kis.mymessangerjava.Keys.KEY_REFERENCES_DB;

import android.util.Log;

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
import java.util.Objects;

public class UsersViewModel extends ViewModel {

    private FirebaseAuth auth;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference usersReferences;

    private MutableLiveData<FirebaseUser> userFirebase = new MutableLiveData<>();

    private MutableLiveData<List<User>> users = new MutableLiveData<>();

    public UsersViewModel() {
        auth = FirebaseAuth.getInstance();

        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    userFirebase.setValue(firebaseAuth.getCurrentUser());
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
                    if (user == null) {
                        return;
                    }

                    if (!Objects.equals(user.getId(), currentUser.getUid())) {
                        usersDB.add(user);
                    }

                    Log.d("getCurrentUser", currentUser.getUid() + "-- current user");
                    Log.d("getCurrentUser", user.getId() + "-- just user");
                }
                users.setValue(usersDB);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public LiveData<FirebaseUser> getUserFirebase() {
        return userFirebase;
    }

    public MutableLiveData<List<User>> getUsers() { return users; }

    public void logOut() {
        setUserOnline(false);
        auth.signOut();
    }

    public void setUserOnline(boolean isOnline) {
        FirebaseUser firebaseUser = auth.getCurrentUser();
        if (firebaseUser == null) {
            return;
        }
        usersReferences.child(firebaseUser.getUid()).child("isOnline").setValue(isOnline);
    }


}

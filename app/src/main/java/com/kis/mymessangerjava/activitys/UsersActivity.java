package com.kis.mymessangerjava.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseUser;
import com.kis.mymessangerjava.Pojo.User;
import com.kis.mymessangerjava.R;
import com.kis.mymessangerjava.view_models.UsersViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UsersActivity extends AppCompatActivity {

    private UsersViewModel viewModel;
    private RecyclerView recyclerViewUsers;
    private UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        viewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        observeViewModel();

        List<User> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            User user = new User(
                    "id" +i, "name" + i,
                    i + "lastName", i + "age", new Random().nextBoolean() );
            list.add(user);
        }
        Log.d("TAGLIST", list.toString());
        initViews();
        usersAdapter.setUsers(list);



    }

    private void initViews() {
        recyclerViewUsers = findViewById(R.id.userRecycleView);
        usersAdapter = new UsersAdapter();
        recyclerViewUsers.setAdapter(usersAdapter);
    }

    private void observeViewModel() {
        viewModel.getUser().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser == null) {
                    Intent intent = new LoginActivity().newIntent(UsersActivity.this);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item_logout) {
            viewModel.logOut();
        }
        return super.onOptionsItemSelected(item);
    }

    public Intent newIntent(Context context) {
        return new Intent(context, UsersActivity.class);
    }


}
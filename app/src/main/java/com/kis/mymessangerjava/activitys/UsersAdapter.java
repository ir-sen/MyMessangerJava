package com.kis.mymessangerjava.activitys;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.kis.mymessangerjava.Pojo.User;
import com.kis.mymessangerjava.R;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    public List<User> users = new ArrayList<>();

    private OnUserClickListener onUserClickListener;

    public void setOnUserClickListener(OnUserClickListener onUserClickListener) {
        this.onUserClickListener = onUserClickListener;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.user_item, parent, false
        );
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        String userInfo = String.format("%s %s %s",
                user.getName(), user.getLasetName(), user.getAge()
        );
        holder.textViewUserInfo.setText(userInfo);
        int bgResId;
        if (user.getIsOnline()) {
            bgResId = R.drawable.circie_green;
        } else {
            bgResId = R.drawable.circie_red;
        }
        Drawable backgroud = ContextCompat.getDrawable(holder.itemView.getContext(), bgResId);
        holder.viewOnlineStatus.setBackground(backgroud);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onUserClickListener != null) {
                    onUserClickListener.onUserClick(user);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    interface OnUserClickListener {
        void onUserClick(User user);
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUserInfo;
        View viewOnlineStatus;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUserInfo = itemView.findViewById(R.id.textViewUserInfo);
            viewOnlineStatus = itemView.findViewById(R.id.viewOnlineStatus);

        }
    }
}

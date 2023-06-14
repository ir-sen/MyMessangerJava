package com.kis.mymessangerjava.activitys;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kis.mymessangerjava.Pojo.Message;
import com.kis.mymessangerjava.R;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>{

    private List<Message> messages = new ArrayList<>();

    String currentUserId;

    public MessagesAdapter(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    private int MY_VIEW = 110;
    private int OTHER_VIEW = 112;

    public void setMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutResId;
        if (viewType == MY_VIEW) {
            layoutResId = R.layout.item_sms;
        } else {
            layoutResId = R.layout.other_item_sms;
        }
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(layoutResId, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.textViewMessage.setText(message.getText());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        if (message.getSenderId().equals(currentUserId)) {
            return MY_VIEW;
        } else {
            return OTHER_VIEW;
        }
    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewMessage;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.sms_tv);
        }

    }
}

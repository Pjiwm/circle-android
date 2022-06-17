package com.pedro.rtpstreamer.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pedro.rtpstreamer.R;

import java.util.List;

public class MessageListAdapter extends RecyclerView.Adapter {
    private static final int FOLLOWER_MESSAGE_SENT = 1;
    private static final int STREAMER_MESSAGE_SENT = 2;

    private Context mContext;
    private List<MessageClass> mMessageClassList;
    private AuthClass currentUser;
    public MessageListAdapter(Context mContext, List<MessageClass> mMessageClassList) {
        this.mContext = mContext;
        this.mMessageClassList = mMessageClassList;
    }

    @Override
    public int getItemCount() {
        return  mMessageClassList.size();
    }

    @Override
    public int getItemViewType(int position) {
        MessageClass message = (MessageClass) mMessageClassList.get(position);

        if (message.getUser().getPrivateKey().equals(currentUser.getPrivateKey())) {
            // If the current user is the sender of the message
            return FOLLOWER_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return STREAMER_MESSAGE_SENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == FOLLOWER_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_custom, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == STREAMER_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_custom, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MessageClass message = (MessageClass) mMessageClassList.get(position);

        switch (holder.getItemViewType()) {
            case FOLLOWER_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case STREAMER_MESSAGE_SENT:
                ((ReceivedMessageHolder) holder).bind(message);
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);

            messageText = (TextView) itemView.findViewById(R.id.send_text_message);
//            timeText = (TextView) itemView.findViewById(R.id.message);
        }

        void bind(MessageClass message) {
            messageText.setText(message.getMessage());

//            timeText.setText(message.getTimestamp().toString());
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.message);
//            timeText = (TextView) itemView.findViewById(R.id.message);
//            nameText = (TextView) itemView.findViewById(R.id.message);
        }

        void bind(MessageClass message) {
            messageText.setText(message.getMessage());

//            timeText.setText(message.getTimestamp().toString());
//            nameText.setText(message.getUser().getUsername());
        }
        }

}

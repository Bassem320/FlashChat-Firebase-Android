package com.bassemsaleh.flashchatnewfirebase;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ChatListAdapter extends BaseAdapter {
    private Activity mActivity;
    private DatabaseReference mDataReference;
    private String mDisplayName;
    private ArrayList<DataSnapshot> mSnapshotlist;
    private ChildEventListener mListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            mSnapshotlist.add(dataSnapshot);
            notifyDataSetChanged();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    public ChatListAdapter(Activity activity, DatabaseReference ref, String name){
        mActivity = activity;
        mDataReference = ref.child("messages");
        mDisplayName = name;
        mSnapshotlist = new ArrayList<>();
        mDataReference.addChildEventListener(mListener);
    }

    @Override
    public int getCount() {
        return mSnapshotlist.size();
    }

    @Override
    public InstantMessage getItem(int position) {
        return mSnapshotlist.get(position).getValue(InstantMessage.class);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            // create new message
            LayoutInflater inflater = mActivity.getLayoutInflater();
            convertView = inflater.inflate(R.layout.chat_msg_row,parent,false);
            //create new ViewHolder
            final ViewHolder holder = new ViewHolder();
            holder.authorName = convertView.findViewById(R.id.author);
            holder.body = convertView.findViewById(R.id.message);
            holder.params = (LinearLayout.LayoutParams) holder.authorName.getLayoutParams();
            //add ViewHolder to view
            convertView.setTag(holder);
        }
        // retrieve the message and attach to view
        InstantMessage message = (InstantMessage) getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.authorName.setText(message.getAuthor());
        holder.body.setText(message.getMessage());
        boolean isMe = message.getAuthor().equals(mDisplayName);
        setChatRowApperance(isMe,holder);
        return convertView;
    }

    private void setChatRowApperance(boolean isItMe, ViewHolder holder){
        if(isItMe){
            holder.params.gravity = Gravity.END;
            holder.authorName.setTextColor(Color.GREEN);
            holder.body.setTextColor(Color.GREEN);
            holder.body.setBackgroundResource(R.drawable.bubble2);
        }else{
            holder.params.gravity = Gravity.START;
            holder.authorName.setTextColor(Color.BLUE);
            holder.body.setTextColor(Color.BLUE);
            holder.body.setBackgroundResource(R.drawable.bubble1);
        }
        holder.authorName.setLayoutParams(holder.params);
        holder.body.setLayoutParams(holder.params);
    }

    public void cleanUp(){
        mDataReference.removeEventListener(mListener);
    }

    static class ViewHolder{
        TextView authorName;
        TextView body;
        LinearLayout.LayoutParams params;
    }

}

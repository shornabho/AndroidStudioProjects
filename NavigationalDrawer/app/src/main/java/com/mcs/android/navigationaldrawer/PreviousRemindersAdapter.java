package com.mcs.android.navigationaldrawer;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Date;

public class PreviousRemindersAdapter extends ArrayAdapter<String> {

    private Activity context;
    private String[] dateTimeObjects;
    private String[] messageObjects;

    public PreviousRemindersAdapter(Activity context, String[] dateTimeObjects, String[] messageObjects) {
        super(context, R.layout.list_previous_reminder, dateTimeObjects);

        this.context = context;
        this.dateTimeObjects = dateTimeObjects;
        this.messageObjects = messageObjects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.list_previous_reminder, null, true);

        TextView reminderTime = rowView.findViewById(R.id.textViewListReminderTime);
        TextView reminderMessage = rowView.findViewById(R.id.textViewListReminderMessage);

        reminderTime.setText(dateTimeObjects[position]);
        reminderMessage.setText(messageObjects[position]);

        return rowView;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return messageObjects[position] + " at " + dateTimeObjects[position];
    }
}

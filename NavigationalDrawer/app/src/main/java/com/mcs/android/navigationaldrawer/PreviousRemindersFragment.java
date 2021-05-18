package com.mcs.android.navigationaldrawer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class PreviousRemindersFragment extends Fragment {
    ListView previousRemindersList;

    String[] dateTimeObjects = {
            "00:00 March 30, 2021",
            "05:00 April 01, 2021",
            "09:30 April 02, 2021",
            "11:50 April 02, 2021",
            "04:00 April 03, 2021"
    };

    String[] messageObjects = {
            "Reminder 1",
            "Reminder 2",
            "Reminder 3",
            "Reminder 4",
            "Reminder 5"
    };

    public PreviousRemindersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_previous_reminders, container, false);

        PreviousRemindersAdapter previousRemindersAdapter = new PreviousRemindersAdapter(getActivity(), dateTimeObjects, messageObjects);
        previousRemindersList = rootView.findViewById(R.id.listViewPreviousReminders);
        previousRemindersList.setAdapter(previousRemindersAdapter);

        previousRemindersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getContext(), previousRemindersAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
package com.mcs.android.navigationaldrawer;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SetReminderFragment extends Fragment implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    EditText editTextChooseDateTime;
    EditText editTextMessage;
    Button btnSetReminder;

    int currentDay, currentMonth, currentYear, currentHour, currentMinute;
    int chosenDay, chosenMonth, chosenYear, chosenHour, chosenMinute;

    Date chosenTime;
    String reminderMessage;

    public SetReminderFragment() {
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
        return inflater.inflate(R.layout.fragment_set_reminder, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        editTextChooseDateTime = getActivity().findViewById(R.id.editTextChooseDateTime);
        editTextMessage = getActivity().findViewById(R.id.editTextMessage);
        btnSetReminder = getActivity().findViewById(R.id.btnSetReminder);

        editTextChooseDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                currentYear = calendar.get(Calendar.YEAR);
                currentMonth = calendar.get(Calendar.MONTH);
                currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), SetReminderFragment.this, currentYear, currentMonth, currentDay);
                datePickerDialog.show();

            }
        });

        btnSetReminder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                editTextMessage.clearFocus();
                reminderMessage = editTextMessage.getText().toString();

                if (chosenTime == null)
                    Toast.makeText(getContext(), "Please choose a time.", Toast.LENGTH_SHORT).show();
                else if (reminderMessage.equals(""))
                    Toast.makeText(getContext(), "Please enter a message.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(), "Reminder set to remind: " + reminderMessage + " at " + chosenTime.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        chosenYear = year;
        chosenMonth = month;
        chosenDay = dayOfMonth;
        Calendar calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR);
        currentMinute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), SetReminderFragment.this, currentHour, currentMinute, DateFormat.is24HourFormat(getContext()));
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        chosenHour = hourOfDay;
        chosenMinute = minute;

        GregorianCalendar gregorianCalendar = new GregorianCalendar(chosenYear, chosenMonth, chosenDay, chosenHour, chosenMinute);

        chosenTime = gregorianCalendar.getTime();

        editTextChooseDateTime.setText(chosenTime.toString());
    }
}
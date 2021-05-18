package com.mcs.android.chrizo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {
    private TextView textViewUserEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);

        Bundle bundle = getIntent().getExtras();

        String userEmail = bundle.get("email").toString();
        textViewUserEmail.setText(userEmail);

    }
}
package com.vogella.android.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class otherActivity extends AppCompatActivity {

    TextView otherActivityTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other1);
        otherActivityTextView = (TextView) findViewById(R.id.otherActivityTextView);

        Bundle bundle = getIntent().getExtras();
        String text = bundle.getString("KEY");

        otherActivityTextView.setText(text);

    }
}

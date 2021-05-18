package com.mcs.android.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowInfoActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView userEmailTextView;
    private TextView userCourseTextView;
    private TextView userRegnoTextView;

    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_info);

        Bundle bundle = getIntent().getExtras();

        userEmailTextView = (TextView) findViewById(R.id.userEmail);
        userCourseTextView = (TextView) findViewById(R.id.userCourse);
        userRegnoTextView = (TextView) findViewById(R.id.userRegno);
        backButton = (Button) findViewById(R.id.backButton);

        userEmailTextView.setText(bundle.getString("userEmail"));
        userCourseTextView.setText(bundle.getString("userCourse"));
        userRegnoTextView.setText(bundle.getString("userRegno"));

        backButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton:
                finish();
                break;
        }
    }
}
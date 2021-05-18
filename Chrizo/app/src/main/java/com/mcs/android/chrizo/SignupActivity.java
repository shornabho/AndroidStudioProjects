package com.mcs.android.chrizo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private Button signupButton;
    private TextView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupButton = (Button) findViewById(R.id.signupButton);
        goBack = (TextView) findViewById(R.id.goBack);

        signupButton.setOnClickListener(this);
        goBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signupButton:
                Toast.makeText(this, "Signed up successfully!", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.goBack:
                finish();
                break;

        }
    }
}
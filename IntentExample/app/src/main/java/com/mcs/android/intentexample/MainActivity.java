package com.mcs.android.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button loginButton;


    private static String[][] userDirectory = {
            {"swarnava.ghosh@cs.christuniversity.in", "swarnava", "2MCS", "2047126"},
            {"shreya.sharma@science.christuniversity.in", "shreya", "6CMS", "1740236"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                String inputEmail = editTextEmail.getText().toString();
                String inputPassword = editTextPassword.getText().toString();
                boolean loggedIn = false;

                for (String[] user : userDirectory) {
                    if (inputEmail.equals(user[0]) && inputPassword.equals(user[1])) {

                        Intent dashboardIntent = new Intent(this, DashboardActivity.class);

                        Bundle bundle = new Bundle();

                        bundle.putString("userEmail", user[0]);
                        bundle.putString("userPassword", user[1]);
                        bundle.putString("userCourse", user[2]);
                        bundle.putString("userRegno", user[3]);

                        dashboardIntent.putExtras(bundle);

                        startActivity(dashboardIntent);

                        editTextEmail.setText("");
                        editTextPassword.setText("");

                        loggedIn = true;
                    }
                }

                if (!loggedIn) {
                    Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
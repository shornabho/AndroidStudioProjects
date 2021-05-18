package com.mcs.android.chrizo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button loginButton;
    private TextView signupLink;

    // Dummy user database
    private static String[][] userDirectory = {
            {"swarnava98@gmail.com", "swarnava"},
            {"devyadav@gmail.com", "dev"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        loginButton = (Button) findViewById(R.id.loginButton);

        signupLink = (TextView) findViewById(R.id.signupLink);

        loginButton.setOnClickListener(this);
        signupLink.setOnClickListener(this);
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

                        bundle.putString("email", inputEmail);
                        bundle.putString("password", inputPassword);

                        dashboardIntent.putExtras(bundle);

                        startActivity(dashboardIntent);

                        loggedIn = true;
                    }
                }
                
                if (!loggedIn)
                    Toast.makeText(this, "Failed to login! Try again!", Toast.LENGTH_SHORT).show();


                break;
            case R.id.signupLink:

                Intent signupIntent = new Intent(this, SignupActivity.class);

                startActivity(signupIntent);

                break;
        }
    }
}
package com.mcs.android.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    private String userEmail;
    private String userPassword;

    private Button showInfoButton;
    private Button callOfficeButton;
    private Button checkWebsiteButton;
    private Button showLocationButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        showInfoButton = (Button) findViewById(R.id.showInfoButton);
        callOfficeButton = (Button) findViewById(R.id.callOfficeButton);
        checkWebsiteButton = (Button) findViewById(R.id.checkWebsiteButton);
        showLocationButton = (Button) findViewById(R.id.showLocationButton);

        logoutButton = (Button) findViewById(R.id.logoutButton);

        showInfoButton.setOnClickListener(this);
        callOfficeButton.setOnClickListener(this);
        checkWebsiteButton.setOnClickListener(this);
        showLocationButton.setOnClickListener(this);

        logoutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.showInfoButton:
                Bundle bundle = getIntent().getExtras();

                Intent showInfoIntent = new Intent(this, ShowInfoActivity.class);

                showInfoIntent.putExtras(bundle);

                startActivity(showInfoIntent);

                break;
            case R.id.callOfficeButton:
                Intent callOfficeIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:08040129600"));
                startActivity(callOfficeIntent);
                break;

            case R.id.checkWebsiteButton:
                Intent checkWebsiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.christuniversity.in/"));
                startActivity(checkWebsiteIntent);
                break;

            case R.id.showLocationButton:
                Intent showLocationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:12.9329857,77.6037331,15"));
                startActivity(showLocationIntent);
                break;

            case R.id.logoutButton:
                finish();
                break;
        }
    }
}
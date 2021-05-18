package com.mcs.android.cat2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class AboutUsActivity extends AppCompatActivity {
    DrawerLayout dl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        dl=findViewById(R.id.drawer_layout);
    }

    public void ClickMenu(View view)
    {
        MainActivity.openDrawer(dl);
    }
    public void ClickLogo(View view)
    {
        MainActivity.closeDrawer(dl);
    }
    public void ClickHome(View view){
        MainActivity.redirectActivity(this, MainActivity.class);
    }
    public void ClickDashboard(View view)
    {
        MainActivity.redirectActivity(this, ProfileActivity.class);
    }
    public void ClickAboutUs(View view)
    {
        MainActivity.redirectActivity(this, AboutUsActivity.class);
    }
    public void ClickLogout(View view)
    {
        MainActivity.logout(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.closeDrawer(dl);
    }
}
package com.mcs.android.navigationaldrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerToggle.getDrawerArrowDrawable().setColor(getColor(R.color.teal_700));
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SetReminderFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_set_reminder);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_set_reminder:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SetReminderFragment()).commit();
                break;
            case R.id.nav_previous_reminders:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PreviousRemindersFragment()).commit();
                break;
            case R.id.nav_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                break;
            case R.id.nav_open_calculatex:
                Intent launchCalculatexIntent = getPackageManager().getLaunchIntentForPackage("com.mcs.android.calculatex");
                if (launchCalculatexIntent != null) {
                    startActivity(launchCalculatexIntent);//null pointer check in case package name was not found
                }
                break;
            case R.id.nav_open_chrizo:
                Intent launchChrizoIntent = getPackageManager().getLaunchIntentForPackage("com.mcs.android.chrizo");
                if (launchChrizoIntent != null) {
                    startActivity(launchChrizoIntent);//null pointer check in case package name was not found
                }
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
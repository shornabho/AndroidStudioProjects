package com.mcs.android.testio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.mcs.android.testio.adapters.TestRecyclerViewAdapter;
import com.mcs.android.testio.models.Test;

import java.util.ArrayList;
import java.util.Date;

public class AllTestsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private RecyclerView testRecyclerView;
    private TestRecyclerViewAdapter testRecyclerViewAdapter;

    private ArrayList<Test> testArrayList;

    public static final String TAG = "Value";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tests);

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

        testRecyclerView = (RecyclerView) findViewById(R.id.testRecyclerView);
        testRecyclerView.setHasFixedSize(true);
        testRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        testArrayList = new ArrayList<Test>();
        testArrayList.add(new Test("01", "Test 1", "Dr Gobi R", new Date()));
        testArrayList.add(new Test("02", "Test 2", "Dr Vinay M", new Date()));
        testArrayList.add(new Test("03", "Test 3", "Dr Kirubanand VB", new Date()));

        testRecyclerViewAdapter = new TestRecyclerViewAdapter(this, testArrayList);
        testRecyclerView.setAdapter(testRecyclerViewAdapter);

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
            case R.id.nav_create_test:
                Intent createTestIntent = new Intent(getApplicationContext(), CreateTestActivity.class);
                startActivity(createTestIntent);
                break;
            case R.id.nav_take_test:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
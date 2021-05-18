package com.mcs.android.asynctask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    private Switch switchDarkMode;
    private Spinner spinnerTextSize;
    private String [] textSizes = { "Large", "Medium", "Small"};
    private SharedPreferences sharedPreferences;

    public static final int FONT_SIZE_LARGE = 16;
    public static final int FONT_SIZE_MEDIUM = 14;
    public static final int FONT_SIZE_SMALL = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences("com.mcs.android.asynctask.settings", MODE_PRIVATE);
        boolean darkMode = sharedPreferences.getBoolean("darkMode", false);
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Settings");
        }

        switchDarkMode = (Switch) findViewById(R.id.switchDarkMode);
        spinnerTextSize = (Spinner) findViewById(R.id.spinnerTextSize);

        switchDarkMode.setOnCheckedChangeListener(this);
        switchDarkMode.setChecked(darkMode);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, textSizes);
        spinnerTextSize.setAdapter(arrayAdapter);

        spinnerTextSize.setOnItemSelectedListener(this);
        int textSize = sharedPreferences.getInt("textSize", FONT_SIZE_MEDIUM);
        switch (textSize) {
            case FONT_SIZE_LARGE:
                spinnerTextSize.setSelection(0);
                break;
            case FONT_SIZE_MEDIUM:
                spinnerTextSize.setSelection(1);
                break;
            case FONT_SIZE_SMALL:
                spinnerTextSize.setSelection(2);
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        int textSize = FONT_SIZE_MEDIUM;
        switch (textSizes[position]) {
            case "Large":
                textSize = FONT_SIZE_LARGE;
                break;
            case "Medium":
                textSize = FONT_SIZE_MEDIUM;
                break;
            case "Small":
                textSize = FONT_SIZE_SMALL;
                break;
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("textSize", textSize);
        editor.apply();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean currentMode) {
        boolean defaultMode = sharedPreferences.getBoolean("darkMode", false);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("darkMode", currentMode);
        editor.apply();

        if (defaultMode != currentMode) {
            if (currentMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }
            recreate();
        }
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
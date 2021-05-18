package com.mcs.android.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView cardViewUtensils;
    CardView cardViewVehicles;
    CardView cardViewFurniture;
    CardView cardViewGroceries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardViewVehicles = (CardView) findViewById(R.id.cardViewVehicles);
        cardViewUtensils = (CardView) findViewById(R.id.cardViewUtensils);
        cardViewFurniture = (CardView) findViewById(R.id.cardViewFurniture);
        cardViewGroceries = (CardView) findViewById(R.id.cardViewGroceries);

        cardViewVehicles.setOnClickListener(this);
        cardViewUtensils.setOnClickListener(this);
        cardViewFurniture.setOnClickListener(this);
        cardViewGroceries.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        String category = "";
        switch (view.getId()) {
            case R.id.cardViewVehicles:
                category = "Vehicles";
                String[] vehicles = {"Mercedes", "BMW", "Audi"};
                bundle.putStringArray("items", vehicles);
                break;
            case R.id.cardViewUtensils:
                category = "Utensils";
                String[] utensils = {"Fork", "Spoon", "Plate", "Dish"};
                bundle.putStringArray("items", utensils);
                break;
            case R.id.cardViewFurniture:
                category = "Furniture";
                String[] furniture = {"Chair", "Table", "Rocking Chair", "Sofa"};
                bundle.putStringArray("items", furniture);
                break;
            case R.id.cardViewGroceries:
                category = "Groceries";
                String[] groceries = {"Potato", "Moong Dal", "Basmati Rice", "Paneer"};
                bundle.putStringArray("items", groceries);
                break;
        }
        bundle.putString("category", category);

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.rootLayout), category, Snackbar.LENGTH_LONG)
                .setAction("View", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Fragment fragment = new ItemsFragment();
                        fragment.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragmentItemsList, fragment, null);
                        fragmentTransaction.commit();
                    }
                });
        snackbar.show();
    }


}
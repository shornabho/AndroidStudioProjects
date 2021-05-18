package com.mcs.android.foodiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.mcs.android.foodiz.adapter.CheckoutRecyclerViewAdapter;
import com.mcs.android.foodiz.models.FoodItem;

public class CheckoutActivity extends AppCompatActivity {
    private RecyclerView checkoutRecyclerView;
    private CheckoutRecyclerViewAdapter checkoutRecyclerViewAdapter;
    private ImageButton actionBarCartButton;
    private ImageButton actionBarBackButton;
    private TextView checkoutTotalAmount;
    private Button buttonPayAndPlaceOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        actionBarCartButton = findViewById(R.id.actionBarCartButton);
        actionBarCartButton.setVisibility(View.INVISIBLE);

        actionBarBackButton = findViewById(R.id.actionBarBackButton);
        actionBarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        checkoutRecyclerView = findViewById(R.id.checkoutRecyclerView);
        checkoutRecyclerView.setHasFixedSize(true);
        checkoutRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        checkoutRecyclerViewAdapter = new CheckoutRecyclerViewAdapter(this);
        checkoutRecyclerView.setAdapter(checkoutRecyclerViewAdapter);

        checkoutTotalAmount = findViewById(R.id.checkoutTotalAmount);
        double totalAmount = 0.0;
        for (FoodItem foodItem : MainActivity.selectedFoodItems) {
            totalAmount += foodItem.getItemPrice();
        }
        checkoutTotalAmount.setText("₹ " + Double.toString(totalAmount));

        buttonPayAndPlaceOrder = findViewById(R.id.buttonPayAndPlaceOrder);
        if (totalAmount == 0) {
            buttonPayAndPlaceOrder.setEnabled(false);
        }
        else {
            buttonPayAndPlaceOrder.setEnabled(true);
        }

        buttonPayAndPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(findViewById(R.id.checkoutRootLayout), "Order placed!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Okay!", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });
                snackbar.setActionTextColor(getColor(R.color.light_pink));

                snackbar.show();
                MainActivity.clearSelectedFoodItems();

//                Toast.makeText(CheckoutActivity.this, "Order placed!", Toast.LENGTH_SHORT).show();
//                finish();
            }
        });
    }
}
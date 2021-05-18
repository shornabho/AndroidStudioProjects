package com.mcs.android.foodiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mcs.android.foodiz.adapter.MenuRecyclerViewAdapter;
import com.mcs.android.foodiz.models.FoodItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView actionBarTitle;
    private ImageButton actionBarBackButton;
    private ImageButton actionBarCartButton;
    private RecyclerView recyclerView;
    private MenuRecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<FoodItem> foodItemArrayList;
    public static ArrayList<FoodItem> selectedFoodItems = new ArrayList<FoodItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBarBackButton = findViewById(R.id.actionBarBackButton);
        actionBarTitle = findViewById(R.id.actionBarTitle);
        actionBarCartButton = findViewById(R.id.actionBarCartButton);

        actionBarBackButton.setVisibility(View.INVISIBLE);
        actionBarCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cartIntent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(cartIntent);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        foodItemArrayList = new ArrayList<FoodItem>();
        foodItemArrayList.add(new FoodItem("Butter Chicken (Boneless)", 120.00, "Rich and creamy blend of tomatoes, butter, curry and spices with a mild meat.", R.drawable.butter_chicken_boneless));
        foodItemArrayList.add(new FoodItem("Mutton Raan", 575.00, "Leg of Lamb Grilled to perfection.", R.drawable.mutton_raan));
        foodItemArrayList.add(new FoodItem("Chicken Masala Boneless", 145.00, "Chicken cooked in classic masala gravy.", R.drawable.chicken_masala_boneless));
        foodItemArrayList.add(new FoodItem("Chicken Tikka Masala", 145.00, "Smoky charcoal grilled chicken in a classic Tikka masala gravy.", R.drawable.chicken_tikka_masala));
        foodItemArrayList.add(new FoodItem("Chicken Kuruma Boneless", 145.00, "Classic chef's special gravy cooked to perfection.", R.drawable.chicken_kuruma_boneless));
        foodItemArrayList.add(new FoodItem("Pepper Chicken Masala Boneless", 145.00, "Combination of fiery pepper and juicy chicken.", R.drawable.pepper_chicken_masala));
        foodItemArrayList.add(new FoodItem("Chicken Manchurian Masala", 145.00, "Classic chicken manchurian.", R.drawable.chicken_manchurian_masala));
        foodItemArrayList.add(new FoodItem("Egg Biriyani", 130.00, "Eggs in our special aromatic Biriyani rice.", R.drawable.egg_biriyani));
        foodItemArrayList.add(new FoodItem("Veg Biriyani", 130.00, "Vegetarian version of our special aromatic Biriyani.", R.drawable.veg_biriyani));
        foodItemArrayList.add(new FoodItem("Biriyani Rice (Non-veg)", 135.00, "Flavourful and spice filled aromatic rice.", R.drawable.biriyani_rice_non_veg));
        foodItemArrayList.add(new FoodItem("Mutton Raan Biriyani", 685.00, "Rich Leg of Mutton in aromatic Biriyani rice.", R.drawable.mutton_raan_biriyani));
        foodItemArrayList.add(new FoodItem("Jeera Rice", 130.00, "Fresh coocked rice with jeera of cumin seeds.", R.drawable.jeera_rice));
        foodItemArrayList.add(new FoodItem("Plain Rice", 65.00, "Fresh Boiled Rice.", R.drawable.plain_rice));
        foodItemArrayList.add(new FoodItem("Lemon Rice", 130.00, "Tangy local special cooked with Fresh rice.", R.drawable.lemon_rice));

        recyclerViewAdapter = new MenuRecyclerViewAdapter(this, foodItemArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public static void clearSelectedFoodItems() {
        selectedFoodItems.clear();
    }
}
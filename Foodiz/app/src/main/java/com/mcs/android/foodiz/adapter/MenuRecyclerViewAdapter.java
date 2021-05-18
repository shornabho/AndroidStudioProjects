package com.mcs.android.foodiz.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcs.android.foodiz.MainActivity;
import com.mcs.android.foodiz.R;
import com.mcs.android.foodiz.models.FoodItem;

import java.util.ArrayList;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<FoodItem> foodItems;

    public MenuRecyclerViewAdapter(Context context, ArrayList<FoodItem> foodItems) {
        this.context = context;
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodItem foodItem = foodItems.get(position);

        holder.imageViewItemImage.setImageResource(foodItem.getItemImageResourceID());
        holder.textViewItemName.setText(foodItem.getItemName());
        holder.textViewItemPrice.setText("₹ " + Double.toString(foodItem.getItemPrice()));
        holder.textViewItemDescription.setText(foodItem.getItemDescription());
        holder.selectedFoodItem = foodItem;
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageViewItemImage;
        private TextView textViewItemName;
        private TextView textViewItemPrice;
        private TextView textViewItemDescription;
        private Button buttonAddItem;
        private FoodItem selectedFoodItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewItemImage = itemView.findViewById(R.id.menuImageViewItemImage);
            textViewItemName = itemView.findViewById(R.id.menuTextViewItemName);
            textViewItemPrice = itemView.findViewById(R.id.menuTextViewItemPrice);
            textViewItemDescription = itemView.findViewById(R.id.menuTextViewItemDescription);
            buttonAddItem = itemView.findViewById(R.id.buttonAddItem);

            buttonAddItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, selectedFoodItem.getItemName() + " added to cart!", Toast.LENGTH_SHORT).show();
            MainActivity.selectedFoodItems.add(selectedFoodItem);
        }
    }
}

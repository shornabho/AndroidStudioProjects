package com.mcs.android.foodiz.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcs.android.foodiz.MainActivity;
import com.mcs.android.foodiz.R;
import com.mcs.android.foodiz.models.FoodItem;

import java.util.ArrayList;

public class CheckoutRecyclerViewAdapter extends RecyclerView.Adapter<CheckoutRecyclerViewAdapter.ViewHolder> {


    private Activity activity;

    public CheckoutRecyclerViewAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public CheckoutRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.checkout_food_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutRecyclerViewAdapter.ViewHolder holder, int position) {
        FoodItem foodItem = MainActivity.selectedFoodItems.get(position);

        holder.checkoutTextViewItemName.setText(foodItem.getItemName());
        holder.checkoutImageViewItemImage.setImageResource(foodItem.getItemImageResourceID());
        holder.checkoutTextViewItemPrice.setText("₹ " + Double.toString(foodItem.getItemPrice()));
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        Log.i("SelectedItemsSize", Integer.toString(MainActivity.selectedFoodItems.size()));
        return MainActivity.selectedFoodItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView checkoutImageViewItemImage;
        private Button checkoutButtonRemoveItem;
        private TextView checkoutTextViewItemName;
        private TextView checkoutTextViewItemPrice;
        private TextView checkoutTotalAmount;
        private Button buttonPayAndPlaceOrder;
        private int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkoutImageViewItemImage = itemView.findViewById(R.id.checkoutImageViewItemImage);
            checkoutButtonRemoveItem = itemView.findViewById(R.id.checkoutButtonRemoveItem);
            checkoutTextViewItemName = itemView.findViewById(R.id.checkoutTextViewItemName);
            checkoutTextViewItemPrice = itemView.findViewById(R.id.checkoutTextViewItemPrice);
            checkoutTotalAmount = activity.findViewById(R.id.checkoutTotalAmount);
            buttonPayAndPlaceOrder = activity.findViewById(R.id.buttonPayAndPlaceOrder);

            checkoutButtonRemoveItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MainActivity.selectedFoodItems.remove(position);
                    notifyDataSetChanged();
                    double totalAmount = 0.0;
                    for (FoodItem foodItem : MainActivity.selectedFoodItems) {
                        totalAmount += foodItem.getItemPrice();
                    }
                    checkoutTotalAmount.setText("₹ " + Double.toString(totalAmount));
                    if (totalAmount == 0) {
                        buttonPayAndPlaceOrder.setEnabled(false);
                    }
                    else {
                        buttonPayAndPlaceOrder.setEnabled(true);
                    }
                }
            });
        }
    }
}

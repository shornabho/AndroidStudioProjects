package com.mcs.android.grocs.ui.categories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcs.android.grocs.R;
import com.mcs.android.grocs.models.Product;

import java.util.List;

public class CategoryProductsAdapter extends RecyclerView.Adapter<CategoryProductsAdapter.ViewHolder> {

    private Context context;
    private List<Product> categoryProductsList;

    public CategoryProductsAdapter(Context context, List<Product> categoryProductsList) {
        this.context = context;
        this.categoryProductsList = categoryProductsList;
    }

    @NonNull
    @Override
    public CategoryProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_products_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryProductsAdapter.ViewHolder holder, int position) {
        Product currentProduct = categoryProductsList.get(position);
        holder.ivProductImage.setImageResource(currentProduct.getProductImage());
        holder.tvProductTitle.setText(currentProduct.getProductTitle());
        holder.tvShopName.setText(currentProduct.getProductShop());
        holder.tvProductSellingPrice.setText("₹ " + currentProduct.getProductSellingPrice());
        holder.tvProductMRP.setText("₹ " + currentProduct.getProductMRP());
        holder.tvProductQuantity.setText(currentProduct.getQuantityInKg() + " kg");
    }

    @Override
    public int getItemCount() {
        return categoryProductsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivProductImage;
        private TextView tvShopName;
        private Button btnAddItem;
        private TextView tvProductTitle;
        private TextView tvProductSellingPrice;
        private TextView tvProductMRP;
        private TextView tvProductQuantity;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProductImage = itemView.findViewById(R.id.ivCategoryProductImage);
            tvShopName = itemView.findViewById(R.id.tvCategoryProductShop);
            btnAddItem = itemView.findViewById(R.id.btnCategoryProductAddItem);
            tvProductTitle = itemView.findViewById(R.id.tvCategoryProductTitle);
            tvProductSellingPrice = itemView.findViewById(R.id.tvCategoryProductSellingPrice);
            tvProductMRP = itemView.findViewById(R.id.tvCategoryProductMRP);
            tvProductQuantity = itemView.findViewById(R.id.tvCategoryProductQuantity);
        }
    }
}

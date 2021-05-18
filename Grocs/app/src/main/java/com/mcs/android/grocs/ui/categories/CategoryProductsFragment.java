package com.mcs.android.grocs.ui.categories;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mcs.android.grocs.R;
import com.mcs.android.grocs.models.Category;
import com.mcs.android.grocs.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryProductsFragment extends Fragment {

    private RecyclerView categoryProductsRecyclerView;
    private CategoryProductsAdapter categoryProductsAdapter;
    private List<Product> categoryProductsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoryProductsRecyclerView = view.findViewById(R.id.categoriesProductsRecyclerView);
        categoryProductsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        categoryProductsList = new ArrayList<Product>();

        categoryProductsList.add(new Product(R.drawable.onions, "Onions", "Nature's Basket", "Vegetables", 1, 14, 12));
        categoryProductsList.add(new Product(R.drawable.tomatoes, "Tomatoes", "Nature's Basket", "Vegetables", 1, 20, 18.5));
        categoryProductsList.add(new Product(R.drawable.potatoes, "Potatoes", "Nature's Basket", "Vegetables", 1, 20, 18.5));
        categoryProductsList.add(new Product(R.drawable.milkpowder, "Milk Powder", "Nature's Basket", "Vegetables", 1, 20, 18.5));
        categoryProductsList.add(new Product(R.drawable.pomfret, "Pomfret Fish", "Nature's Basket", "Vegetables", 1, 20, 18.5));
        categoryProductsList.add(new Product(R.drawable.chicken, "Boneless Chicken", "Nature's Basket", "Vegetables", 1, 20, 18.5));

        categoryProductsAdapter = new CategoryProductsAdapter(getContext(), categoryProductsList);
        categoryProductsRecyclerView.setAdapter(categoryProductsAdapter);

    }
}
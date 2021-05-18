package com.mcs.android.grocs.ui.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mcs.android.grocs.R;
import com.mcs.android.grocs.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment {

    private RecyclerView categoriesRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private CategoriesAdapter categoriesAdapter;
    private List<Category> categoryList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoriesRecyclerView = view.findViewById(R.id.categoriesRecyclerView);
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        categoriesRecyclerView.setLayoutManager(gridLayoutManager);

        categoryList = new ArrayList<Category>();

        categoryList.add(new Category(R.drawable.fruits, "Fruits"));
        categoryList.add(new Category(R.drawable.vegetables, "Vegetables"));
        categoryList.add(new Category(R.drawable.dairy, "Bakery Cakes and Diary"));
        categoryList.add(new Category(R.drawable.beverages, "Beverages"));
        categoryList.add(new Category(R.drawable.snacks, "Snacks and Branded Foods"));
        categoryList.add(new Category(R.drawable.cleaning, "Cleaning and Household"));
        categoryList.add(new Category(R.drawable.meat, "Eggs, Meat and Fish"));
        categoryList.add(new Category(R.drawable.babycare, "Baby Care"));

        categoriesAdapter = new CategoriesAdapter(getContext(), categoryList);
        categoriesRecyclerView.setAdapter(categoriesAdapter);
    }
}
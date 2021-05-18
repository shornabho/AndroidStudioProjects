package com.mcs.android.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ItemsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemsFragment newInstance(String param1, String param2) {
        ItemsFragment fragment = new ItemsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        String[] productArray = {};
        String category = "";
        if (bundle != null) {
            productArray = bundle.getStringArray("items");
            category = bundle.getString("category");
        }
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_items, container, false);
        // Update Category name
        TextView textViewItemCategory = (TextView) v.findViewById(R.id.textViewItemCategory);
        textViewItemCategory.setText(category);

        // List view items
        ListView listView = (ListView) v.findViewById(R.id.listViewItems);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.item_layout, productArray);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                Snackbar snackbar = Snackbar
                        .make(getView(), item, Snackbar.LENGTH_LONG)
                        .setAction("Buy", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                purchaseItem(getActivity(), item);
                            }
                        });
                snackbar.show();
            }
        });

        return v;
    }

    public void purchaseItem(final Activity activity, String item) {
        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle("Confirm Order?");
        builder.setMessage("Are you sure you want to order a " + item + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Fragment fragment = new ThankYouFragment();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                Fragment thankYouFragment = new ThankYouFragment();
                fragmentTransaction.replace(R.id.fragmentItemsList, thankYouFragment, null);
                fragmentTransaction.commit();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
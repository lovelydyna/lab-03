package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {

    private int position;
    private City cityToEdit;

    @NonNull
    static EditCityFragment newInstance(City cityToEdit, int position
    ) {
        // position added + HINT
        Bundle args = new Bundle();
        args.putSerializable("city", cityToEdit);
        args.putInt("position", position);

        EditCityFragment fragment = new EditCityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    interface EditCityDialogListener {
        void editCity(City newCity, int position);
    }
    private EditCityDialogListener listener;

    public EditCityFragment() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof EditCityFragment.EditCityDialogListener) {
            listener = (EditCityFragment.EditCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view =
                LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText editCityName = view.findViewById(R.id.edit_text_city_text);
        EditText editProvinceName = view.findViewById(R.id.edit_text_province_text);

        // Retrieve selected city
        Bundle args = getArguments();
        City cityToEdit = null;
        if (args != null) {
            cityToEdit = (City) args.getSerializable("city");
        }
        int position = args.getInt("position");
        if (cityToEdit != null) {
            editCityName.setText(cityToEdit.getName());
            editProvinceName.setText(cityToEdit.getProvince());
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        return builder
                .setView(view)
                .setTitle("Edit a city")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Add", (dialog, which) -> {
                    String cityName = editCityName.getText().toString();
                    String provinceName = editProvinceName.getText().toString();


                    // ADDED by TA
//                    City city = (City) getArguments().getSerializable("city");
//                    city = new City(cityName, provinceName);

//                  // OG
                    listener.editCity(new City(cityName, provinceName), position);
                })
                .create();
    }
}


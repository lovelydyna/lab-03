package com.example.listycitylab3;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogListener, EditCityFragment.EditCityDialogListener {


    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;

    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {
                "Toronto", "Montreal", "Vancouver", "Edmonton"
        };

        String[] provinces = {
                "ON", "QC", "BC", "AB"
        };


        // addcity fragment
        dataList = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        cityAdapter = new CityArrayAdapter(this, dataList);
        ListView cityList = findViewById(R.id.city_list);
        cityList.setAdapter(cityAdapter);

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(v -> {
            new AddCityFragment().show(getSupportFragmentManager(), "Add City");
        });


        // editcity fragment
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City cityToEdit = cityAdapter.getItem(position);

            EditCityFragment fragment = EditCityFragment.newInstance(cityToEdit);
            fragment.show(getSupportFragmentManager(), "EDIT_CITY");
        });
    }

    @Override
    public void editCity(City city) {
        cityAdapter.remove(city);
        cityAdapter.notifyDataSetChanged();
    }
}
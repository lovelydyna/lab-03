package com.example.listycitylab3;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;

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

        dataList = new ArrayList<>(); 
        
        for (int i = 0; i < cities.length; i++) {
            dataList.add(new City(cities[i], provinces[i]));
        }

        cityAdapter = new CityArrayAdapter(this, dataList);
        ListView cityList = findViewById(R.id.city_list);
        cityList.setAdapter(cityAdapter);
    }
}
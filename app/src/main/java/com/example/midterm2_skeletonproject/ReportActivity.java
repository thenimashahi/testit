package com.example.midterm2_skeletonproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {
    ListView listView;
    private CarsAdapter carsAdapter;
    private DBHandler mDBHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        mDBHandler = new DBHandler(this, null, null, 1);
        listView = findViewById(R.id.carListView);
        ArrayList<Car> cars = mDBHandler.getCars();
        carsAdapter = new CarsAdapter(this, cars);
        listView.setAdapter(carsAdapter);
    }
}

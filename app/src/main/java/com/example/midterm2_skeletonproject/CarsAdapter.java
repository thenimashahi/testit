package com.example.midterm2_skeletonproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CarsAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<Car> cars;

    CarsAdapter(Activity activity, ArrayList<Car> cars) {
        this.activity = activity;
        this.cars = cars;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return cars.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view = inflater.inflate(R.layout.item_car, parent, false);
        configureCarItem(view, position);

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void configureCarItem(View view, int position) {
        TextView nameTv = view.findViewById(R.id.nameTv);
        TextView modelYearTv = view.findViewById(R.id.modelYearTv);
        ImageView carIv = view.findViewById(R.id.carIv);

        Car carInfo = cars.get(position);
        nameTv.setText(carInfo.getOwnerName());
        modelYearTv.setText("Model: " + carInfo.getModel() + "  Year: " + carInfo.getYear());
        carIv.setImageBitmap(base64ToBitmap(carInfo.getImageData()));
    }

    private Bitmap base64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }
}

package com.example.midterm2_skeletonproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView myImage;
    EditText myName;

    Car myCar;
    ArrayList<Car> carsArrayList;
    String carImg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myImage = (ImageView) findViewById(R.id.personalImageID);
        myName = (EditText) findViewById(R.id.studentName);
        carsArrayList = new ArrayList<Car>(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void uploade(View view) {
        //open camera app and take a photo of the car. (use an emulator fixed camera instead if you don't have physical device)
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }



    public void addNewCarModelAndYear(View view) {
        String owner = myName.getText().toString();
        if(owner.isEmpty()) {
            Toast.makeText(this, "Please input your name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(carImg.isEmpty()) {
            Toast.makeText(this, "Please choose a image", Toast.LENGTH_SHORT).show();
            return;
        }

        //open dialog fragment to get the car model and year from the owner
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("dialog");
        if(prev != null) {
            fragmentTransaction.remove(prev);
        }
        fragmentTransaction.addToBackStack(null);

        DialogFragment newFragment = ModelYearDialogFragment.newInstance(owner, carImg);
        newFragment.show(fragmentTransaction, "dialog");
    }


    public void saveCar(View view) {
        //Save car object and navigate to report activity to show the list of all cars
        Intent reportIntent = new Intent(this,ReportActivity.class);
        startActivity(reportIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            assert data != null;
            Bundle extras = data.getExtras();
            assert extras != null;
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            assert imageBitmap != null;
            carImg = bitmapToBase64(imageBitmap);
            myImage.setImageBitmap(imageBitmap);
        }
    }

    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}

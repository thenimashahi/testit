package com.example.midterm2_skeletonproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "db_car", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE cars(id INTEGER PRIMARY KEY, owner TEXT, model TEXT, year INT, image TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DELETE_TABLE = "DROP TABLE IF EXISTS cars";
        db.execSQL(DELETE_TABLE);
        onCreate(db);
    }

    public void addCar(Car car) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("owner", car.getOwnerName());
        contentValues.put("model", car.getModel());
        contentValues.put("year", car.getYear());
        contentValues.put("image", car.getImageData());

        SQLiteDatabase database = this.getWritableDatabase();
        database.insert("cars", null, contentValues);
        database.close();
    }

    public ArrayList<Car> getCars() {
        String query = "SELECT * FROM cars";
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        ArrayList<Car> cars = new ArrayList<>();
        if(!cursor.moveToFirst()) return cars;
        cars.add(new Car(cursor.getString(1), cursor.getString(4), cursor.getInt(3),
                cursor.getString(2)));
        while (cursor.moveToNext()) {
            cars.add(new Car(cursor.getString(1), cursor.getString(4), cursor.getInt(3),
                    cursor.getString(2)));
        }
        cursor.close();
        database.close();

        return cars;
    }
}

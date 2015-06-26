package com.devkadair.vrum.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.devkadair.vrum.data.CarInfoContract.StyleEntry;
import com.devkadair.vrum.data.CarInfoContract.CarEntry;

/**
 * Created by keith on 6/15/15.
 */
public class CarDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "car.db";

    public CarDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_CAR_TABLE = " CREATE TABLE " + CarEntry.TABLE_NAME + " (" +
                CarEntry._ID + " INTEGER PRIMARY KEY " +
                CarEntry.COLUMN_MAKE + " TEXT NOT NULL " +
                CarEntry.COLUMN_MODEL + " TEXT NOT NULL " +
                CarEntry.COLUMN_YEAR + " TEXT NOT NULL " +
                CarEntry.COLUMN_STYLE_ID + " TEXT NOT NULL " +
                CarEntry.COLUMN_STYLE_DESCRIPTION + " TEXT NOT NULL " +
                " );";

        final String SQL_CREATE_STYLE_TABLE = " CREATE TABLE " + StyleEntry.TABLE_NAME + " (" +
                StyleEntry._ID + " INTEGER PRIMARY KEY " +
                StyleEntry.COLUMN_CARGO_CAPACITY + " TEXT NOT NULL " +
                StyleEntry.COLUMN_CLEARANCE + " TEXT NOT NULL " +
                StyleEntry.COLUMN_DRIVE_TYPE + " TEXT NOT NULL " +
                StyleEntry.COLUMN_ENGINE_CYLINDERS + " TEXT NOT NULL " +
                StyleEntry.COLUMN_ENGINE_SIZE + " TEXT NOT NULL " +
                StyleEntry.COLUMN_FUEL_CAPACITY + " TEXT NOT NULL " +
                StyleEntry.COLUMN_FUEL_TYPE + " TEXT NOT NULL " +
                StyleEntry.COLUMN_HEIGHT + " TEXT NOT NULL " +
                StyleEntry.COLUMN_HORSEPOWER + " TEXT NOT NULL " +
                StyleEntry.COLUMN_LENGTH + " TEXT NOT NULL " +
                StyleEntry.COLUMN_MAKE + " TEXT NOT NULL " +
                StyleEntry.COLUMN_MILEAGE_CITY + " TEXT NOT NULL " +
                StyleEntry.COLUMN_MILEAGE_HIGHWAY + " TEXT NOT NULL " +
                StyleEntry.COLUMN_MODEL + " TEXT NOT NULL " +
                StyleEntry.COLUMN_NUMBER_DOORS + " TEXT NOT NULL " +
                StyleEntry.COLUMN_RANGE_CITY + " TEXT NOT NULL " +
                StyleEntry.COLUMN_TORQUE + " TEXT NOT NULL " +
                StyleEntry.COLUMN_RANGE_HIGHWAY + " TEXT NOT NULL " +
                StyleEntry.COLUMN_TRANSMISSION + " TEXT NOT NULL " +
                StyleEntry.COLUMN_VALVES + " TEXT NOT NULL " +
                StyleEntry.COLUMN_STYLE_ID + " REAL NOT NULL " +
                StyleEntry.COLUMN_WEIGHT + " TEXT NOT NULL " +
                StyleEntry.COLUMN_WIDTH + " TEXT NOT NULL " +
                StyleEntry.COLUMN_WHEEL_BASE + " TEXT NOT NULL " +
                StyleEntry.COLUMN_YEAR + "TEXT NOT NULL " +

//                " FOREIGN KEY (" + StyleEntry.COLUMN_STYLE_ID + ") REFERENCES " +
//                CarEntry.TABLE_NAME + " (" + CarEntry.COLUMN_STYLE_ID + "), " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_STYLE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_CAR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + StyleEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}

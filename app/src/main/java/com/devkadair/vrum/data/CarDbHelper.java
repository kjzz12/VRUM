package com.devkadair.vrum.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
                CarEntry.COLUMN_CARGO_CAPACITY + " TEXT NOT NULL " +
                CarEntry.COLUMN_CLEARANCE + " TEXT NOT NULL " +
                CarEntry.COLUMN_DRIVE_TYPE + " TEXT NOT NULL " +
                CarEntry.COLUMN_ENGINE_CYLINDERS + " TEXT NOT NULL " +
                CarEntry.COLUMN_ENGINE_SIZE + " TEXT NOT NULL " +
                CarEntry.COLUMN_FUEL_CAPACITY + " TEXT NOT NULL " +
                CarEntry.COLUMN_FUEL_TYPE + " TEXT NOT NULL " +
                CarEntry.COLUMN_HEIGHT + " TEXT NOT NULL " +
                CarEntry.COLUMN_HORSEPOWER + " TEXT NOT NULL " +
                CarEntry.COLUMN_LENGTH + " TEXT NOT NULL " +
                CarEntry.COLUMN_MAKE + " TEXT NOT NULL " +
                CarEntry.COLUMN_MILEAGE_CITY + " TEXT NOT NULL " +
                CarEntry.COLUMN_MILEAGE_HIGHWAY + " TEXT NOT NULL " +
                CarEntry.COLUMN_MODEL + " TEXT NOT NULL " +
                CarEntry.COLUMN_NUMBER_DOORS + " TEXT NOT NULL " +
                CarEntry.COLUMN_RANGE_CITY + " TEXT NOT NULL " +
                CarEntry.COLUMN_TORQUE + " TEXT NOT NULL " +
                CarEntry.COLUMN_RANGE_HIGHWAY + " TEXT NOT NULL " +
                CarEntry.COLUMN_TRANSMISSION + " TEXT NOT NULL " +
                CarEntry.COLUMN_VALVES + " TEXT NOT NULL " +
                CarEntry.COLUMN_CAR_ID + " REAL NOT NULL " +
                CarEntry.COLUMN_WEIGHT + " TEXT NOT NULL " +
                CarEntry.COLUMN_WIDTH + " TEXT NOT NULL " +
                CarEntry.COLUMN_WHEEL_BASE + " TEXT NOT NULL " +
                CarEntry.COLUMN_YEAR + "TEXT NOT NULL " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_CAR_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + CarEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}

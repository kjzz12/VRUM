package com.devkadair.vrum.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Kadair on 6/7/15.
 */
public class VehicleInfoContract {

    public static final String CONTENT_AUTHORITY = "com.devkadair.vrum";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_CAR = "car";

    public static final String PATH_VIN = "vin_entry";

    public static final class CarEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CAR).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CAR;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CAR;

//        Table name is "car"
        public static final String TABLE_NAME = "car";

//        General car traits
        public static final String COLUMN_MAKE = "car_make";

        public static final String COLUMN_MODEL = "car_model";

        public static final String COLUMN_YEAR = "car_year";

        public static final String COLUMN_NUMBER_DOORS = "car_doors";

//        Car Measurements
        public static final String COLUMN_LENGTH = "car_length";

        public static final String COLUMN_WIDTH = "car_width";

        public static final String COLUMN_HEIGHT = "car_height";

        public static final String COLUMN_WEIGHT = "car_weight";

        public static final String COLUMN_CARGO_CAPACITY = "car_cargo_capacity";

        public static final String COLUMN_WHEEL_BASE = "car_wheel_base";

        public static final String COLUMN_CLEARANCE = "car_clearance";

//        Fuel
        public static final String COLUMN_FUEL_TYPE = "car_fuel_type";

        public static final String COLUMN_FUEL_CAPACITY = "car_fuel_capacity";

        public static final String COLUMN_MILEAGE_HIGHWAY = "car_mileage_highway";

        public static final String COLUMN_MILEAGE_CITY = "car_mileage_city";

        public static final String COLUMN_RANGE_CITY = "car_range_city";

        public static final String COLUMN_RANGE_HIGHWAY = "car_range_highway";

//        Drivetrain
        public static final String COLUMN_DRIVE_TYPE = "car_drive_type";

        public static final String COLUMN_TRANSMISSION = "car_transmission";

//        Engine
        public static final String COLUMN_ENGINE_SIZE = "car_engine_size";

        public static final String COLUMN_ENGINE_CYLINDERS = "car_engine_cylinders";

        public static final String COLUMN_HORSEPOWER = "car_horsepower";

        public static final String COLUMN_VALVES = "car_valves";

        public static final String COLUMN_TORQUE = "car_torque";

//        URI
        public static Uri buildCarUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}

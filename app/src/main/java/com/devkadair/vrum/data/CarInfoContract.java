package com.devkadair.vrum.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Kadair on 6/7/15.
 */
public class CarInfoContract {

    public static final String CONTENT_AUTHORITY = "com.devkadair.vrum";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_CAR = "car";
    public static final String PATH_STYLE = "style";

    public static final class  CarEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon ().appendPath(PATH_CAR).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CAR;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CAR;

//        Table name is "car"
        public static final String TABLE_NAME = "car";

        public static final String COLUMN_MAKE = "car_make";
        public static final String COLUMN_MODEL = "car_model";
        public static final String COLUMN_YEAR = "car_year";
        public static final String COLUMN_STYLE_ID = "car_style";
        public static final String COLUMN_STYLE_DESCRIPTION = "car_style_desc";

        //        URI
        public static Uri buildStyleUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }


    public static final class StyleEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_STYLE).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STYLE;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_STYLE;

//        Table name is "style"
        public static final String TABLE_NAME = "style";

//        General car traits
        public static final String COLUMN_MAKE = "style_make";

        public static final String COLUMN_MODEL = "style_model";

        public static final String COLUMN_YEAR = "style_year";

        public static final String COLUMN_NUMBER_DOORS = "style_doors";

        public static final String COLUMN_STYLE_ID =  "style_id";

//        Car Measurements
        public static final String COLUMN_LENGTH = "style_length";

        public static final String COLUMN_WIDTH = "style_width";

        public static final String COLUMN_HEIGHT = "style_height";

        public static final String COLUMN_WEIGHT = "style_weight";

        public static final String COLUMN_CARGO_CAPACITY = "style_cargo_capacity";

        public static final String COLUMN_WHEEL_BASE = "style_wheel_base";

        public static final String COLUMN_CLEARANCE = "style_clearance";

//        Fuel
        public static final String COLUMN_FUEL_TYPE = "style_fuel_type";

        public static final String COLUMN_FUEL_CAPACITY = "style_fuel_capacity";

        public static final String COLUMN_MILEAGE_HIGHWAY = "style_mileage_highway";

        public static final String COLUMN_MILEAGE_CITY = "style_mileage_city";

        public static final String COLUMN_RANGE_CITY = "style_range_city";

        public static final String COLUMN_RANGE_HIGHWAY = "style_range_highway";

//        Drivetrain
        public static final String COLUMN_DRIVE_TYPE = "style_drive_type";

        public static final String COLUMN_TRANSMISSION = "style_transmission";

//        Engine
        public static final String COLUMN_ENGINE_SIZE = "style_engine_size";

        public static final String COLUMN_ENGINE_CYLINDERS = "style_engine_cylinders";

        public static final String COLUMN_HORSEPOWER = "style_horsepower";

        public static final String COLUMN_VALVES = "style_valves";

        public static final String COLUMN_TORQUE = "style_torque";

//        URI
        public static Uri buildStyleUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}

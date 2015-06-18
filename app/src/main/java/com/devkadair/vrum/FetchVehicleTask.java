package com.devkadair.vrum;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import com.devkadair.vrum.data.CarInfoContract;

/**
 * Created by keith on 6/17/15.
 */
public class FetchCarTask extends AsyncTask<String, Void, Void> {

    private final String LOG_TAG = FetchCarTask.class.getSimpleName();

    private final Context mContext;

    public FetchCarTask(Context context) {
        mContext = context;
    }

    private boolean DEBUG = true;

    long addcar(String carSetting, String carMake, String carModel, String carYear) {
        long carID;

        Cursor carCursor = mContext.getContentResolver().query(
                CarInfoContract.CarEntry.CONTENT_URI,
                new String[]{CarInfoContract.CarEntry._ID},
                CarInfoContract.CarEntry.COLUMN_CAR_ID + " = ?",
                new String[]{carSetting},
                null);
    }

}

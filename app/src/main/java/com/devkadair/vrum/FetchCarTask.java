package com.devkadair.vrum;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.devkadair.vrum.data.CarInfoContract;
import com.devkadair.vrum.data.CarInfoContract.CarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

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

    long addCar(String carSetting, String carMake, String carModel, String carYear) {
        long carId;

        Cursor carCursor = mContext.getContentResolver().query(
                CarInfoContract.CarEntry.CONTENT_URI,
                new String[]{CarInfoContract.CarEntry._ID},
                CarInfoContract.CarEntry.COLUMN_STYLE_ID + " = ?",
                new String[]{carSetting},
                null);

        if (carCursor.moveToFirst()) {
            int carIdIndex = carCursor.getColumnIndex(CarInfoContract.CarEntry._ID);
            carId = carCursor.getLong(carIdIndex);
        } else {
            ContentValues carValues = new ContentValues();

            carValues.put(CarInfoContract.CarEntry.COLUMN_MAKE, carMake);
            carValues.put(CarInfoContract.CarEntry.COLUMN_MODEL, carModel);
            carValues.put(CarInfoContract.CarEntry.COLUMN_YEAR, carYear);

            Uri insertedUri = mContext.getContentResolver().insert(
                    CarInfoContract.CarEntry.CONTENT_URI,
                    carValues
            );

            carId = ContentUris.parseId(insertedUri);
        }

        carCursor.close();
        return carId;
    }

    private void getCarDataFromJson(String carJsonStr,
                                        String carMake, String carModel, String carYear)
        throws JSONException {

        final String OWM_STYLES = "styles";
        final String OWM_STYLE_ID = "id";
        final String OWM_STYLE_NAME = "name";

        try {
            JSONObject carJson = new JSONObject(carJsonStr);
            JSONArray styleArray = carJson.getJSONArray(OWM_STYLES);

            Vector<ContentValues> cVVector = new Vector<ContentValues>(styleArray.length());

            for(int i = 0; i <styleArray.length() - 1; i++) {
                String id;
                String name;

                JSONObject styleInfo = styleArray.getJSONObject(i);

                id = styleInfo.getString(OWM_STYLE_ID);
                name = styleInfo.getString(OWM_STYLE_NAME);

                ContentValues carValues = new ContentValues();

                carValues.put(CarEntry.COLUMN_STYLE_ID, id);
                carValues.put(CarEntry.COLUMN_STYLE_DESCRIPTION, name);

                cVVector.add(carValues);
            }
            int inserted = 0;
            if ( cVVector.size() > 0) {
                ContentValues[] cvArray = new ContentValues[cVVector.size()];
                cVVector.toArray(cvArray);
                inserted = mContext.getContentResolver().bulkInsert(CarEntry.CONTENT_URI, cvArray);
            }

            Log.d(LOG_TAG, "FetchCarTaks Complete. " + inserted + " Inserted");

        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(String... params) {
        if (params.length == 0) {
            return null;
        }
//        String makeQuery = params[0];
//        String modelQuery = params[1];
//        String yearQuery = params[2];
        String makeQuery = "honda";
        String modelQuery = "accord";
        String yearQuery = "2014";

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String carJsonStr = null;

        String format = "json";
        String apiKey = "77cfddt9fz35sycv79tvvwmd";
        String view = "basic";

        try {
            final String CAR_BASE_URL = "https://api.edmunds.com/api/vehicle/v2";
            final String YEAR_PARAM = "year";
            final String VIEW_PARAM = "view";
            final String FORMAT_PARAM = "fmt";
            final String APIKEY_PARAM = "api_key";

            Uri builtUri = Uri.parse(CAR_BASE_URL).buildUpon()
                    .appendPath(makeQuery)
                    .appendPath(modelQuery)
                    .appendQueryParameter(YEAR_PARAM, yearQuery)
                    .appendQueryParameter(VIEW_PARAM, view)
                    .appendQueryParameter(FORMAT_PARAM, format)
                    .appendQueryParameter(APIKEY_PARAM, apiKey)
                    .build();

            URL url = new URL(builtUri.toString());

            Log.d(LOG_TAG, "Built URL is " + url);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            carJsonStr = buffer.toString();
            getCarDataFromJson(carJsonStr, makeQuery, modelQuery, yearQuery);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return null;
    }
}
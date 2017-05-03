package se.manageall.api1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.RelativeLayout;

import org.json.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;


/**
 * Created by Nils on 2017-05-03.
 */

public class MyRequest extends AsyncTask<String,Void,Void> {

    @Override
    protected Void doInBackground(String... params) {

        // Create URL
        String format = "json";
        String key = "767365360f464abbbc31808c78b5aba9";
        String lat = "59.3453541"; // Nackademin
        String lon = "18.0103099"; // Nackademin
        String max_resp = "4";
        String max_rad = "1000";
        String address = "http://api.sl.se/api2/nearbystops." + format + "?key=" + key + "&originCoordLat=" + lat + "&originCoordLong=" + lon + "&maxresults=" + max_resp + "&radius=" + max_rad + "";

        String myAddress = address;

        Log.d("Address", myAddress);

        //Begin Stuff

        URL myUrl;

        HttpURLConnection myConnection = null;

        try {

            myUrl = new URL( myAddress );

            myConnection = ( HttpURLConnection ) myUrl.openConnection();

            myConnection.connect();

            InputStream myInputStream = myConnection.getInputStream();

            InputStreamReader myInputStreamReader = new InputStreamReader( myInputStream );

            int data = myInputStreamReader.read();

            String res = "";

            while (data != -1) {
                char current = (char) data;
                data = myInputStreamReader.read();
                res += current;
            }

            // All result now in res
            Log.d("++++ Result +++", res);

            // Convert from json to arraylist

            JSONObject myJSONObject = new JSONObject(res);

            JSONArray MyJSONArray = myJSONObject.getJSONArray("MyJSONArray");



            // Update string "message"

            // Testa för att se om programmet lever
            Log.d("++++++++++++++++","++++++++++++++++++++++++"); // testar
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (myConnection != null) {
                myConnection.disconnect();
            }
        }
        return null;
    }
}

package codeOholix.covid19.Util.Network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Url_Json_Parsing1 extends AsyncTask<Void, Void, String>{


private AsyncResponse1<String> mCallbacks;
public Exception exception;


        Context context;

public Url_Json_Parsing1(Context context, AsyncResponse1 mCallbacks)
        {
        this.context = context;
        this.mCallbacks=mCallbacks;
        }

private final String URL_TOTAL_STATS="https://api.covid19india.org/data.json";

@Override
protected String doInBackground(Void... params) {

        HttpURLConnection urlConnection=null;
        URL url1;
        BufferedReader reader = null;

        try {

        url1 = new URL(URL_TOTAL_STATS);
        urlConnection = (HttpURLConnection) url1.openConnection();
        urlConnection.setRequestMethod("GET"); //Your method here
        urlConnection.connect();


        InputStream inputStream = urlConnection.getInputStream();
        StringBuffer buffer = new StringBuffer();
        if (inputStream == null) {
        return null;
        }
        reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null)
        buffer.append(line + "\n");

        if (buffer.length() == 0)
        return null;

        return buffer.toString();
        } catch (IOException e) {
        Log.e(TAG, "IO Exception", e);
        exception = e;
        return null;
        } finally {
        if (urlConnection != null) {
        urlConnection.disconnect();
        }
        if (reader != null) {
        try {
        reader.close();
        } catch (final IOException e) {
        exception = e;
        Log.e(TAG, "Error closing stream", e);
        }
        }
        }

        }

@Override
protected void onPostExecute(String response) {

        if (response != null) {
        mCallbacks.onSuccess(response);

        try {/*
                    JSONObject obj = new JSONObject(response);
                    JSONArray array = obj.getJSONArray("statewise");
                    for (int i=0;i<array.length();i++)
                    {
                        JSONObject data = array.getJSONObject(i);
                        Toast.makeText(context, "With Class :"+data.getString("state"), Toast.LENGTH_SHORT).show();
                    }*/



        }
        catch(Exception e)
        {
        Log.d("Values ",e.toString());
        }

        }
        }

        }

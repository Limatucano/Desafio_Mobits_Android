package com.example.desafio_mobits_android.data;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchBooks extends AsyncTask<String, Void, String> {
    private WeakReference<TextView> mTitleText;

    public FetchBooks(TextView titleText){
        this.mTitleText = new WeakReference<TextView>(titleText);
    }
    @Override
    protected void onPostExecute(String s) {
        try {
            JSONArray response = new JSONArray(s);
            Log.d("TESTE", response.toString());

        }catch (JSONException e) {
            e.printStackTrace();
        }
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getAllBooks();
    }
}

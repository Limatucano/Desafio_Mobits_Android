package com.example.desafio_mobits_android.data;

import static com.example.desafio_mobits_android.data.NetworkUtils.BASE_URL;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafio_mobits_android.view.adapter.BookListAdapter;
import com.example.desafio_mobits_android.view.adapter.HouseListAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.ref.WeakReference;

public class FetchHouses extends AsyncTask<String, Void, String> {
    private RecyclerView houseList;
    private HouseListAdapter mAdapter;
    private Context context;

    public FetchHouses(RecyclerView houseRecyclerView, Context context){
        this.houseList = houseRecyclerView;
        this.context = context;
    }
    @Override
    protected void onPostExecute(String s) {
        try {
            JSONArray response = new JSONArray(s);
            mAdapter = new HouseListAdapter(context, response);
            houseList.setAdapter(mAdapter);
            houseList.setLayoutManager(new LinearLayoutManager(context));

        }catch (JSONException e) {
            e.printStackTrace();
        }
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        Uri builtURI = Uri.parse(BASE_URL).buildUpon()
        .appendPath("houses")
        .build();
        String requestMethod = "GET";
        return NetworkUtils.request(builtURI, requestMethod);
    }
}

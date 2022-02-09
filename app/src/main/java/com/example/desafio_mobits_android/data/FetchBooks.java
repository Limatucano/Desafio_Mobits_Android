package com.example.desafio_mobits_android.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafio_mobits_android.view.adapter.BookListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class FetchBooks extends AsyncTask<String, Void, String> {
    private WeakReference<RecyclerView> bookRecyclerView;
    private RecyclerView bookList;
    private BookListAdapter mAdapter;
    private Context context;

    public FetchBooks(RecyclerView bookRecyclerView, Context context){
        this.bookRecyclerView = new WeakReference<RecyclerView>(bookRecyclerView);
        this.bookList = bookRecyclerView;
        this.context = context;
    }
    @Override
    protected void onPostExecute(String s) {
        try {
            JSONArray response = new JSONArray(s);
            mAdapter = new BookListAdapter(context, response);
            bookList.setAdapter(mAdapter);
            bookList.setLayoutManager(new LinearLayoutManager(context));

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

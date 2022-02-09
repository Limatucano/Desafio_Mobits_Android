package com.example.desafio_mobits_android.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafio_mobits_android.R;
import com.example.desafio_mobits_android.view.DetailBookActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    private final JSONArray books;
    private LayoutInflater mInflater;
    private Context context;

    public BookListAdapter(Context context, JSONArray books ){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.books = books;
    }

    @NonNull
    @Override
    public BookListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.booklist_item, parent, false);
        return new ViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull BookListAdapter.ViewHolder holder, int position) {
        try {
            JSONObject bookItem = new JSONObject(String.valueOf(books.getJSONObject(position)));
            holder.bookTitle.setText(bookItem.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return books.length();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView bookTitle;
        public BookListAdapter adapter;

        public ViewHolder(@NonNull View itemView, BookListAdapter adapter) {
            super(itemView);
            this.bookTitle = itemView.findViewById(R.id.bookTitle);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            try {
                JSONObject bookItem = new JSONObject(String.valueOf(books.getJSONObject(position)));
                Intent intent = new Intent(context, DetailBookActivity.class);
                intent.putExtra("book", bookItem.toString());
                context.startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}

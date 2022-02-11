package com.example.desafio_mobits_android.view.adapter;

import android.content.Context;
import android.content.Intent;
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

public class TitlesCharacterAdapter extends RecyclerView.Adapter<TitlesCharacterAdapter.ViewHolder> {

    private final JSONArray titles;
    private LayoutInflater mInflater;
    private Context context;

    public TitlesCharacterAdapter(Context context, JSONArray titles ){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.titles = titles;
    }

    @NonNull
    @Override
    public TitlesCharacterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.character_title_list, parent, false);
        return new ViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TitlesCharacterAdapter.ViewHolder holder, int position) {
        try {
            holder.title.setText(titles.getString(position));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return titles.length();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TitlesCharacterAdapter adapter;

        public ViewHolder(@NonNull View itemView, TitlesCharacterAdapter adapter) {
            super(itemView);
            this.title = itemView.findViewById(R.id.titleCharacter);
            this.adapter = adapter;
        }

    }

}

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
import com.example.desafio_mobits_android.view.DetailHouseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HouseListAdapter extends RecyclerView.Adapter<HouseListAdapter.ViewHolder> {

    private final JSONArray houses;
    private LayoutInflater mInflater;
    private Context context;

    public HouseListAdapter(Context context, JSONArray houses ){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.houses = houses;
    }

    @NonNull
    @Override
    public HouseListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.houseslist_item, parent, false);
        return new ViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HouseListAdapter.ViewHolder holder, int position) {
        try {
            JSONObject houseItem = new JSONObject(String.valueOf(houses.getJSONObject(position)));
            holder.houseName.setText(houseItem.getString("name"));
            holder.housePhrase.setText(houseItem.getString("words"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return houses.length();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView houseName;
        public TextView housePhrase;
        public HouseListAdapter adapter;

        public ViewHolder(@NonNull View itemView, HouseListAdapter adapter) {
            super(itemView);
            this.houseName = itemView.findViewById(R.id.houseName);
            this.housePhrase = itemView.findViewById(R.id.housePhrase);
            this.adapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition();
            try {
                JSONObject houseItem = new JSONObject(String.valueOf(houses.getJSONObject(position)));
                Intent intent = new Intent(context, DetailHouseActivity.class);
                intent.putExtra("house", houseItem.toString());
                context.startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}

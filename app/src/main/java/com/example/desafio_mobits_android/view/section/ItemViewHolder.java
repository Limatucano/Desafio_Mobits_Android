package com.example.desafio_mobits_android.view.section;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafio_mobits_android.R;

class ItemViewHolder extends RecyclerView.ViewHolder{

    public TextView characterName;
    public CardView characterCard;
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        this.characterName = itemView.findViewById(R.id.characterName);
        this.characterCard = itemView.findViewById(R.id.characterCard);
    }
}


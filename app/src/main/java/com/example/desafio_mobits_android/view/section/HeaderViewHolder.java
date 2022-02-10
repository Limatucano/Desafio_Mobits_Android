package com.example.desafio_mobits_android.view.section;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.desafio_mobits_android.R;

class HeaderViewHolder extends RecyclerView.ViewHolder{

    public TextView titleSection;
    public HeaderViewHolder(@NonNull View itemView) {
        super(itemView);
        this.titleSection = itemView.findViewById(R.id.titleSection);
    }
}


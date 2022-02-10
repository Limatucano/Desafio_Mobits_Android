package com.example.desafio_mobits_android.view.section;

import androidx.annotation.NonNull;

public interface ClickListener {

    void onItemRootViewClicked(@NonNull final CharacterSection section, final int itemAdapterPosition);
}
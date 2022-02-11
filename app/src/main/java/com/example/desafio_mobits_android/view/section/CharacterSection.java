package com.example.desafio_mobits_android.view.section;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafio_mobits_android.R;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

public class CharacterSection extends Section {
    private List<String> itemList;
    private final String title;
    private final ClickListener clickListener;

    public CharacterSection(@NonNull final String title, final List<String> item, final ClickListener clickListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.characterlist_item)
                .headerResourceId(R.layout.characterlist_header)
                .build());
        this.title = title;
        this.itemList = item;
        this.clickListener = clickListener;
    }

    @Override
    public int getContentItemsTotal() {
        return itemList.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemViewHolder itemHolder = (ItemViewHolder) holder;
        itemHolder.characterName.setText(itemList.get(position));

        itemHolder.characterCard.setOnClickListener(view -> {
            clickListener.onItemRootViewClicked(this, itemHolder.getAdapterPosition(), position, String.valueOf(itemList.get(position).charAt(0)));
        });
    }
    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(final View view) {
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(final RecyclerView.ViewHolder holder) {
        final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        headerHolder.titleSection.setText(title);
    }

}

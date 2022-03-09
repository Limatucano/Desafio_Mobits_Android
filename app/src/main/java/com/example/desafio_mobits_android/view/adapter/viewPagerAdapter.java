package com.example.desafio_mobits_android.view.adapter;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.example.desafio_mobits_android.view.booksFragment;
import com.example.desafio_mobits_android.view.housesFragment;

public class viewPagerAdapter extends FragmentStateAdapter {

    private final int NUM_PAGES = 2;
    private listenerViewPager listener;

    public viewPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle, listenerViewPager listener) {
        super(fragmentManager, lifecycle);
        this.listener = listener;
    }
    @Override
    public long getItemId(int position) {
        listener.getPositionItem(position);
        return position;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        //listener.getPositionItem(position);
        if(position == 1){
            return new housesFragment();
        }
        return new booksFragment();
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}

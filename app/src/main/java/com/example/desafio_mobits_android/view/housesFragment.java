package com.example.desafio_mobits_android.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.desafio_mobits_android.R;
import com.example.desafio_mobits_android.data.FetchHouses;
import com.example.desafio_mobits_android.databinding.FragmentBooksBinding;
import com.example.desafio_mobits_android.databinding.FragmentHousesBinding;

public class housesFragment extends Fragment {

    private FragmentHousesBinding binding;

    public housesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHousesBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle bundle){

        new FetchHouses(binding.housesRecycler,getContext()).execute();
    }
}
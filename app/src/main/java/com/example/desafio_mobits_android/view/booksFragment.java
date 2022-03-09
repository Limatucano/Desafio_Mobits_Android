package com.example.desafio_mobits_android.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.desafio_mobits_android.R;
import com.example.desafio_mobits_android.data.FetchBooks;
import com.example.desafio_mobits_android.databinding.FragmentBooksBinding;


public class booksFragment extends Fragment {

    private FragmentBooksBinding binding;
    public booksFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBooksBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle bundle){
        super.onViewCreated(view, bundle);

        new FetchBooks(binding.booksRecycler, getContext()).execute();
    }
}
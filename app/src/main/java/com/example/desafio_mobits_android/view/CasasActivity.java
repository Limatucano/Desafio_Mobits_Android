package com.example.desafio_mobits_android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.desafio_mobits_android.R;
import com.example.desafio_mobits_android.data.FetchHouses;
import com.example.desafio_mobits_android.databinding.ActivityCasasBinding;
import com.example.desafio_mobits_android.databinding.ActivityMainBinding;

public class CasasActivity extends AppCompatActivity {

    private ActivityCasasBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCasasBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBack.setOnClickListener(view -> onBackPressed());

        new FetchHouses(binding.housesRecycler,this).execute();
    }
}
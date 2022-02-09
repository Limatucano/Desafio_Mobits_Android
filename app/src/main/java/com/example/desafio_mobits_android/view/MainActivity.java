package com.example.desafio_mobits_android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.desafio_mobits_android.R;
import com.example.desafio_mobits_android.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBooks.setOnClickListener(view -> {
            Intent intent = new Intent(this, LivrosActivity.class);
            startActivity(intent);
        });

        binding.buttonHouses.setOnClickListener(view -> {
            Intent intent = new Intent(this, CasasActivity.class);
            startActivity(intent);
        });
    }
}
package com.example.desafio_mobits_android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.desafio_mobits_android.databinding.ActivityDetailCharacterBinding;

public class DetailCharacterActivity extends AppCompatActivity {
    private ActivityDetailCharacterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailCharacterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBack.setOnClickListener(view -> onBackPressed());


        Log.d("TESTE",getIntent().getStringExtra("idCharacter"));

    }
}
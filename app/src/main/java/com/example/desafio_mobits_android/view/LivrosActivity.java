package com.example.desafio_mobits_android.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.desafio_mobits_android.data.FetchBooks;
import com.example.desafio_mobits_android.databinding.ActivityLivrosBinding;

public class LivrosActivity extends AppCompatActivity {
    private ActivityLivrosBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLivrosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBack.setOnClickListener(view -> {
            onBackPressed();
        });

        new FetchBooks(binding.teste).execute();
    }

}
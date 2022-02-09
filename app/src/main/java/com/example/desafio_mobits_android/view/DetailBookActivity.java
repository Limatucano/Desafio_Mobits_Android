package com.example.desafio_mobits_android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.desafio_mobits_android.databinding.ActivityDetailBookBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailBookActivity extends AppCompatActivity {
    private ActivityDetailBookBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBack.setOnClickListener(view -> {
            onBackPressed();
        });
        try{
            JSONObject book = new JSONObject(getIntent().getStringExtra("book"));
            String numbersPage = "Páginas: " + book.getInt("numberOfPages");
            String isbn = "Isbn: " + book.getString("isbn");
            String released = "Lançamento: " + book.getString("released");
            binding.titleBook.setText(book.getString("name"));
            binding.isbnBook.setText(isbn);
            binding.pagesBook.setText(numbersPage);
            binding.releasedBook.setText(released);
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
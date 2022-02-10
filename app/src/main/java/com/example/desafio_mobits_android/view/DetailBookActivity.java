package com.example.desafio_mobits_android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.desafio_mobits_android.databinding.ActivityDetailBookBinding;

import org.json.JSONArray;
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
        JSONObject book = null;
        try{
            book = new JSONObject(getIntent().getStringExtra("book"));
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

        JSONObject finalBook = book;
        binding.buttonSeeAllCharacters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailBookActivity.this, AllCharactersActivity.class);
                JSONArray characters = null;
                if (finalBook != null) {
                    try {
                        characters = finalBook.getJSONArray("characters");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(characters != null) {
                    intent.putExtra("characters", characters.toString());
                }

                startActivity(intent);
            }
        });
    }
}
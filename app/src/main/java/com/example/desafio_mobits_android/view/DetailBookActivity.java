package com.example.desafio_mobits_android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.desafio_mobits_android.databinding.ActivityDetailBookBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailBookActivity extends AppCompatActivity {
    private ActivityDetailBookBinding binding;
    private String title = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBack.setOnClickListener(view -> {
            onBackPressed();
        });
        binding.buttonSearch.setOnClickListener(view -> {
            String url = "https://www.google.com/search?tbm=isch&q=" + title ;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });

        JSONObject book = null;
        try{
            book = new JSONObject(getIntent().getStringExtra("book"));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            String releasedDate = format.format(parser.parse(book.getString("released")));
            String numbersPage = "Páginas: " + book.getInt("numberOfPages");
            String isbn = "Isbn: " + book.getString("isbn");
            String released = "Lançamento: " + releasedDate;
            title = book.getString("name");
            binding.titleBook.setText(book.getString("name"));
            binding.isbnBook.setText(isbn);
            binding.pagesBook.setText(numbersPage);
            binding.releasedBook.setText(released);
        }catch(JSONException e){
            e.printStackTrace();
        }catch(ParseException e){
            e.printStackTrace();
        }

        JSONObject finalBook = book;

        binding.buttonSeePovCharacters.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailBookActivity.this, AllCharactersActivity.class);
                JSONArray povCharacters = null;
                if(finalBook != null){
                    try {
                        povCharacters = finalBook.getJSONArray("povCharacters");
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
                if(povCharacters != null){
                    intent.putExtra("characters", povCharacters.toString());
                }
                startActivity(intent);
            }
        });

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
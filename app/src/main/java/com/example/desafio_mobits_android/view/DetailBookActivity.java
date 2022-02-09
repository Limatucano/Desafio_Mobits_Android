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

        try{
            JSONObject book = new JSONObject(getIntent().getStringExtra("book"));
            Log.d("TESTE", "teste");
        }catch(JSONException e){
            e.printStackTrace();
        }
    }
}
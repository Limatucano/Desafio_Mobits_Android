package com.example.desafio_mobits_android.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.desafio_mobits_android.databinding.ActivityAllCharactersBinding;
import com.example.desafio_mobits_android.view.section.CharacterSection;
import com.example.desafio_mobits_android.view.section.ClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

public class AllCharactersActivity extends AppCompatActivity implements ClickListener {
    private ActivityAllCharactersBinding binding;
    private SectionedRecyclerViewAdapter sectionedAdapter;
    private JSONObject charactersJson;
    private HashMap<String, List<String>> charactersList;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllCharactersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBack.setOnClickListener(view -> {
            onBackPressed();
        });

        String characters = getJSONData(this,"personagens_key_value.json");
        try {
            JSONArray charactersUrl = new JSONArray(getIntent().getStringExtra("characters"));
            charactersJson = new JSONObject(characters);
            charactersList = new HashMap<String, List<String>>();
            for(int i=0;i < charactersUrl.length(); i++){
                String character = charactersJson.getString(charactersUrl.getString(i));
                if(charactersList.containsKey(String.valueOf(character.charAt(0)))){
                    charactersList.get(String.valueOf(character.charAt(0))).add(character);
                }else{
                    LinkedList<String> firstCharacter = new LinkedList<String>();
                    firstCharacter.add(character);
                    charactersList.put(String.valueOf(character.charAt(0)),firstCharacter);
                }
            }
            sectionedAdapter = new SectionedRecyclerViewAdapter();

            for (final Map.Entry<String, List<String>> entry : charactersList.entrySet()){
                if(entry.getValue().size() > 0){
                    Collections.sort(entry.getValue());
                    sectionedAdapter.addSection(new CharacterSection(entry.getKey(), entry.getValue(),this));
                }
            }
            binding.charactersRecycler.setLayoutManager(new LinearLayoutManager(this));
            binding.charactersRecycler.setAdapter(sectionedAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public static String getJSONData(Context context, String textFileName) {
        String strJSON;
        StringBuilder buf = new StringBuilder();
        InputStream json;
        try {
            json = context.getAssets().open(textFileName);

            BufferedReader in = new BufferedReader(new InputStreamReader(json, "UTF-8"));

            while ((strJSON = in.readLine()) != null) {
                buf.append(strJSON);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buf.toString();
    }

    @Override
    public void onItemRootViewClicked(@NonNull CharacterSection section, int itemAdapterPosition, int position, String characterLetter) {
        try {
            String characterByName = getJSONData(this,"personagens_by_name.json");
            JSONObject characterByNameJSON = new JSONObject(characterByName);
            String characterClicked = charactersList.get(characterLetter).get(position);
            String idCharacter = characterByNameJSON.getString(characterClicked);
            Log.d("TESTE", idCharacter);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
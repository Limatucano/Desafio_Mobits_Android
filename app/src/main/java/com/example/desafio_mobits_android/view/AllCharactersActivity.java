package com.example.desafio_mobits_android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.desafio_mobits_android.databinding.ActivityAllCharactersBinding;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class AllCharactersActivity extends AppCompatActivity {
    private ActivityAllCharactersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllCharactersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String characters = getJSONData(this,"personagens_key_value.json");

        try {
            JSONArray charactersUrl = new JSONArray(getIntent().getStringExtra("characters"));
            JSONObject charactersJson = new JSONObject(characters);
            LinkedHashMap<String, LinkedList<String>> charactersList = new LinkedHashMap<String, LinkedList<String>>();
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
}
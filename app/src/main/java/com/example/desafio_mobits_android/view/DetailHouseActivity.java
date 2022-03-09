package com.example.desafio_mobits_android.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.regex.*;
import com.example.desafio_mobits_android.databinding.ActivityDetailHouseBinding;
import com.example.desafio_mobits_android.view.adapter.TitlesCharacterAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DetailHouseActivity extends AppCompatActivity {
    private ActivityDetailHouseBinding binding;
    private String houseValue = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailHouseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBack.setOnClickListener(view -> onBackPressed());

        binding.buttonSearch.setOnClickListener(view -> {
            Intent intent = new Intent(this,WebViewActivity.class);
            intent.putExtra("name",houseValue);
            startActivity(intent);
        });
        JSONObject house = null;
        try{
            house = new JSONObject(getIntent().getStringExtra("house"));
            houseValue = house.getString("name");
            binding.houseName.setText(house.getString("name"));
            binding.housePhrase.setText(house.getString("words"));
            binding.houseRegion.setText(house.getString("region"));
            JSONArray titles = new JSONArray(house.getString("titles"));
            TitlesCharacterAdapter mAdapter = new TitlesCharacterAdapter(this, titles);
            binding.titlesCharacter.setAdapter(mAdapter);
            binding.titlesCharacter.setLayoutManager(new LinearLayoutManager(this));

        }catch(JSONException e){
            e.printStackTrace();
        }

        JSONObject finalHouse = house;

        binding.buttonLord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String idCurrentLord = getCharacterId(finalHouse.getString("currentLord"));
                    Intent intent = new Intent(DetailHouseActivity.this, DetailCharacterActivity.class);
                    intent.putExtra("idCharacter", idCurrentLord);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        binding.buttonHeir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String idHeir = getCharacterId(finalHouse.getString("heir"));
                    Intent intent = new Intent(DetailHouseActivity.this, DetailCharacterActivity.class);
                    intent.putExtra("idCharacter", idHeir);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private String getCharacterId(String url){
        Pattern pattern = Pattern.compile("(?<=/characters/).*");
        Matcher matcher = pattern.matcher(url);
        if(matcher.find()){
            return matcher.group();
        }
        return "";
    }
}
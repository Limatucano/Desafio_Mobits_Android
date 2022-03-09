package com.example.desafio_mobits_android.data;

import static com.example.desafio_mobits_android.data.NetworkUtils.BASE_URL;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafio_mobits_android.view.adapter.BookListAdapter;
import com.example.desafio_mobits_android.view.adapter.TitlesCharacterAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;

public class FetchCharacter extends AsyncTask<String, Void, String> {

    private Context context;
    private String idCharacter;
    private TextView nameCharacter;
    private TextView sexCharacter;
    private TextView bornCharacter;
    private RecyclerView titlesCharacter;
    private TextView titlesLabel;
    private ImageView buttonSearch;
    private TitlesCharacterAdapter mAdapter;

    public FetchCharacter(String idCharacter,
                          TextView nameCharacter,
                          TextView sexCharacter,
                          TextView bornCharacter,
                          RecyclerView titlesCharacter,
                          Context context,
                          TextView titlesLabel,
                          ImageView buttonSearch
    ){
        this.idCharacter = idCharacter;
        this.nameCharacter = nameCharacter;
        this.sexCharacter = sexCharacter;
        this.bornCharacter = bornCharacter;
        this.titlesCharacter = titlesCharacter;
        this.context = context;
        this.titlesLabel = titlesLabel;
        this.buttonSearch = buttonSearch;
    }
    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject response = new JSONObject(s);
            nameCharacter.setText(response.getString("name"));
            sexCharacter.setText("Sexo: " + response.getString("gender"));
            bornCharacter.setText("Nascimento: " + response.getString("born"));
            buttonSearch.setVisibility(View.VISIBLE);

            JSONArray titles = new JSONArray(response.getString("titles"));
            if(titles.get(0).toString().equals("")){
                titlesLabel.setVisibility(View.GONE);
                titlesCharacter.setVisibility(View.GONE);
            }
            mAdapter = new TitlesCharacterAdapter(context, titles);
            titlesCharacter.setAdapter(mAdapter);
            titlesCharacter.setLayoutManager(new LinearLayoutManager(context));
        }catch (JSONException e) {
            e.printStackTrace();
        }
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... strings) {
        Uri builtURI = Uri.parse(BASE_URL).buildUpon()
        .appendPath("characters")
        .appendPath(idCharacter)
        .build();
        String requestMethod = "GET";
        return NetworkUtils.request(builtURI, requestMethod);
    }
}

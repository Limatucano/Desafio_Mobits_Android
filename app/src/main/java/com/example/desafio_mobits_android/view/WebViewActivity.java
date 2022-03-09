package com.example.desafio_mobits_android.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;

import com.example.desafio_mobits_android.R;
import com.example.desafio_mobits_android.databinding.ActivityDetailHouseBinding;
import com.example.desafio_mobits_android.databinding.ActivityWebViewBinding;

public class WebViewActivity extends AppCompatActivity {
    private ActivityWebViewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonBack.setOnClickListener(view -> onBackPressed());

        String url = "https://www.google.com/search?tbm=isch&q=" + getIntent().getStringExtra("name") ;
        WebSettings webSettings = binding.webview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        binding.webview.loadUrl(url);



    }
}
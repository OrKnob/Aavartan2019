package com.technocracy.app.aavartan.ui.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.utils.AppConstants;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        String URL = getIntent().getStringExtra(AppConstants.WEBVIEW_EXTRA);
        initView();
        loadURL(URL);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView(){

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


    }

    private void loadURL(String URL){

        webView.loadUrl(URL);

    }

}

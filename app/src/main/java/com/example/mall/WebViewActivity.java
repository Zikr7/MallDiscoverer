package com.example.mall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_web_view);

        webView = findViewById(R.id.webView);

        // Enable JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        // Set WebViewClient to handle links within the WebView
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });

        // Set WebChromeClient for handling progress changes, alert dialogs, etc.
        webView.setWebChromeClient(new WebChromeClient());

        // Retrieve the URL from the intent
        String url = getIntent().getStringExtra("url");

        // Load the URL in the WebView
        if (url != null) {
            webView.loadUrl(url);
        }
    }

    @Override
    public void onBackPressed() {
        // Handle back button press within the WebView
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

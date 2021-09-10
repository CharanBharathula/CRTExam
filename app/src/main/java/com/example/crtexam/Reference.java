package com.example.crtexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Reference extends AppCompatActivity {

    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);
        webview=findViewById(R.id.wv);
        webview.setWebViewClient(new MyBrowser());

        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://www.indiabix.com");
    }

    private class MyBrowser extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(webview.canGoBack())
            webview.goBack();
    }
}

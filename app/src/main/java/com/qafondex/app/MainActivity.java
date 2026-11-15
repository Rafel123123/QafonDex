package com.qafondex.app;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings;
import android.webkit.JavascriptInterface;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends Activity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(
            new GameBridge(), "Android"
        );

        setContentView(webView);
        webView.loadUrl("file:///android_asset/index.html");
    }

    public class GameBridge {
        @JavascriptInterface
        public void openGame() {
            runOnUiThread(() -> {
                Intent intent = getPackageManager()
                    .getLaunchIntentForPackage(
                        "com.dts.freefireth"
                    );

                if (intent != null) {
                    startActivity(intent);
                } else {
                    Toast.makeText(
                        MainActivity.this,
                        "Free Fire belum terpasang",
                        Toast.LENGTH_LONG
                    ).show();
                }
            });
        }
    }
}

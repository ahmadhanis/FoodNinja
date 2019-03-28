package com.slumberjer.foodninja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Random;

public class PaymentActivity extends AppCompatActivity {
WebView webView;
String userid,name,phone,total,orderid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        userid = bundle.getString("userid");
        name = bundle.getString("name");
        phone = bundle.getString("phone");
        total = bundle.getString("total");
        orderid = bundle.getString("orderid");
        WebView simpleWebView = (WebView) findViewById(R.id.wbview);
        simpleWebView.setWebViewClient(new MyWebViewClient());
        Random rand = new Random();

        String url = "http://uumresearch.com/foodninja/php/payment.php?" +
                "userid="+userid+"&mobile="+phone+"&name="+name+"&amount="+total+"&orderid="+orderid;
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(url); // load the url on the web view
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url); // load the url
            return true;
        }

    }
}

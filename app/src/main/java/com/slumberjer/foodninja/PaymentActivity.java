package com.slumberjer.foodninja;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.Random;

public class PaymentActivity extends AppCompatActivity {
String userid,name,phone,total,orderid;
WebView simpleWebView;
boolean loadingFinished = true;
boolean redirect = false;
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
        simpleWebView = (WebView) findViewById(R.id.wbview);
        simpleWebView.setWebViewClient(new MyWebViewClient());

        String url = "http://uumresearch.com/foodninja/php/payment.php?" +
                "userid="+userid+"&mobile="+phone+"&name="+name+"&amount="+total+"&orderid="+orderid;
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(url); // load the url on the web view
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.e("HANIS","shouldOverrideUrlLoading");

            if (!loadingFinished) {
                redirect = true;
            }
            loadingFinished = false;
            view.loadUrl(url); // load the url
            return true;
        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap facIcon) {
            Log.e("HANIS","onPageStarted");
            loadingFinished = false;

            //SHOW LOADING IF IT ISNT ALREADY VISIBLE
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.e("HANIS","onPageFinished");
            if(!redirect){
                loadingFinished = true;
            }

            if(loadingFinished && !redirect){
                //HIDE LOADING IT HAS FINISHED
                //Toast.makeText(PaymentActivity.this, "Loading completed", Toast.LENGTH_SHORT).show();
            } else{
                redirect = false;
                Toast.makeText(PaymentActivity.this, "Redirecting..", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_payment, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mnu_refresh:
                loadPayment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadPayment() {
        String url = "http://uumresearch.com/foodninja/php/payment.php?" +
                "userid="+userid+"&mobile="+phone+"&name="+name+"&amount="+total+"&orderid="+orderid;
        simpleWebView.getSettings().setJavaScriptEnabled(true);
        simpleWebView.loadUrl(url); // load the url on the web view
    }

}

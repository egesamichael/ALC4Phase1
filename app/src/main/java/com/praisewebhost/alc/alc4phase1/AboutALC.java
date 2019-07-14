package com.praisewebhost.alc.alc4phase1;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class AboutALC extends AppCompatActivity {

    //Setting the URL into a string variable
    String url = "https://andela.com/alc/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        toolbar.setTitle("About ALC");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //Getting the webview
        final WebView myWebView = (WebView) findViewById(R.id.mywebview);

        //Getting the Progress bar to show theuser that the page is loading
        final ProgressDialog progressDialog = ProgressDialog.show(this, "", "Please wait, while we load the ALC experience...", true);

        //Enabling Javascript for our web view
        myWebView.getSettings().setJavaScriptEnabled(true);

        //Disabling the scroll bars on the web view
        myWebView.setHorizontalScrollBarEnabled(false);
        myWebView.setVerticalScrollBarEnabled(false);

        //Creating a web view client
        myWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(AboutALC.this, description, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressDialog.show();
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                progressDialog.dismiss();

                String webUrl = myWebView.getUrl();
            }


            //Checking for SSL Error
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                // ignore ssl error
                if (handler != null) {
                    handler.proceed();
                } else {
                    super.onReceivedSslError(view, null, error);
                }
            }


        });


        //Loading the URL now

        myWebView.loadUrl(url);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

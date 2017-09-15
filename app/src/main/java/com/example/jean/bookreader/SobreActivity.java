package com.example.jean.bookreader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;


public class SobreActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*mWebView = (WebView) findViewById(R.id.about);

        String text = "<html><body>"
                + "<p align=\"justify\">"
                +  getString(R.string.app_desc)
                + "</p> "
                + "</body></html>";

        mWebView.loadData(text, "text/html", "utf-8");*/

        //android.app.ActionBar actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void login(View v) {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }

    public void abreSite(View v)
    {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bookreader.16mb.com")));
    }
}


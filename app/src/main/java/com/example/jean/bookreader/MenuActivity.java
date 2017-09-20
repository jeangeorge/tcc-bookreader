package com.example.jean.bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setMessage("Bem vindo " + name + "!!")
                .setNegativeButton("OK", null)
                .create()
                .show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void startCamera(View v)
    {
        Intent camera = new Intent(this, CameraActivity.class);
        startActivity(camera);
    }
    public void startPdf(View v)
    {
        Intent pdf = new Intent(this, PDFReaderActivity.class);
        startActivity(pdf);
    }
}


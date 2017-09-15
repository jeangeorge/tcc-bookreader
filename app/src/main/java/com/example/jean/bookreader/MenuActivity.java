package com.example.jean.bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MenuActivity extends AppCompatActivity  {
    TextToSpeech narrador;
    boolean a = true, b = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");

        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setMessage("Bem vindo " + name + "!!")
                .setNegativeButton("OK", null)
                .create()
                .show();


        narrador = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    //Quando inicia o narrador utiliza a linguagem do portugues brasileiro
                    Locale myLocale = new Locale("pt", "PT");

                    //Seta a localidade
                    narrador.setLanguage(myLocale);
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onStart() {
        a = true;
        super.onStart();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*
        switch(MotionEventCompat.getActionMasked(event))
        {
            case MotionEvent.ACTION_DOWN:
                if(a)
                if(event.getPointerCount() == 3) {
                    narrador.speak("Bem vindo ao BookReader. Clique com um dedo se voce for deficiente visual e com dois se não for. Toque com 3 dedos para repetir.", TextToSpeech.QUEUE_FLUSH, null);
                }
                else if(event.getPointerCount() == 2)
                {
                    Intent agradecimento = new Intent(this, MainActivity.class);
                    startActivity(agradecimento);
                }
                else if(event.getPointerCount() == 1)
                {
                    if(b) {
                        narrador.speak("Bem vindo ao BookReader. Clique com um dedo se voce for deficiente visual e com dois se não for. Toque com 3 dedos para repetir.", TextToSpeech.QUEUE_FLUSH, null);
                        b = false;
                    }
                    else {
                        Intent agradecimento = new Intent(this, MenuVisualActivity.class);
                        startActivity(agradecimento);
                    }

                }
                a = false;
                break;
            case MotionEvent.ACTION_UP:
                a = true;
                break;
        }*/
        return super.onTouchEvent(event);
    }

    public void startCamera(View v)
    {
        Intent camera = new Intent(this, CameraActivity.class);
        startActivity(camera);
    }
}


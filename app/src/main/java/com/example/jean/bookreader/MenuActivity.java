package com.example.jean.bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jean.bookreader.BD.Usuario;
import com.example.jean.bookreader.BD.UsuarioSingleton;

public class MenuActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Intent intent = getIntent();
        //String name = intent.getStringExtra("name");
        Usuario usuario = UsuarioSingleton.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
        builder.setMessage("Bem vindo " + usuario.nome + "!!")
                .setNegativeButton("OK", null)
                .create()
                .show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void startCamera(View v)
    {
        Intent menuCamera = new Intent(this, MenuCameraActivity.class);
        startActivity(menuCamera);
    }
    public void startPdf(View v)
    {
        Intent pdf = new Intent(this, PDFReaderActivity.class);
        startActivity(pdf);
    }

    public void startAudioLivro(View v)
    {
        Intent audio = new Intent(this, AudioLivroActivity.class);
        startActivity(audio);
    }

    public void startListaDeLivros(View v)
    {
        Intent audio = new Intent(this, LivrosActivity.class);
        startActivity(audio);
    }
}


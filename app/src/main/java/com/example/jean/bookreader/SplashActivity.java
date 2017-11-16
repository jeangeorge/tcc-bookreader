package com.example.jean.bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.jean.bookreader.BD.Usuario;
import com.example.jean.bookreader.BD.UsuarioRepositorio;
import com.example.jean.bookreader.BD.UsuarioSingleton;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    // Timer da splash screen
    private static int SPLASH_TIME_OUT = 3000;
    UsuarioRepositorio usuarioRepositorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
            @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                usuarioRepositorio = new UsuarioRepositorio(getApplicationContext());
                List<Usuario> lista = usuarioRepositorio.buscarUsuario(null);
                if(lista.size() > 0)
                {
                    UsuarioSingleton user = new UsuarioSingleton(lista.get(0));
                    Intent i = new Intent(SplashActivity.this, MenuActivity.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                }

                // Fecha esta activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}

package com.example.jean.bookreader;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jean.bookreader.BD.Usuario;
import com.example.jean.bookreader.BD.UsuarioRepositorio;
import com.example.jean.bookreader.BD.UsuarioSingleton;

public class MenuActivity extends AppCompatActivity  {
    public boolean logado = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Intent intent = getIntent();
        //String name = intent.getStringExtra("name");
        if(UsuarioSingleton.getInstance() !=  null) {
            Usuario usuario = UsuarioSingleton.getInstance();
            AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
            builder.setMessage("Bem vindo " + usuario.nome + "!!")
                    .setNegativeButton("OK", null)
                    .create()
                    .show();
        }

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        logado = true;
        switch(item.getItemId())
        {

            case R.id.sobre_item:
                Intent sobre = new Intent(this, SobreActivity.class);
                startActivity(sobre);
                break;
            case R.id.sair_item:
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                builder.setMessage("Tem certeza que deseja deslogar?")
                        .setNegativeButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                UsuarioRepositorio repositorio = new UsuarioRepositorio(getApplicationContext());

                               /* String soma = "";
                                List<Usuario> lista = repositorio.buscarUsuario( null);
                                for(int i = 0; i < lista.size(); i++)
                                {
                                    soma+=lista.get(i).email + ", " + lista.get(i).nome + ", " + lista.get(i).ID;
                                }
                                AlertDialog.Builder teste = new AlertDialog.Builder(MenuActivity.this);
                                teste.setMessage(soma);
                                teste
                                        .setNegativeButton("Ok", null)
                                        .create()
                                        .show();
                                */
                                if(repositorio.buscarUsuario( null).size() < 1)
                                {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                                    builder.setMessage("Você não está Logado");
                                    builder
                                            .setNegativeButton("Ok", null)
                                            .create()
                                            .show();
                                }
                                else {
                                    repositorio.excluir(UsuarioSingleton.getInstance());
                                    Intent sair = new Intent(MenuActivity.this, LoginActivity.class);
                                    UsuarioSingleton.Sair();
                                    startActivity(sair);
                                    try {
                                        finish();
                                    } catch (Throwable throwable) {
                                        throwable.printStackTrace();
                                    }
                                }



                            }
                        })
                        .setNeutralButton("Não", null)
                        .create()
                        .show();
                break;
        }

        return super.onOptionsItemSelected(item);
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
        Intent audio = new Intent(this, PlayerLivroActivity.class);
        startActivity(audio);
    }
}



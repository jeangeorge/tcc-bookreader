package com.example.jean.bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jean.bookreader.BD.Livro;
import com.example.jean.bookreader.BD.LivroRepositorio;
import com.example.jean.bookreader.BD.Pagina;

import java.util.List;

public class PlayerLivroActivity extends AppCompatActivity {
    int SELECIONA_LIVRO = 1;
    TextView nomeLivro;
    Livro livroAtual;
    TextView qtdPaginas;
    List<Pagina> paginas;
    LivroRepositorio repositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_livro);
        nomeLivro = (TextView) findViewById(R.id.nomeLivro);
        qtdPaginas = (TextView) findViewById(R.id.paginasLivro);
        Intent escolheLivro = new Intent(PlayerLivroActivity.this, LivrosActivity.class);
        startActivityForResult(escolheLivro, SELECIONA_LIVRO);
        repositorio = new LivroRepositorio(getApplicationContext());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == SELECIONA_LIVRO)
        {
            nomeLivro.setText(data.getStringExtra("nomeLivro"));
            livroAtual = new Livro(data.getLongExtra("idLivro", 0),data.getStringExtra("nomeLivro"));

            AlertDialog.Builder builder = new AlertDialog.Builder(PlayerLivroActivity.this);
            builder.setMessage("" + livroAtual.ID)
                    .setNegativeButton("Ok", null)
                    .create()
                    .show();

            paginas = repositorio.getPaginas(livroAtual.ID);
            qtdPaginas.setText("1/" + paginas.size());
        }
    }

    public void escolherLivro(View v)
    {
        Intent escolheLivro = new Intent(PlayerLivroActivity.this, LivrosActivity.class);
        startActivityForResult(escolheLivro, SELECIONA_LIVRO);
    }

    public void anteriorLivro(View v)
    {

    }

    public void proximoLivro(View v)
    {

    }

    public void pauseLivro(View v)
    {

    }



}

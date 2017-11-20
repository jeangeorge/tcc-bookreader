package com.example.jean.bookreader;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.jean.bookreader.BD.Livro;
import com.example.jean.bookreader.BD.LivroRepositorio;

import java.util.List;

public class MenuScannerActivity extends AppCompatActivity {
    private int SELECIONA_LIVRO = 2;
    LivroRepositorio repositorio;
    public Livro livro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_scanner);
        repositorio = new LivroRepositorio(getApplicationContext());
    }
    public void novoLivro(View v)
    {
        EditText text = (EditText) findViewById(R.id.editText2);

        livro = new Livro(text.getText().toString());
        List<Livro> livros = repositorio.buscarLivros(livro.nome);
        if(livros.size() == 0)
        {
            repositorio.novoLivro(livro);
            livros = repositorio.buscarLivros(livro.nome);
            livro = livros.get(0);
            Intent it = new Intent(MenuScannerActivity.this, ScaneiaLivroActivity.class);
            it.putExtra("idLivro", (int)livro.ID);
            it.putExtra("nomeLivro", livro.nome);
            startActivity(it);
        }
        else
        {
            livro = livros.get(0);
            AlertDialog.Builder builder = new AlertDialog.Builder(MenuScannerActivity.this);
            builder.setMessage("Já existe um livro cadastrado com esse nome. Deseja adicionar novas páginas à esse livro?")
                    .setNegativeButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent it = new Intent(MenuScannerActivity.this, ScaneiaLivroActivity.class);
                            it.putExtra("idLivro", (int)livro.ID);
                            it.putExtra("nomeLivro", livro.nome);
                            startActivity(it);
                        }
                    })
                    .setNeutralButton("Não", null)
                    .create()
                    .show();
        }


    }
    public void abrirLista(View v)
    {
        Intent it = new Intent(MenuScannerActivity.this, LivrosActivity.class);
        startActivityForResult(it, SELECIONA_LIVRO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == SELECIONA_LIVRO)
        {
            //nomeLivro.setText(data.getStringExtra("nomeLivro"));
            livro = new Livro(data.getLongExtra("idLivro", 0),data.getStringExtra("nomeLivro"));
            Intent it = new Intent(MenuScannerActivity.this, ScaneiaLivroActivity.class);
            it.putExtra("idLivro", (int)livro.ID);
            it.putExtra("nomeLivro", livro.nome);
            startActivity(it);
        }
    }
}

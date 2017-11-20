package com.example.jean.bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jean.bookreader.BD.Livro;
import com.example.jean.bookreader.BD.LivroRepositorio;

import java.util.List;

public class LivrosActivity extends AppCompatActivity {
    LivroRepositorio repositorio;
    List<Livro> livros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livros);
        repositorio = new LivroRepositorio(getApplicationContext());


        livros = repositorio.buscarLivros(null);

        ListView listaDeCursos = (ListView) findViewById(R.id.lista);

        ArrayAdapter<Livro> adapter = new ArrayAdapter<Livro>(this, android.R.layout.simple_list_item_1, livros);

        listaDeCursos.setAdapter(adapter);


        listaDeCursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("nomeLivro", livros.get(position).nome);
                returnIntent.putExtra("idLivro", livros.get(position).ID);
                setResult(RESULT_OK,returnIntent);
                finish();
            }});

    }



}

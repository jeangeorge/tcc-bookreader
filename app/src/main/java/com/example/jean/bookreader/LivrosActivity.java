package com.example.jean.bookreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jean.bookreader.BD.Livro;
import com.example.jean.bookreader.BD.LivroRepositorio;

import java.util.List;

public class LivrosActivity extends AppCompatActivity {
    LivroRepositorio repositorio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livros);
        repositorio = new LivroRepositorio(getApplicationContext());


        List<Livro> cursos = repositorio.buscarLivros(null);

        ListView listaDeCursos = (ListView) findViewById(R.id.lista);

        ArrayAdapter<Livro> adapter = new ArrayAdapter<Livro>(this,
                android.R.layout.simple_list_item_1, cursos);

        listaDeCursos.setAdapter(adapter);
    }



}

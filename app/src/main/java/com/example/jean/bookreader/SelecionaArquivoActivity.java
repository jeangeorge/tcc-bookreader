package com.example.jean.bookreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SelecionaArquivoActivity extends AppCompatActivity {
    private File file;
    private List<File> minhaLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleciona_arquivo);

        ListView listView = (ListView) findViewById(R.id.lista_arquivos);

        minhaLista = new ArrayList<File>();

        //String root_sd = Environment.getExternalStorageDirectory().toString();//Environment.dire; //Environment.getExternalStorageDirectory().toString();

        file = new File("sdcard");//root_sd ) ;
        File list[] = file.listFiles();

        for( int i=0; i< list.length; i++)
        {
            minhaLista.add( list[i] );
        }
        ArrayAdapter<File> adapter = new ArrayAdapter<File>(this, android.R.layout.simple_list_item_1, minhaLista);

        listView.setAdapter(adapter);

        /*List<Livro> cursos = repositorio.buscarLivros(null);

        ListView listaDeCursos = (ListView) findViewById(R.id.lista);

        ArrayAdapter<Livro> adapter = new ArrayAdapter<Livro>(this,
                android.R.layout.simple_list_item_1, cursos);

        listaDeCursos.setAdapter(adapter);*/
    }
}

package com.example.jean.bookreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jean.bookreader.BD.*;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MostraTextoActivity extends AppCompatActivity {
    private static int ID;
    TextView textViewConteudo;
    EditText editTextData;
    EditText editTextDescricao;
    private AppDbHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_texto);

        Intent i = getIntent();
        String textoEscaneado = i.getExtras().getString("textoEscaneado");

        textViewConteudo = (TextView) findViewById(R.id.textoVindoDaCamera);
        textViewConteudo.setText(textoEscaneado);

        // Find all relevant views that we will need to read user input from
       // textViewConteudo.setText(CameraActivity.getTextoEscaneado());

    }

    public void mostrarTexto(String texto)
    {
        // textViewConteudo.setText(CameraActivity.getTextoEscaneado());
    }

    public void salvarTexto(View v){
        insertTexto();
    }
    private void insertTexto(){
        //trim remove the blanck spaces/
        String conteudo = textViewConteudo.getText().toString().trim();


        // Create and/or open a database to write from it
        AppDbHelper mDbHelper = new AppDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //create the object no be included
        ContentValues values = new ContentValues();
        values.put(PaginaContract.PaginaEntry._ID, ID);
        values.put(PaginaContract.PaginaEntry.COLUMM_NUMEROPAGINA, 0);
        values.put(PaginaContract.PaginaEntry.COLUMN_CONTEUDO, conteudo);

        long newRowId = db.insert(PaginaContract.PaginaEntry.TABLE_NAME, null, values);

        if (newRowId==-1){
            Toast.makeText(this, "Erro ao salvar o texto.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Texto salvo com sucesso!", Toast.LENGTH_SHORT).show();

        }

    }

}

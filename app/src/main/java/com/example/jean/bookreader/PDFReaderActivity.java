package com.example.jean.bookreader;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class PDFReaderActivity extends AppCompatActivity {
    final int REQUEST_PDF = 1;
    Leitor leitor;
    ModuloPDF pdfReader;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfreader);
        selectPdf();
        imageView = (ImageView) findViewById(R.id.imageViewPDF);
        try {
            leitor = new Leitor(getApplicationContext());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);


    }
    public void selectPdf() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("pdf/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_PDF);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PDF && resultCode == RESULT_OK) {
            Uri uri = data.getData();

            pdfReader = new ModuloPDF(uri.getPath());
           imageView.setImageBitmap(pdfReader.getImagem());
            leitor.setImagem(pdfReader.getImagem());
            leitor.LerImagem();
        }
    }
    public void Proximo(View v)
    {
        leitor.Zerar();
        pdfReader.ProximaPagina();
        imageView.setImageBitmap(pdfReader.getImagem());
        leitor.setImagem(pdfReader.getImagem());
        leitor.LerImagem();
    }
    public void Anterior(View v)
    {
        leitor.Zerar();
        pdfReader.VoltarPagina();
        imageView.setImageBitmap(pdfReader.getImagem());
        leitor.setImagem(pdfReader.getImagem());
        leitor.LerImagem();
    }

    public void Pause(View v)
    {
        leitor.pararLeitura();
    }
    @Override
    protected void onStop() {
        super.onStop();
        leitor.pararLeitura();
    }
}

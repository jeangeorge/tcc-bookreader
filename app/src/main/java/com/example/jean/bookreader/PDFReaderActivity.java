package com.example.jean.bookreader;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
class PlayerThread implements Runnable
{
    Leitor leitor;

    ModuloPDF pdfReader;

    PDFReaderActivity activity;

    boolean rodando = true, pausado = false;


    PlayerThread(Leitor leitor, ModuloPDF pdfReader, PDFReaderActivity activity)
    {
        this.activity = activity;
        this.leitor = leitor;
        this.pdfReader = pdfReader;
    }
    public void setPausado(boolean pausado)
    {
        this.pausado = pausado;
    }
    public boolean getPausado()
    {
        return pausado;
    }
    public void setRodando(boolean rod)
    {
        rodando = rod;
    }
    @Override
    public void run() {
        while(rodando)
        {
            if(!pausado)
            {
               if(!leitor.estaLendo())
               {
                   activity.teste();
               }
            }
        }
    }
}
public class PDFReaderActivity extends AppCompatActivity{
    final int REQUEST_PDF = 1;

    PlayerThread player;

    Leitor leitor;

    //Le o pdf e converte cada página pra uma imagem
    ModuloPDF pdfReader;

    //Mostra a imagem da página atual do pdf
    ImageView imageView;

    Thread thread;
    int paginaAtual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfreader);

        //Cria um intent que permite que o usuário escolha um arquivo pdf
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
        player = new PlayerThread(leitor, pdfReader,this);
        thread = new Thread(player);
        //thread.start()
    }

    //Abre um intent que permite que o usuário selecione um arquivo pdf
    public void selectPdf() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("pdf/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_PDF);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Se ele escoher uma arquivo pdf
        if (requestCode == REQUEST_PDF && resultCode == RESULT_OK) {

            //Pega os dados do arquivo
            Uri uri = data.getData();

            //Cria um objeto para o ler o df
            pdfReader = new ModuloPDF(uri.getPath());

            //Seta a imagem do leitor
            leitor.setImagem(pdfReader.getImagem());

            //Seta a imagem do image view
            imageView.setImageBitmap(leitor.getImagem());

            //Ele le o texto que esta na imagem
            leitor.LerImagem();

            paginaAtual = pdfReader.getPaginaAtual();

            thread.start();
        }
    }
    public void Proximo(View v)
    {
        leitor.pararLeitura();

        leitor.Zerar();

        pdfReader.ProximaPagina();

        leitor.setImagem(pdfReader.getImagem());

        imageView.setImageBitmap(leitor.getImagem());

        AlertDialog.Builder a = new AlertDialog.Builder(PDFReaderActivity.this);

        a.setMessage(leitor.LerImagem())
                .setNegativeButton("Ok",null)
                .create()
                .show();
    }
    public void Anterior(View v)
    {
        leitor.pararLeitura();

        leitor.Zerar();

        pdfReader.VoltarPagina();

        leitor.setImagem(pdfReader.getImagem());

        imageView.setImageBitmap(leitor.getImagem());

        AlertDialog.Builder a = new AlertDialog.Builder(PDFReaderActivity.this);

        a.setMessage(leitor.LerImagem())
                .setNegativeButton("Ok",null)
                .create()
                .show();

       // leitor.LerImagem();
    }

    public void Pause(View v)
    {
        player.setPausado(!player.getPausado());
        if(leitor.estaLendo())leitor.pararLeitura();
        else leitor.LerImagem();

    }
    @Override
    protected void onStop() {
        super.onStop();
        leitor.pararLeitura();
    }
    public void teste()
    {
        leitor.pararLeitura();

        leitor.Zerar();

        pdfReader.ProximaPagina();

        leitor.setImagem(pdfReader.getImagem());

        imageView.setImageBitmap(leitor.getImagem());

        AlertDialog.Builder a = new AlertDialog.Builder(PDFReaderActivity.this);

        a.setMessage(leitor.LerImagem())
                .setNegativeButton("Ok",null)
                .create()
                .show();
    }




}

package com.example.jean.bookreader;

/**
 * Created by Gabriel on 15/09/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.speech.tts.TextToSpeech;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Gabriel on 07/09/2017.
 */

//Essa é a classe que lê, identifica e corrige o texto
public class Leitor {
    //Imagem que vai ser reconhecida
    public Bitmap imagem;

    //Objeto que faz o reconhecimento do texto
    private TextRecognizer teste;

    //Objeto que le o texto com uma voz sintetizada
    private TextToSpeech narrador;

    //Lista de blocos de texto
    List<TextBlock> blocos;

    List<String> blocosProntos;

    public void setImagem(Bitmap imagem)
    {
        this.imagem = imagem;
    }

    public Leitor(Context ctx) throws IOException {
        teste = new TextRecognizer.Builder(ctx).build();

        blocos = new ArrayList<TextBlock>();

        blocosProntos = new ArrayList<String>();

        narrador = new TextToSpeech(ctx, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    //Quando inicia o narrador utiliza a linguagem do portugues brasileiro
                    Locale myLocale = new Locale("pt", "PT");

                    //Seta a localidade
                    narrador.setLanguage(myLocale);
                }
            }
        });
//        ParcelFileDescriptor file = new ParcelFileDescriptor();

  //      PdfRenderer renderer = new PdfRenderer(file);
    //    renderer.openPage().render();


    }

    //Método que lê o que estiver na lista com a voz do narrador
    public void LerImagem()
    {
        Reconhecer();
        Ordenar();
        Corrigir();
        narrador.speak(getTexto(),TextToSpeech.QUEUE_FLUSH,null);
    }

    //Lê uma String
    public void Ler(String palavra)
    {
        narrador.speak(palavra,TextToSpeech.QUEUE_FLUSH,null);
    }

    //Método que lê pdf
    public void LerPDF()
    {

    }


    //Apaga todos os blocos de texto que estão armazenados
    public void Zerar()
    {
        blocos.clear();
    }

    //Nesse método todos os blocos de texto devem ser ordenados
    private void Ordenar()
    {



    }

    //Esse método corrige o texto para posterior leitura
    private void Corrigir()
    {

    }


    public void Reconhecer()
    {
        Frame outputFrame = new Frame.Builder().setBitmap(imagem).build();
        SparseArray<TextBlock>items = teste.detect(outputFrame);
        if(items.size() != 0)
        {
            StringBuilder stringBuilder = new StringBuilder();
            for(int i =0;i<items.size();++i)
            {
                TextBlock item = items.valueAt(i);
                blocos.add(item);

                stringBuilder.append(item.getValue());
                stringBuilder.append("\n");
            }
        }
    }


    public String getTexto()
    {
        Ordenar();
        String a = "";
        for(int i = 0; i < blocos.size(); i++)
        {
            a+=blocos.get(i).getValue() + "\n";
        }
        return a;
    }
}
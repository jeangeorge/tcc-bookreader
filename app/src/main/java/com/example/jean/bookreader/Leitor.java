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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by Gabriel on 07/09/2017.
 */


public class Leitor {
    public Bitmap imagem;
    private TextRecognizer teste;
    private TextToSpeech narrador;
    List<TextBlock> blocos;

    public void setImagem(Bitmap imagem)
    {
        this.imagem = imagem;
    }

    public Leitor(Context ctx)
    {
        teste = new TextRecognizer.Builder(ctx).build();
        blocos = new ArrayList<TextBlock>();
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

    }
    public void Ler()
    {
        narrador.speak(reconhecer(),TextToSpeech.QUEUE_FLUSH,null);
    }

    public void Zerar()
    {
        blocos.clear();
    }

    public void Ordenar()
    {
        Collections.sort(blocos, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                TextBlock p1 = (TextBlock) o1;
                TextBlock p2 = (TextBlock) o2;

                return calculaDistancia(p1) < calculaDistancia(p2) ? -1 : (calculaDistancia(p1) > calculaDistancia(p1) ? +1 : 0);
            }
        });
    }

    private double calculaDistancia(TextBlock obj)
    {
       return Math.sqrt( Math.pow( (obj.getBoundingBox().left - 0),2 ) +
                Math.pow( (obj.getBoundingBox().top - 0),2 ) );
    }



    public String reconhecer()
    {
        Frame outputFrame = new Frame.Builder().setBitmap(imagem).build();
        SparseArray<TextBlock>items = teste.detect(outputFrame);
        String a = "";
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
            a = stringBuilder.toString();
        }
        return a;
    }
    public String reconhecer2()
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
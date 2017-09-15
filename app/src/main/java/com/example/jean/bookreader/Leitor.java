package com.example.jean.bookreader;

/**
 * Created by Gabriel on 15/09/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

/**
 * Created by Gabriel on 07/09/2017.
 */

public class Leitor {
    public Bitmap imagem;
    private TextRecognizer teste;

    public void setImagem(Bitmap imagem)
    {
        this.imagem = imagem;
    }

    public Leitor(Context ctx)
    {
        teste = new TextRecognizer.Builder(ctx).build();
    }

    public String reconhecer()
    {
        Frame outputFrame = new Frame.Builder().setBitmap(imagem).build();
        SparseArray<TextBlock> items = teste.detect(outputFrame);
        String a = "";
        if(items.size() != 0)
        {
            StringBuilder stringBuilder = new StringBuilder();
            for(int i =0;i<items.size();++i)
            {
                TextBlock item = items.valueAt(i);
                stringBuilder.append(item.getValue());
                stringBuilder.append("\n");
            }
            a = stringBuilder.toString();
        }
        return a;
    }
}
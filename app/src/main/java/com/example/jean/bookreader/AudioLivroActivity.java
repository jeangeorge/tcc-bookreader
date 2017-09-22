package com.example.jean.bookreader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class AudioLivroActivity extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_livro);
        mp = new MediaPlayer();
        File arq = getApplicationContext().getFileStreamPath("audiolivro.mp3");
        try {
            mp.setDataSource(arq.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageView imageView = (ImageView) findViewById(R.id.imageViewAudio);

        Bitmap bitmap = null;
        File filePath = getApplicationContext().getFileStreamPath("thumb.jpg");


        bitmap = BitmapFactory.decodeFile(filePath.toString());


        imageView.setImageBitmap(bitmap);
    }

    public void Pause(View v)
    {
        mp.start();
    }
    public void Proximo(View v)
    {

    }
    public void Anterior(View v)
    {

    }

}

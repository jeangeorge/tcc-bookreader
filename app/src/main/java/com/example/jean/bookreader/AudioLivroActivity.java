package com.example.jean.bookreader;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class AudioLivroActivity extends AppCompatActivity {
    MediaPlayer mp;
    ImageView botao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_livro);

        botao = (ImageView) findViewById(R.id.play_pause_audio);

        mp = MediaPlayer.create(AudioLivroActivity.this, R.raw.audiolivro);

        ImageView imageView = (ImageView) findViewById(R.id.imageViewAudio);

        imageView.setImageResource(R.drawable.thumb);

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
                botao.setImageResource(R.drawable.ic_action_pause_over_video);
            }
        });
    }

    public void Pause(View v)
    {
        if(mp.isPlaying())
        {
            mp.pause();
            botao.setImageResource(R.drawable.ic_action_play_over_video);
        }
        else
        {
            mp.start();
            botao.setImageResource(R.drawable.ic_action_pause_over_video);
        }
    }
    public void Proximo(View v)
    {

    }
    public void Anterior(View v)
    {

    }

}

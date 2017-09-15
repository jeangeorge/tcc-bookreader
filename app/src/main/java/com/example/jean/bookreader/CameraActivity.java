package com.example.jean.bookreader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CameraActivity extends AppCompatActivity {

    //view que mostra o que a camera está "vendo"
    SurfaceView cameraView;


    //View que mostra o que está escrito na imagem da camera
    TextView textView;

    //Classe da camera
    CameraSource cameraSource;

    //Id da permissão da camera
    final int RequestCameraPermissionID = 1001;

    //Objeto que permite a leitura dos textos
    TextToSpeech narrador;

    //Botão que inicia a leitura
    Button botao;

    //Armazena as linhas identifidadas no texto
    List<String>  linhas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(com.example.jean.bookreader.R.layout.activity_camera);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        linhas = new ArrayList<String>();

        narrador = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
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

        //Botao de ler
        botao = (Button)findViewById(com.example.jean.bookreader.R.id.button);

        //adiciona a função do evento de click do botão
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Lê todas os itens da lista
                for(int i = 0; i < linhas.size(); i++)
                    narrador.speak(linhas.get(i),TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        //Surface view que mostra a imagem da camera
        cameraView = (SurfaceView) findViewById(com.example.jean.bookreader.R.id.surface_view);


        textView = (TextView) findViewById(com.example.jean.bookreader.R.id.text_value);

        //Reconhece o texto
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();



        if (!textRecognizer.isOperational()) {
            Log.w("MainActivity", "As dependencias do detector de texto não estao disponíveis");
        } else {
            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();
            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {

                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(CameraActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    RequestCameraPermissionID);
                            return;
                        }
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    cameraSource.stop();
                }
            });

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {
                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    linhas.clear();
                    if(items.size() != 0)
                    {
                        textView.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                for(int i =0;i<items.size();++i)
                                {
                                    TextBlock item = items.valueAt(i);
                                    stringBuilder.append(item.getValue());
                                    stringBuilder.append("\n");
                                    linhas.add(stringBuilder.toString());
                                }
                                textView.setText(stringBuilder.toString());
                            }
                        });
                    }
                }
            });
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case RequestCameraPermissionID: {
                //Se ele der permissão para acessar a camera vai entrar aqui e iniciar
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {

                        //Inicia a camera e transforma o view para receber as imagens
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    @Override
    protected void onDestroy() {
        narrador.stop();
        super.onDestroy();
    }
}
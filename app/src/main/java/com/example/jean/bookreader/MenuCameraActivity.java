package com.example.jean.bookreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenuCameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_camera);
    }

    public void abrirScanner(View v)
    {
        Intent camera = new Intent(this, MenuScannerActivity.class);
        startActivity(camera);
    }
    public void abrirLeitor(View v)
    {
        Intent cameraLeitor = new Intent(this, CameraActivity.class);
        startActivity(cameraLeitor);
    }


}

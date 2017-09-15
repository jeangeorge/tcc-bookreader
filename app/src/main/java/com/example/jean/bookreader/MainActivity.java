package com.example.jean.bookreader;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId())
        {
            case R.id.sobre_item:
                Intent sobre = new Intent(this, SobreActivity.class);
                startActivity(sobre);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void showSobre(View v) {
        Intent sobre = new Intent(this, SobreActivity.class);
        startActivity(sobre);
    }
    public void login(View v) {
        Intent login = new Intent(this, LoginActivity.class);
        startActivity(login);
    }

    public void teste(View v) {
        Intent login = new Intent(this, MenuActivity.class);
        startActivity(login);
    }

    public void menu(View v) {
        Intent menu = new Intent(this, MenuActivity.class);
        startActivity(menu);
    }
}

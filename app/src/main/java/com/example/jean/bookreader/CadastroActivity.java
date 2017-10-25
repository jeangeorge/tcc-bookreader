package com.example.jean.bookreader;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void cadastro(View v) {
        String nome = ((EditText)findViewById(R.id.nome)).getText().toString();

        String email = ((EditText)findViewById(R.id.email)).getText().toString();
        String senha = ((EditText)findViewById(R.id.password)).getText().toString();

        String senha2 = ((EditText)findViewById(R.id.password2)).getText().toString();
        if(!senha.equals(senha2))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(CadastroActivity.this);
            builder.setMessage("As senhas digitadas são diferentes")
                    .setNegativeButton("Ok", null)
                    .create()
                    .show();
        }
        else {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if (success) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(CadastroActivity.this);
                            builder.setMessage("Usuário cadastrado com sucesso")
                                    .setNegativeButton("Ok", null)
                                    .create()
                                    .show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(CadastroActivity.this);
                            builder.setMessage("Erro ao cadastrar usuário")
                                    .setNegativeButton("Tentar novamente", null)
                                    .create()
                                    .show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            };
            RegisterRequest registerRequest = new RegisterRequest(nome, email, senha, responseListener);
            RequestQueue queue = Volley.newRequestQueue(CadastroActivity.this);
            queue.add(registerRequest);
        }
    }
}

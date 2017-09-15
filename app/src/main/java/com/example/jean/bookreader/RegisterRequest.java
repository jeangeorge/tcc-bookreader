package com.example.jean.bookreader;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gabriel on 13/06/2017.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://bookreader.16mb.com/services/cadastro.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String username, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("CAMPO_NOME", name);
        params.put("CAMPO_EMAIL", username);
        params.put("CAMPO_SENHA", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

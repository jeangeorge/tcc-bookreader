package com.example.jean.bookreader;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://bookreader.16mb.com/services/login.php";
    private Map<String, String> params;

    public LoginRequest(String username, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("CAMPO_USUARIO", username);
        params.put("CAMPO_SENHA", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

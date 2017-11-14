package com.example.jean.bookreader.BD;

/**
 * Created by Gabriel on 12/11/2017.
 */

public class UsuarioSingleton {
    static String nome;
    static String email;
    static String senha;

    public UsuarioSingleton(String nome2, String email2, String senha2) {
        nome = nome2;
        email = email2;
        senha = senha2;
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome2) {
        nome = nome2;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email2) {
        email = email2;
    }

    public static String getSenha() {
        return senha;
    }

    public static void setSenha(String senha2) {
        senha = senha2;
    }
}

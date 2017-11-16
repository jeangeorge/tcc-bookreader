package com.example.jean.bookreader.BD;

/**
 * Created by Gabriel on 12/11/2017.
 */

public class UsuarioSingleton {
    public static Usuario usuario;

    public UsuarioSingleton(Usuario usuario2) {
        usuario = usuario2;

    }
     public static Usuario getInstance()
     {
         return usuario;
     }
}

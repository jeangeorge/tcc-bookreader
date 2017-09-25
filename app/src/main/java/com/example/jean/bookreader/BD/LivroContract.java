package com.example.jean.bookreader.BD;

import android.provider.BaseColumns;

public class LivroContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private LivroContract() {}

    /* Inner class that defines the table contents */
    public static class LivroEntry implements BaseColumns {
        public static final String TABLE_NAME = "livros";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_IDUSUARIO = "idUsuario";
        public static final String COLUMN_NOMELIVRO = "nomeDoLivro";
        public static final String COLUMN_AUTOR = "autor";
        public static final String COLUMN_DESCRICAO = "descricao";
    }
}

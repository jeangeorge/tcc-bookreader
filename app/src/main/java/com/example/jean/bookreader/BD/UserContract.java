package com.example.jean.bookreader.BD;

import android.provider.BaseColumns;

public class UserContract {

    // Construtor privado para previnir instanciar essa classe acidentalmente
    private UserContract() {}

    /* Classe interna que define o conteúdo da tabela */
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "usuarios";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "nome";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "senha";
    }
}

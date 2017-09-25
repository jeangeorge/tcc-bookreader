package com.example.jean.bookreader.BD;

import android.provider.BaseColumns;

public class PaginaContract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private PaginaContract() {}

    /* Inner class that defines the table contents */
    public static class PaginaEntry implements BaseColumns {
        public static final String TABLE_NAME = "paginas";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMM_NUMEROPAGINA = "numero";
        public static final String COLUMN_CONTEUDO = "conteudo";
    }
}

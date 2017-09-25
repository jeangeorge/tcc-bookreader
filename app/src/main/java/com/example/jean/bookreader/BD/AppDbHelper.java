package com.example.jean.bookreader.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AppDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = AppDbHelper.class.getSimpleName();

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BookReader.db";

    public AppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_USUARIOS_TABLE =  "CREATE TABLE " + UserContract.UserEntry.TABLE_NAME + " ("
                + UserContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserContract.UserEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + UserContract.UserEntry.COLUMN_EMAIL + " TEXT NOT NULL, "
                + UserContract.UserEntry.COLUMN_PASSWORD + " ITEXT NOT NULL );";


        String SQL_CREATE_LIVRO_TABLE =  "CREATE TABLE " + LivroContract.LivroEntry.TABLE_NAME + " ("
                + LivroContract.LivroEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + LivroContract.LivroEntry.COLUMN_IDUSUARIO + " INTEGER NOT NULL, "
                + LivroContract.LivroEntry.COLUMN_NOMELIVRO + " TEXT NOT NULL, "
                + LivroContract.LivroEntry.COLUMN_AUTOR + " TEXT, "
                + LivroContract.LivroEntry.COLUMN_DESCRICAO + " TEXT );";


        String SQL_CREATE_PAGINA_TABLE =  "CREATE TABLE " + PaginaContract.PaginaEntry.TABLE_NAME + " ("
                + PaginaContract.PaginaEntry._ID + " INTEGER PRIMARY KEY, "
                + PaginaContract.PaginaEntry.COLUMN_CONTEUDO + " TEXT, "
                + PaginaContract.PaginaEntry.COLUMM_NUMEROPAGINA + " INTEGER );";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USUARIOS_TABLE);
        db.execSQL(SQL_CREATE_LIVRO_TABLE);
        db.execSQL(SQL_CREATE_PAGINA_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


    }
}

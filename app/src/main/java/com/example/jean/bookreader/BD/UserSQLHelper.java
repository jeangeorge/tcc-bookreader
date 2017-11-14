package com.example.jean.bookreader.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Gabriel on 12/11/2017.
 */
class TabelaUsuario
{
    public static final String TABELA_NOME = "Usuarios";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_NOME = "nome";
    public static String getCreate()
    {
        return "CREATE TABLE " + TABELA_NOME + " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_EMAIL + " TEXT, "+ COLUNA_NOME + " TEXT)";
    }

}

class TabelaLivros
{
    public static final String TABELA_NOME = "Livros";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_NOME = "nome";
    public static String getCreate()
    {
        return "CREATE TABLE " + TABELA_NOME + " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_NOME + " TEXT)";
    }
}

class TabelaPagina
{
    public static final String TABELA_NOME = "Paginas";
    public static final String COLUNA_ID = "_id";
    public static final String COLUNA_ID_LIVRO = "idLivro";
    public static final String COLUNA_CONTEUDO = "conteudo";
    public static String getCreate()
    {
        return "CREATE TABLE " + TABELA_NOME + " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_CONTEUDO + " TEXT, "+ COLUNA_ID_LIVRO + " INTEGER)";
    }
}

public class UserSQLHelper extends SQLiteOpenHelper{
    private static final String NOME_BANCO = "dbBookReader";
    private static final int VERSAO_BANCO = 1;

    public UserSQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TabelaUsuario.getCreate());
        db.execSQL(TabelaLivros.getCreate());
        db.execSQL(TabelaPagina.getCreate());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

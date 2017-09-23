package com.example.jean.bookreader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jean on 23/09/2017.
 */

public class BancoDeDados
{

}


class Livro
{

    // Variáveis
    int id;
    String nome;
    String conteudo;


    public Livro()
    {

    }

    // Construtor
    public Livro(int id, String nome, String conteudo)
    {
        this.id = id;
        this.nome = nome;
        this.conteudo = conteudo;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

}


class Controlador extends SQLiteOpenHelper
{

    // Todas as variáveis são estáticas (ava)

    // Versão do banco de dados
    private static final int DATABASE_VERSION = 1;

    // Nome do BD
    private static final String DATABASE_NAME = " BookReader.db ";

    // Tabela livros
    private static final String TABELA_LIVROS = "livros";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NOME = "nome";
    private static final String KEY_CONTEUDO = "conteudo";

    public Controlador(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Criando as tabelas
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABELA_LIVROS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOME + " TEXT,"
                + KEY_CONTEUDO + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Excluindo a tabela antiga, caso exista
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_LIVROS);

        // Criando a tabela novamente
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adicionando um novo livro
    void addLivro(Livro livro) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, livro.getNome()); // Nome do livro
        values.put(KEY_CONTEUDO, livro.getConteudo()); // Conteudo do livro

        // Inserindo
        db.insert(TABELA_LIVROS, null, values);
        db.close(); // Fechando a conexão com o banco de dados
    }

    // Pegando um único livro
    Livro getLivro(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABELA_LIVROS, new String[] { KEY_ID,
                        KEY_NOME, KEY_CONTEUDO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Livro livro = new Livro(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // retorna o livro
        return livro;
    }

    // Pegando todos os livros
    public List<Livro> getAllLivros() {
        List<Livro> livrosList = new ArrayList<Livro>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABELA_LIVROS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // percorrendo toda a tabela e adicionando na lista
        if (cursor.moveToFirst()) {
            do {
                Livro livro = new Livro();
                livro.setId(Integer.parseInt(cursor.getString(0)));
                livro.setNome(cursor.getString(1));
                livro.setConteudo(cursor.getString(2));

                // Adicionando o livro a lista
                livrosList.add(livro);
            } while (cursor.moveToNext());
        }

        // retornando a lista de livros
        return livrosList;
    }

    // Atualizando um único livro
    public int updateLivro(Livro livro) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOME, livro.getNome());
        values.put(KEY_CONTEUDO, livro.getConteudo());

        // updating row
        return db.update(TABELA_LIVROS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(livro.getId()) });
    }

    // Deletando um único livro
    public void deleteLivro(Livro livro) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABELA_LIVROS, KEY_ID + " = ?",
                new String[] { String.valueOf(livro.getId()) });
        db.close();
    }


    // Pegando o número de livros
    public int getLivrosCount() {
        String countQuery = "SELECT  * FROM " + TABELA_LIVROS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // retornando a quantidade
        return cursor.getCount();
    }
}
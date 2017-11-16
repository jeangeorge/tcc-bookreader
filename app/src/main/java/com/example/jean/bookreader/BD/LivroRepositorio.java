package com.example.jean.bookreader.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 13/11/2017.
 */

public class LivroRepositorio {

    private UserSQLHelper helper;

    public LivroRepositorio(Context ctx)
    {
        helper = new UserSQLHelper(ctx);
    }
    public long novoLivro(Livro livro)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TabelaLivros.COLUNA_NOME, livro.nome);

        long id = db.insert(TabelaLivros.TABELA_NOME, null, cv);
        if(id != -1)
        {
            livro.ID = id;
        }
        db.close();
        return id;
    }

    public int excluirLivro(Livro livro)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        int linhasAfetadas = db.delete(TabelaLivros.TABELA_NOME, TabelaLivros.COLUNA_ID + " = ?", new String[]{String.valueOf(livro.ID)});
        linhasAfetadas+= db.delete(TabelaPagina.TABELA_NOME, TabelaPagina.COLUNA_ID_LIVRO + " = ?", new String[]{String.valueOf(livro.ID)});
        db.close();
        return linhasAfetadas;
    }

    public Pagina getPagina(int numero)
    {
        return null;
    }
    public Pagina getPaginaID()
    {
        return null;
    }

    public List<Pagina> getPaginas(int idLivro)
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM " + TabelaPagina.TABELA_NOME;
        String [] argumentos = null;
        String filtro = ""+idLivro;
        if(filtro != null)
        {
            sql += " WHERE " + TabelaPagina.COLUNA_ID_LIVRO + " = ?";
            argumentos = new String[]{filtro};
        }
        Cursor cursor = db.rawQuery(sql, argumentos);
        List<Pagina> paginas = new ArrayList<Pagina>();
        while(cursor.moveToNext())
        {
            long id = cursor.getLong(cursor.getColumnIndex(TabelaUsuario.COLUNA_ID));
            //long idLivro = cursor.getLong(cursor.getColumnIndex(TabelaUsuario.COLUNA_ID));
            String conteudo = cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_NOME));

            Pagina pagina = new Pagina(id, idLivro, conteudo);


            paginas.add(pagina);
        }
        cursor.close();
        db.close();
        return paginas;
    }

    public long novaPagina(Pagina pagina, Livro livro)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(TabelaPagina.COLUNA_ID_LIVRO, livro.ID);
        cv.put(TabelaPagina.COLUNA_CONTEUDO, pagina.conteudo);

        long id = db.insert(TabelaPagina.TABELA_NOME, null, cv);
        if(id != -1)
        {
            pagina.ID = id;
        }
        db.close();
        return id;
    }
    public int getNumeroDePaginas()
    {
        return 0;
    }

    public List<Livro> buscarLivros(String filtro)
    {
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + TabelaLivros.TABELA_NOME;

        String [] argumentos = null;

        if(filtro != null)
        {
            sql += " WHERE " + TabelaLivros.COLUNA_NOME + " = ?";
            argumentos = new String[]{filtro};
        }
        Cursor cursor = db.rawQuery(sql, argumentos);
        List<Livro> livros = new ArrayList<Livro>();
        while(cursor.moveToNext())
        {
            long id = cursor.getLong(cursor.getColumnIndex(TabelaLivros.COLUNA_ID));
            String nome = cursor.getString(cursor.getColumnIndex(TabelaLivros.COLUNA_NOME));
            Livro usuario = new Livro(id, nome);
            livros.add(usuario);
        }
        cursor.close();
        db.close();
        return livros;
    }



}

package com.example.jean.bookreader.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 12/11/2017.
 */

public class UsuarioRepositorio {
    private UserSQLHelper helper;

    public UsuarioRepositorio(Context ctx)
    {
        helper = new UserSQLHelper(ctx);
    }
    private long inserir(Usuario usuario)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(TabelaUsuario.COLUNA_EMAIL, usuario.email);
        cv.put(TabelaUsuario.COLUNA_NOME, usuario.nome);

        long id = db.insert(TabelaUsuario.TABELA_NOME, null, cv);
        if(id != -1)
        {
            usuario.ID = id;
        }
        db.close();
        return id;
    }
    private int atualizar(Usuario usuario)
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(TabelaUsuario.COLUNA_EMAIL, usuario.email);
        cv.put(TabelaUsuario.COLUNA_NOME, usuario.nome);

        int linhasAfetadas = db.update(TabelaUsuario.TABELA_NOME, cv, TabelaUsuario.COLUNA_ID + " = ?", new String[]{String.valueOf(usuario.ID)});


        db.close();
        return linhasAfetadas;
    }

    public void salvar(Usuario usuario)
    {
        if(usuario.ID == 0)
        {
            inserir(usuario);
        }
        else
        {
            atualizar(usuario);
        }
    }

    public int excluir(Usuario usuario)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        int linhasAfetadas = db.delete(TabelaUsuario.TABELA_NOME, TabelaUsuario.COLUNA_ID + " = ?", new String[]{String.valueOf(usuario.ID)});
        db.close();
        return linhasAfetadas;
    }

    public List<Usuario> buscarUsuario(String filtro)
    {
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM " + TabelaUsuario.TABELA_NOME;

        String [] argumentos = null;

        if(filtro != null)
        {
            sql += " WHERE " + TabelaUsuario.COLUNA_NOME + " LIKE ?";
            argumentos = new String[]{filtro};
        }
        Cursor cursor = db.rawQuery(sql, argumentos);
        List<Usuario> usuarios = new ArrayList<Usuario>();
        while(cursor.moveToNext())
        {
            long id = cursor.getLong(cursor.getColumnIndex(TabelaUsuario.COLUNA_ID));
            String nome = cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_NOME));
            String email = cursor.getString(cursor.getColumnIndex(TabelaUsuario.COLUNA_EMAIL));
            Usuario usuario = new Usuario(nome, email, null, id);
            usuarios.add(usuario);
        }
        cursor.close();
        db.close();
        return usuarios;
    }

}

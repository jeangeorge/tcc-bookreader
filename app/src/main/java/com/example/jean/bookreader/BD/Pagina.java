package com.example.jean.bookreader.BD;

/**
 * Created by Gabriel on 13/11/2017.
 */

public class Pagina {
    public long ID;
    public long IdLivro;
    public String conteudo;
    public int numeroDaPagina;

    public Pagina(long ID, long idLivro, String conteudo) {
        this.ID = ID;
        IdLivro = idLivro;
        this.conteudo = conteudo;

    }

}

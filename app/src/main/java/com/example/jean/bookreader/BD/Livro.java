package com.example.jean.bookreader.BD;

/**
 * Created by Gabriel on 13/11/2017.
 */

public class Livro {
    public long ID;
    int paginas = 0;

    public Livro(long ID, int paginas, String nome) {
        this.ID = ID;
        this.paginas = paginas;
        this.nome = nome;
    }

    public Livro(String nome) {
        this.nome = nome;
    }

    public String nome;

    public Livro(long ID, String nome) {
        this.ID = ID;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome + " " + paginas + " páginas.";
    }
}

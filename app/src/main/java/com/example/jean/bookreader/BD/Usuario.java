package com.example.jean.bookreader.BD;

/**
 * Created by Gabriel on 12/11/2017.
 */

public class Usuario {
    public String nome;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public String email;
    public  String senha;
    public long ID;
    public Usuario(String nome, String email, String senha, long ID) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome2) {
        nome = nome2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email2) {
        email = email2;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha2) {
       senha = senha2;
    }
}

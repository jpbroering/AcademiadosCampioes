package com.example.academiadoscampies;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Exercio {
    String nome;
    int rep, serie;

    public Exercio(String nome, int rep, int serie) {
        this.nome = nome;
        this.rep = rep;
        this.serie = serie;
    }
    public Exercio() {}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }
}

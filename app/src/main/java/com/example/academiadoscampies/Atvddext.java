package com.example.academiadoscampies;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Atvddext {
    byte[] foto;
    String nome;
    String ttl;

    public Atvddext(byte[] foto, String nome, String ttl) {
        this.foto = foto;
        this.nome = nome;
        this.ttl = ttl;
    }

    public Atvddext(String nome, String ttl) {
        this.nome = nome;
        this.ttl = ttl;
    }

    public Atvddext() {
    }

    public  byte[] getFoto() {
        return foto;
    }

    public void setFoto( byte[] foto) {
        this.foto = foto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }
    public void salvar2() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Extras").child(nome);
    }
}

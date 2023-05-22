package com.example.academiadoscampies;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Train {
    ArrayList<Exercio> treinos;
    String nome;

    public Train(ArrayList<Exercio> treinos, String nome) {
        this.treinos = treinos;
        this.nome = nome;
    }
    public Train() {
    }

    public ArrayList<Exercio> getTreinos() {
        return treinos;
    }

    public void setTreinos(ArrayList<Exercio> treinos) {
        this.treinos = treinos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void salvar() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Produtos").child(nome).setValue(this);
    }
}

package com.example.academiadoscampies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TreinoA extends AppCompatActivity {
    static ArrayList<Exercio> listae;
    RecyclerView recycler;
    Adaptadore adaptere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treino);
        getSupportActionBar().hide();
        recycler = findViewById(R.id.rva);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        carrega();
    }
    public void carrega(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Produtos").child("Treino A").child("treinos").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //executado quando o aplicativo encontrar alguma coisa no data base
                listae.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    Exercio p = (Exercio) ds.getValue(Exercio.class);
                    listae.add(p);
                }
                adaptere = new Adaptadore(TreinoA.this, listae, new Adaptadore.OnItemClickListener() {
                    @Override
                    public void onItemClick(Exercio p) {
                        //Aqui é o que vai acontecer quando clicar em um item
                        Toast.makeText(TreinoA.this, p.getNome(), Toast.LENGTH_SHORT).show();
                    }
                });
                recycler.setAdapter(adaptere);
                adaptere.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void sair(View v){
        super.onBackPressed();
    }
}
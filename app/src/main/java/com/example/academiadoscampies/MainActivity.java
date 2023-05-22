package com.example.academiadoscampies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText nome,senha;
    ArrayList<Usuario> lista = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        nome = findViewById(R.id.nomel);
        senha = findViewById(R.id.senhal);
        complist();
    }
    public void cadastro(View v){
        if(valida()) {
            String n = nome.getText().toString();
            String s = senha.getText().toString();
            for (Usuario e : lista) {
                if (!e.getName().equals(n)) {
                    Usuario u = new Usuario(n, s);
                    lista.add(u);
                    Intent i = new Intent(this, Login.class);
                    startActivity(i);
                    Login.lista = lista;
                    break;
                }
            }
        }
        else{
            Toast.makeText(this, "Preencha as informações corretamente", Toast.LENGTH_LONG).show();
        }
    }
    public void sair(View v){
        Intent i = new Intent(this, Tela_inicial.class);
        startActivity(i);
        Login.lista = lista;
    }
    public Boolean valida(){
        if(nome.getText().toString().isEmpty()||senha.getText().toString().isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
    public void complist(){
    }
}
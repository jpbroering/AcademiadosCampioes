package com.example.academiadoscampies;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    static ArrayList<Usuario> lista;
    EditText nome, senha;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar();
        nome = findViewById(R.id.nomel);
        senha = findViewById(R.id.senhal);
    }
    public void logar(View v){
        if(valida()) {
            String n = nome.getText().toString();
            String s = senha.getText().toString();
            for (Usuario p : lista) {
                if (n.equals(p.getName()) && s.equals(p.getPass())) ;
                Intent i = new Intent(this, Tela_inicial.class);
                startActivity(i);
                Tela_inicial.p = p;
                break;
            }
        }
        else {
            Toast.makeText(this, "Preencha as informações corretamente", Toast.LENGTH_LONG).show();
        }
    }
    public Boolean valida(){
        if(nome.getText().toString().isEmpty()||senha.getText().toString().isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
}
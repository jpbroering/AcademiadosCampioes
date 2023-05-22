package com.example.academiadoscampies;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptadorv extends RecyclerView.Adapter<Adaptadorv.MyViewHolder> {
    Context context;
    ArrayList<Train> lista;
    OnItemClickListener listener;

    public Adaptadorv(Context context, ArrayList<Train> lista, OnItemClickListener listener) {
        this.context = context;
        this.lista = lista;
        this.listener = listener;
    }

    public Adaptadorv(Context context, ArrayList<Train> lista) {
        this.context = context;
        this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout, parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        System.out.println(lista);
        Train e = lista.get(position);
        holder.nome.setText(e.getNome());
        holder.itemView.setOnClickListener(view ->{
           // listener.onItemClick(e);
            String[] list = e.getNome().split(" ");
                if (list[1].equals("A")){
                    Intent i = new Intent(context, TreinoA.class);
                    context.startActivity(i);
                    TreinoA.listae = e.getTreinos();
                }
                else if (list[1].equals("B")){
                    Intent i = new Intent(context, TreinoB.class);
                    context.startActivity(i);
                    TreinoB.listar = e.getTreinos();
                }
                else{
                    Intent i = new Intent(context, TreinoC.class);
                    context.startActivity(i);
                    TreinoC.listat = e.getTreinos();
                }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Train p);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nome;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.titulo);
        }
    }
}

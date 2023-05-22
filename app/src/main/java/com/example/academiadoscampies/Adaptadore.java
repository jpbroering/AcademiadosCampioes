package com.example.academiadoscampies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptadore extends RecyclerView.Adapter<Adaptadore.MyViewHolder> {
    Context context;
    ArrayList<Exercio> lista;
    Adaptadore.OnItemClickListener listener;

    public Adaptadore(Context context, ArrayList<Exercio> lista, OnItemClickListener listener) {
        this.context = context;
        this.lista = lista;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Adaptadore.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout, parent,false);
        return new Adaptadore.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptadore.MyViewHolder holder, int position) {
        Exercio e = lista.get(position);
        holder.exer.setText(e.getNome());
        holder.rep.setText(e.getRep()+" Reps");
        holder.serie.setText(e.getSerie()+" Series");
        holder.itemView.setOnClickListener(view ->{
            listener.onItemClick(e);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Exercio E);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView exer, rep, serie;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            exer = itemView.findViewById(R.id.titulo);
            rep = itemView.findViewById(R.id.rep);
            serie = itemView.findViewById(R.id.serie);
        }
    }
}

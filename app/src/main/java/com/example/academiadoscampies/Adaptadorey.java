package com.example.academiadoscampies;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adaptadorey extends RecyclerView.Adapter<Adaptadorey.MyViewHolder> {
    Context context;
    ArrayList<Atvddext> lista;
    Adaptadorey.OnItemClickListener listener;
    private List<Bitmap> bitmapList;

    public Adaptadorey(Context context, ArrayList<Atvddext> lista, List<Bitmap> bitmaps, OnItemClickListener listener) {
        this.context = context;
        this.lista = lista;
        this.listener = listener;
        this.bitmapList = bitmaps;
    }

    @NonNull
    @Override
    public Adaptadorey.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layoutimg, parent,false);
        return new Adaptadorey.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Atvddext e = lista.get(position);
        byte[] imagemBytes = e.getFoto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imagemBytes, 0, imagemBytes.length);
        holder.foto.setImageBitmap(bitmap);
        holder.titulo.setText(e.getTtl());
        holder.itemView.setOnClickListener(view ->{
            listener.onItemClick(e);
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();

    }

    public interface OnItemClickListener {
        void onItemClick(Atvddext E);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        ImageView foto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.titulo);
            foto = itemView.findViewById(R.id.foto);
        }
    }
}

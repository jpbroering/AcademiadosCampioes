package com.example.academiadoscampies;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Treinos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Treinos extends Fragment {
    ArrayList<Train> lista = new ArrayList<>();
    RecyclerView recycler;
    Adaptadorv adapterv;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Treinos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Treino.
     */
    // TODO: Rename and change types and number of parameters
    public static Treinos newInstance(String param1, String param2) {
        Treinos fragment = new Treinos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_treinos, container, false);
        super.onCreate(savedInstanceState);
        recycler = v.findViewById(R.id.rv);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterv = new Adaptadorv(getContext(), lista);
//        adapterv = new Adaptadorv(getContext(), lista, new Adaptadorv.OnItemClickListener() {
//            @Override
//            public void onItemClick(Train p) {
//
//                String[] list = p.getNome().split(" ");
//                if (list[1].equals("A")){
//                    Intent i = new Intent(getContext(), TreinoA.class);
//                    startActivity(i);
//                    Toast.makeText(getContext(), "a", Toast.LENGTH_SHORT).show();
//                }
//                else if (list[1].equals("B")){
//                    Intent i = new Intent(getContext(), TreinoB.class);
//                    startActivity(i);
//                    Toast.makeText(getContext(), "aa", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Intent i = new Intent(getContext(), TreinoC.class);
//                    startActivity(i);
//                    Toast.makeText(getContext(), "aaa", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        recycler.setAdapter(adapterv);
        adapterv.notifyDataSetChanged();
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        carrega();
    }
    public void carrega(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Produtos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //executado quando o aplicativo encontrar alguma coisa no data base
                lista.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    Train p = (Train) ds.getValue(Train.class);
                    lista.add(p);
                }
                adapterv = new Adaptadorv(getContext(), lista, new Adaptadorv.OnItemClickListener() {
                    @Override
                    public void onItemClick(Train p) {
                        //Aqui é o que vai acontecer quando clicar em um item

                    }
                });
                recycler.setAdapter(adapterv);
                adapterv.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
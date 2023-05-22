package com.example.academiadoscampies;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Atividades_extras#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Atividades_extras extends Fragment {
    ArrayList<Atvddext> lst = new ArrayList<>();
    RecyclerView recycler;
    Adaptadorey adapter;
    private List<Bitmap> bitmapList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Atividades_extras() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Atividades_extras.
     */
    // TODO: Rename and change types and number of parameters
    public static Atividades_extras newInstance(String param1, String param2) {
        Atividades_extras fragment = new Atividades_extras();
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
        View v = inflater.inflate(R.layout.fragment_atividades_extras, container, false);
        recycler = v.findViewById(R.id.rvu);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        bitmapList = new ArrayList<>();
        return v;
    }
    @Override
    public void onResume() {
        super.onResume();
        carrega();
    }
    public void carrega(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Extras").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //executado quando o aplicativo encontrar alguma coisa no data base
                lst.clear();
                for(DataSnapshot ds:snapshot.getChildren()){
                    Atvddext p = (Atvddext) ds.getValue(Atvddext.class);
                    lst.add(p);
                    byte[] imagemBytes = p.getFoto();
                    if (imagemBytes != null && imagemBytes.length > 0) {
                        Bitmap bitmap = BitmapFactory.decodeByteArray(imagemBytes, 0, imagemBytes.length);
                        bitmapList.add(bitmap);
                    } else {
                        bitmapList.add(null); // Adicione um valor nulo se a foto estiver vazia
                    }
                }

                adapter = new Adaptadorey(getContext(), lst, bitmapList, new Adaptadorey.OnItemClickListener() {
                    @Override
                    public void onItemClick(Atvddext p) {
                        //Aqui é o que vai acontecer quando clicar em um item
                        Toast.makeText(getContext(), p.getNome(), Toast.LENGTH_SHORT).show();
                    }
                });
                recycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
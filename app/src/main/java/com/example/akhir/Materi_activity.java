package com.example.akhir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Materi_activity extends AppCompatActivity implements View.OnClickListener {
    String key, titel;
    TextView judul,piro;
    RecyclerView rexx;
    FirebaseFirestore DB;
    List<Materi> ure = new ArrayList<>();
    adapter_materi adapterM;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materi_acc);
        key = getIntent().getStringExtra("docid");
        titel = getIntent().getStringExtra("titelG");

        piro = findViewById(R.id.piro);
        judul = findViewById(R.id.title_materi);
        rexx = findViewById(R.id.materi_rec);
        back =findViewById(R.id.image_arrow_left_sub_courses);
        back.setOnClickListener(this);
        judul.setText(titel);
        DB = FirebaseFirestore.getInstance();
        load();
        rexx.setLayoutManager(new LinearLayoutManager(this));
        adapterM = new adapter_materi(ure);
        rexx.setAdapter(adapterM);



    }
    public void load(){

        DB.collection(Courses.class.getSimpleName()).document(key).collection("Materi")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for(DocumentChange documentChange : value.getDocumentChanges()){
                            if(documentChange.getType() == DocumentChange.Type.ADDED){
                                DocumentSnapshot doc = documentChange.getDocument();
                                String lo = (String) doc.get("Nama");
                                String url = (String) doc.get("img");
                                Materi wii = new Materi(lo,url);
                                wii.setKey(documentChange.getDocument().getId());
                                wii.setPop(documentChange.getDocument().getId());
                                wii.setBapak(key);
                                ure.add(wii);
                            }
                            adapterM.notifyDataSetChanged();
                        }
                        piro.setText(String.valueOf(ure.size()));
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(back.getId()==view.getId()){
            finish();
        }
    }
}
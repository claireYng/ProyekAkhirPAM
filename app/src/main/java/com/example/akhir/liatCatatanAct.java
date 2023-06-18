package com.example.akhir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class liatCatatanAct extends AppCompatActivity implements View.OnClickListener {
    FirebaseFirestore DB;
    List<Catatan> catatat = new ArrayList<>();
    adapter_liat liatt;
    RecyclerView rexxt;
    ImageView bek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liat_catatan);
        DB = FirebaseFirestore.getInstance();
        rexxt =findViewById(R.id.reccc);
        bek = findViewById(R.id.kembali);
        bek.setOnClickListener(this);
        load();
        rexxt.setLayoutManager(new LinearLayoutManager(this));
        liatt = new adapter_liat(catatat);
        rexxt.setAdapter(liatt);

    }
    public void load(){
        DB.collection("Library")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for(DocumentChange documentChange : value.getDocumentChanges()){
                            if(documentChange.getType() == DocumentChange.Type.ADDED){
                                Catatan wii = documentChange.getDocument().toObject(Catatan.class);
                                wii.setKey(documentChange.getDocument().getId());

                                catatat.add(wii);
//                                DocumentSnapshot doc = documentChange.getDocument();
//                                String lo = (String) doc.get("Judul");
//                                Courses X = new Courses(lo);
//                                X.setKey(documentChange.getDocument().getId());
//                                courses.add(X);
                            }
                            liatt.notifyDataSetChanged();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
    if(bek.getId()==view.getId()){
        finish();
    }
    }
}

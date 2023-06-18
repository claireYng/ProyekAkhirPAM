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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Catatan_activity extends AppCompatActivity implements View.OnClickListener {


    String key,pop,bapak;
    FirebaseFirestore DB;
    List<Catatan> Kont;
    RecyclerView ress;
    adapter_catatanD adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sddssdsd);
        bapak = getIntent().getStringExtra("bapak");
        pop = getIntent().getStringExtra("pop");
        key = getIntent().getStringExtra("leh");
        Kont  = new ArrayList<>();
        DB = FirebaseFirestore.getInstance();

//        judul = findViewById(R.id.catatan_judul);
//        isi = findViewById(R.id.catatan_isi);
//        img = findViewById(R.id.catatan_img);
        ress = findViewById(R.id.terss);
        ress.setLayoutManager(new LinearLayoutManager(this));
        adapt = new adapter_catatanD(Kont,this);
        ress.setAdapter(adapt);



        load();

    }


    public void load(){

        DB.collection(Courses.class.getSimpleName()).document(bapak).collection("Materi").document(pop)
                .collection("SubMateri").document(key).collection("Catatan")
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
                                DocumentSnapshot doc = documentChange.getDocument();
                                String lo = (String) doc.get("linkpdf");
                                wii.setLinkpdf(lo);
                                Kont.add(wii);


                            }
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {

    }
}


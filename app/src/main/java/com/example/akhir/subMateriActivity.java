//package com.example.akhir;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.google.firebase.firestore.DocumentChange;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.FirebaseFirestoreException;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class subMateriActivity extends AppCompatActivity implements View.OnClickListener {
//    String key, from, jud;
//    TextView atas;
//    RecyclerView rett;
//    adapter_sub adaptGG;
//    FirebaseFirestore DB;
//    List<Materi> materis = new ArrayList<>();
//    ImageView bek;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sub_materi);
//        key = getIntent().getStringExtra("bapak");
//        from = getIntent().getStringExtra("fromMateri");
//        jud = getIntent().getStringExtra("namaMateri");
//        DB = FirebaseFirestore.getInstance();
//
//        bek = findViewById(R.id.bek);
//        atas = findViewById(R.id.sub_judul);
//        rett = findViewById(R.id.sub_rec);
//        atas.setText(jud);
//        bek.setOnClickListener(this);
//
//        load();
//        rett.setLayoutManager(new LinearLayoutManager(this));
//        adaptGG = new adapter_sub(materis);
//        rett.setAdapter(adaptGG);
//
//
//
//    }
//
//    public void load(){
//
//        DB.collection(Courses.class.getSimpleName()).document(key).collection("Materi").document(from)
//                .collection("SubMateri")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                        if(error != null){
//                            Log.e("Firestore error",error.getMessage());
//                            return;
//                        }
//
//                        for(DocumentChange documentChange : value.getDocumentChanges()){
//                            if(documentChange.getType() == DocumentChange.Type.ADDED){
//                                DocumentSnapshot doc = documentChange.getDocument();
//                                String lo = (String) doc.get("Nama");
//                                String url = (String) doc.get("img");
//                                Materi wii = new Materi(lo,url);
//                                wii.setKey(documentChange.getDocument().getId());
//                                wii.setPop(from);
//                                wii.setBapak(key);
//                                materis.add(wii);
//                            }
//                            adaptGG.notifyDataSetChanged();
//                        }
//                    }
//                });
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        if(bek.getId()==view.getId()){
//            finish();
//        }
//    }
//}

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

public class subMateriActivity extends AppCompatActivity implements View.OnClickListener {
    String key, from, jud;
    TextView atas;
    RecyclerView rett;
    adapter_sub adaptGG;
    FirebaseFirestore DB;
    List<Materi> materis = new ArrayList<>();
    ImageView bek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_materi);
        key = getIntent().getStringExtra("bapak");
        from = getIntent().getStringExtra("fromMateri");
        jud = getIntent().getStringExtra("namaMateri");
        DB = FirebaseFirestore.getInstance();

        bek = findViewById(R.id.bek);
        atas = findViewById(R.id.sub_judul);
        rett = findViewById(R.id.sub_rec);
        atas.setText(jud);
        bek.setOnClickListener(this);

        load();
        rett.setLayoutManager(new LinearLayoutManager(this));
        adaptGG = new adapter_sub(materis);
        rett.setAdapter(adaptGG);



    }

    public void load(){

        DB.collection(Courses.class.getSimpleName()).document(key).collection("Materi").document(from)
                .collection("SubMateri")
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
                                String aut = (String) doc.get("author");
                                Materi wii = new Materi(lo,url);
                                wii.setKey(documentChange.getDocument().getId());
                                wii.setAuthor(aut);
                                wii.setPop(from);
                                wii.setBapak(key);
                                materis.add(wii);
                            }
                            adaptGG.notifyDataSetChanged();
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
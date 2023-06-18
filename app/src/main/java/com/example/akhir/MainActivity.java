package com.example.akhir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        Button unggah;
        ImageButton profil;
        ImageView liat, plus;
        RecyclerView res;
        FirebaseAuth mFier;

        FirebaseFirestore DB;
        adapter_main adapt;
        TextView nama;
    List<Courses> courses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unggah = findViewById(R.id.Unggah);
        res = findViewById(R.id.mainRec);
        profil = findViewById(R.id.btProfil);
        nama = findViewById(R.id.tv_title_name);
        DB = FirebaseFirestore.getInstance();
        liat = findViewById(R.id.liat);
        plus = findViewById(R.id.plus);
        plus.setOnClickListener(this);
        mFier = FirebaseAuth.getInstance();
        load();
        res.setLayoutManager(new LinearLayoutManager(this));
        adapt = new adapter_main(courses);
        res.setAdapter(adapt);
        unggah.setOnClickListener(this);
        liat.setOnClickListener(this);
        profil.setOnClickListener(this);
//        FirebaseUser current = mFier.getCurrentUser();
//        nama.setText(current.getDisplayName());

    }
    public void load(){
        DB.collection(Courses.class.getSimpleName())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for(DocumentChange documentChange : value.getDocumentChanges()){
                            if(documentChange.getType() == DocumentChange.Type.ADDED){
                                Courses wii = documentChange.getDocument().toObject(Courses.class);
                                wii.setKey(documentChange.getDocument().getId());
                                courses.add(wii);
//                                DocumentSnapshot doc = documentChange.getDocument();
//                                String lo = (String) doc.get("Judul");
//                                Courses X = new Courses(lo);
//                                X.setKey(documentChange.getDocument().getId());
//                                courses.add(X);
                            }
                            adapt.notifyDataSetChanged();
                        }
                    }
                });

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==unggah.getId()){
            Intent p = new Intent(MainActivity.this,addCatatanActivity.class);
            startActivity(p);
        } else if (view.getId()==liat.getId()) {
            Intent k  = new Intent(MainActivity.this,liatCatatanAct.class);
            startActivity(k);
        }else if (profil.getId()==  view.getId()) {
            Intent prof = new Intent(MainActivity.this,profileAct.class);
            startActivity(prof);
        }
            else if (plus.getId() == view.getId()) {
            Intent p = new Intent(MainActivity.this,addCatatanActivity.class);
            startActivity(p);
        }
    }
}
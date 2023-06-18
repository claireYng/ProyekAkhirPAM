package com.example.akhir;

import androidx.annotation.NonNull;
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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.remote.WatchChange;

import java.util.ArrayList;
import java.util.List;

public class detilCAtAct extends AppCompatActivity implements View.OnClickListener {
    String judul,texs,imgUrl,key;
    TextView title,isi;
    ImageView img;
    ImageButton back;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_catatan);
        judul = getIntent().getStringExtra("judul");
        texs = getIntent().getStringExtra("texs");
        imgUrl = getIntent().getStringExtra("imgUrl");
        key = getIntent().getStringExtra("key");

        title = findViewById(R.id.textkonsep);
        img = findViewById(R.id.kimia);
        isi = findViewById(R.id.isi);

        //edit
        edit = findViewById(R.id.btnedit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Edit = new Intent(getApplicationContext(), EditAct.class);
                Edit.putExtra("key",key);
                startActivity(Edit);
            }
        });


        back = findViewById(R.id.kembali);
        back.setOnClickListener(this);

        title.setText(judul);
        isi.setText(texs);
        Glide.with(detilCAtAct.this)
                .load(imgUrl)
                .override(500,500)
                .into(img);

      
    }

    @Override
    public void onClick(View view) {
     if(back.getId()==view.getId()){
         finish();
     }
    }
}
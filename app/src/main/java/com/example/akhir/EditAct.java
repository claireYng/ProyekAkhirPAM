package com.example.akhir;

import static android.text.TextUtils.isEmpty;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.units.qual.C;

import java.util.HashMap;
import java.util.Map;

public class EditAct extends AppCompatActivity{
    private EditText edtJudul, edtIsi;
    private Button btnSv;
    private String cekJudul, cekIsi, key;
    private DatabaseReference DB;
    private FirebaseFirestore FS;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edtJudul = findViewById(R.id.etjdl);
        edtIsi = findViewById(R.id.etctt);
        btnSv = findViewById(R.id.btSave);
        key = getIntent().getStringExtra("key");

        DB = FirebaseDatabase.getInstance().getReference();
        FS = FirebaseFirestore.getInstance();
        getData();
        btnSv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cekJudul = edtJudul.getText().toString();
                cekIsi = edtIsi.getText().toString();

                if(isEmpty(cekJudul) || isEmpty(cekIsi)){
                    Toast.makeText(EditAct.this, "Data harus diisi", Toast.LENGTH_SHORT).show();
                }else {
                    Catatan setCatatan = new Catatan();
//                    setCatatan.setJudul(edtJudul.getText().toString());
//                    setCatatan.setTexs(edtIsi.getText().toString());
                    updateCtt();
                    Intent pe = new Intent(EditAct.this, liatCatatanAct.class);
                    startActivity(pe);
                }
            }
        });
    }

    private  boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    private void  getData(){
        Bundle extras = getIntent().getExtras(); //tambahan
        if (extras != null){ //if - tambahan
            final String getJudul = getIntent().getExtras().getString("dataJudul");
            final String getTexs = getIntent().getExtras().getString("dataIsi");
            edtJudul.setText(getJudul);
            edtIsi.setText(getTexs);
        }
    }

    private void updateCtt(){
        Bundle extras = getIntent().getExtras(); //tambahan
        if (extras != null){ //if - tambahan
            Map cnt = new HashMap<>();
            cnt.put("judul",edtJudul.getText().toString());
            cnt.put("texs",edtIsi.getText().toString());
            FS.collection("Library").document(key).update(cnt);
//            String getKey = getIntent().getExtras().getString("getPrimaryKey");
//            DB.child("Catatan")
//                    .child("Notes")
//                    .child(getKey)
//                    .setValue(ctt)
//                    .addOnSuccessListener(new OnSuccessListener<Void>(){
//                        @Override
//                        public void onSuccess(Void aVoid){
//                            edtJudul.setText("");
//                            edtIsi.setText("");
//                            Toast.makeText(EditAct.this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//                    });
        }

    }
}

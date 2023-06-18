package com.example.akhir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class profileAct extends AppCompatActivity implements View.OnClickListener {
    Button keluar;
    FirebaseAuth mFier;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        back = findViewById(R.id.button_back);
        keluar = findViewById(R.id.btOut);
        mFier = FirebaseAuth.getInstance();
        back.setOnClickListener(this);
        keluar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        if(back.getId()==view.getId()){
            finish();
        } else if (keluar.getId()== view.getId()) {
            mFier.signOut();
            Intent out = new Intent(profileAct.this, LoginActivity.class);
            out.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(out);
        }
    }
}



package com.example.akhir;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;

import com.bumptech.glide.Glide;
import com.example.akhir.Dataset.Card;
import com.example.akhir.Dataset.CardType;
import com.example.akhir.databinding.AddCatatanBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class add_catatan extends AppCompatActivity {
    private AddCatatanBinding binding;
    private Intent intent;
    private ImageButton btnupload;
    private EditText name, careIntruction;
    private AppCompatButton upload;
    private FirebaseFirestore db;
    private Uri imageUri;
    private StorageReference storageReference;
    private AppState appState;
    private ProgressDialog progressDialog;
    private ImageButton backButton;

    private int REQUEST_IMAGE_CAPTURE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        binding = AddCatatanBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        name = binding.etjdl;
        careIntruction = binding.etctt;
        upload = binding.upload;
        btnupload = binding.uploadgambar;
        backButton = binding.kembali;
        db = FirebaseFirestore.getInstance();
        appState = AppState.START;
        progressDialog = new ProgressDialog(add_catatan.this);

        btnupload.setOnClickListener(v->{
          Intent intent = new Intent();
          intent.setType("image/*");
          intent.setAction(Intent.ACTION_GET_CONTENT);
          startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

        });
        upload.setOnClickListener(v -> {
            addData();
        });
        backButton.setOnClickListener( v -> {
            finish();
        });
    }

    public void addData() {
        progressDialog.setMessage("Uploading Image");
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        int lengthInstruction = careIntruction.getText().toString().trim().length();
        int lengthName = name.getText().toString().trim().length();
        if(lengthInstruction >= 0 && lengthName >= 0 && appState.equals(AppState.UPLOAD_IMAGE)) {
            uploadImage();
        }
    }

    private void uploadImage() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = formatter.format(now);
        storageReference = FirebaseStorage.getInstance().getReference(fileName);

        storageReference.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.setMessage("downloading URL");
                        progressDialog.setProgress(30);
                        getDownloadUri();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("storage failed", e.toString());
                        Toast.makeText(add_catatan.this,"Failed to Upload",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void getDownloadUri(){
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                imageUri = uri;
                progressDialog.setMessage("Adding to Firebase");
                progressDialog.setProgress(60);
                addToFirebase();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });
    }

    public void addToFirebase() {
        Catatan card = new Catatan( Objects.requireNonNull(careIntruction.getText()).toString(), Objects.requireNonNull(name.getText()).toString());
        card.setImgUrl(String.valueOf(imageUri));
        db.collection("Library")
                .add(card)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        progressDialog.setMessage("Added to Firebase");
                        progressDialog.setProgress(100);
                        progressDialog.dismiss();
                        Toast.makeText(add_catatan.this,"Data Successfully Uploaded",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("error adddata", "Error adding document", e);
                    }
                });
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Glide.with(this).load(imageUri).into(btnupload);
        }
    }
}


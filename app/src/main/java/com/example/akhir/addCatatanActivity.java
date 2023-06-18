package com.example.akhir;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class addCatatanActivity extends AppCompatActivity implements View.OnClickListener {
    EditText judul, texs;
    ImageButton up;
    Button add;
    ActivityResultLauncher<Intent> filePickerLauncher;
    Uri fileUri;
    String Linked;
    StorageReference storageReference;
    FirebaseStorage storage;
    FirebaseFirestore DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_catatan);
        judul =findViewById(R.id.etjdl);
        texs = findViewById(R.id.etctt);
        up = findViewById(R.id.uploadgambar);
        add = findViewById(R.id.upload);
        add.setOnClickListener(this);
        up.setOnClickListener(this);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        DB = FirebaseFirestore.getInstance();


        filePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Intent data = result.getData();
                            fileUri = data.getData();
                            if (fileUri != null) {
                                uploadFile(fileUri);
                            } else {
                                Log.e("Detail", "Failed to retrieve file URI");
                            }
                        }
                    }
                });
    }

    private void uploadFile(Uri fileUri) {
        File file = new File(fileUri.getPath());
        StorageReference fileRef = storageReference.child("img/"+ file.getName());
        // Upload file to Firebase Storage
        UploadTask uploadTask = fileRef.putFile(fileUri);
        // Register observers to listen for the upload progress or errors
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // File uploaded successfully
                Log.d("MainActivity", "File uploaded successfully");
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Linked = uri.toString();
                        // Use the imageUrl as needed
                        addLink(Linked);
                    }

                });
//                leh.put("urI",Linked);
//                DB.collection(Wisata.class.getSimpleName()).document(docId)
//                        .collection("Gambare").add(leh);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Handle unsuccessful uploads
                Log.e("MainActivity", "Failed to upload file: " + e.getMessage());
            }
        });
    }
    Map<Object, String> leh = new HashMap<>();
    public void addLink(String link){
        leh.put("imgUrl",link);
    }
    public void addFIre(){
        leh.put("judul",judul.getText().toString());
        leh.put("texs",texs.getText().toString());
        DB.collection("Library")
                .add(leh);
    }

    @Override
    public void onClick(View view) {
        if (up.getId()==view.getId()){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");
            filePickerLauncher.launch(intent);
        } else if (add.getId()==view.getId()) {
            if(!cekGeming()){
                return;
            }else
            addFIre();
            finish();
        }

    }
    public boolean cekGeming(){
        boolean cek = true;
        if(judul.getText().toString().isEmpty()){
            cek = false;
            Toast.makeText(this, "Yakin gak mau ngisi judul?", Toast.LENGTH_SHORT).show();
        }if(texs.getText().toString().isEmpty()){
            cek = false;
            Toast.makeText(this, "Yakin gak mau ngisi?", Toast.LENGTH_SHORT).show();

        }
        return cek;
    }
}
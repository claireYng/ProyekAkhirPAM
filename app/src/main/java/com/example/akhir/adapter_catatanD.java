package com.example.akhir;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class adapter_catatanD extends RecyclerView.Adapter<adapter_catatanD.viewHolder>{
    List<Catatan> CatatanList;
    Context c;

    public adapter_catatanD(List<Catatan> kont,Context context) {
        this.CatatanList = kont;
        this.c=context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View daftar = inflater.inflate(R.layout.catatanwd,parent,false);
        viewHolder isi = new viewHolder(daftar);
        return isi;
    }



    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.isi.setText(CatatanList.get(position).getTexs());
        holder.judul.setText(CatatanList.get(position).getJudul());
        Glide.with(holder.itemView.getContext())
                .load(CatatanList.get(position).getImgUrl())
                .override(500,500)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        if(CatatanList == null){
            return 0;
        }
        return CatatanList.size();
    }
    FirebaseStorage storage;
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView judul,isi;
        ImageView img;
        Button download ;
        ImageButton kembali;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.catatan_judul);
            img = itemView.findViewById(R.id.catatan_img);
            isi = itemView.findViewById(R.id.catatan_isi);
            kembali = itemView.findViewById(R.id.kembali);
            download = itemView.findViewById(R.id.download);
            download.setOnClickListener(this);
            kembali.setOnClickListener(this);
            storage = FirebaseStorage.getInstance();
        }

        @Override
        public void onClick(View view) {
            if(download.getId()==view.getId()){
                int a = getAdapterPosition();
                String link = CatatanList.get(a).getLinkpdf();
                try {
                    download(itemView.getContext(),link);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (kembali.getId()==view.getId()) {
                ((Activity)c).finish();
            }
        }
    }


    public void download( Context context, String url) throws IOException {
        StorageReference httpsReference = storage.getReferenceFromUrl(url);
        DownloadManager downloadManager = (DownloadManager) context
                .getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        String localFilePath = httpsReference.getName();
        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, localFilePath);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadManager.enqueue(request);




        final File localFile = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS),"Download");
        if (!localFile.exists()) {
            localFile.mkdirs();
        }
        String name = httpsReference.getName();
        File destinationFile = new File(localFile, name);

        httpsReference.getFile(destinationFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                Toast.makeText(context, "FIle downloaded", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
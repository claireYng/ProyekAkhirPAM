package com.example.akhir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class adapter_catatan extends RecyclerView.Adapter<adapter_catatan.viewHolder>{
    List<Catatan> CatatanList;


    public adapter_catatan(List<Catatan> kont) {
        this.CatatanList = kont;
    }

    @NonNull
    @Override
    public adapter_catatan.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View daftar = inflater.inflate(R.layout.activity_catatan,parent,false);
        viewHolder isi = new viewHolder(daftar);
        return isi;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_catatan.viewHolder holder, int position) {
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

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView judul,isi;
        ImageView img;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.catatan_judul);
            img = itemView.findViewById(R.id.catatan_img);
            isi = itemView.findViewById(R.id.catatan_isi);
        }
    }
}

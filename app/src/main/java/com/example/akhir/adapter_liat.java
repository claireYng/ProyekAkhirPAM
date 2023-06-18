package com.example.akhir;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class adapter_liat extends RecyclerView.Adapter<adapter_liat.viewHolder>{
    List<Catatan> CatatanList;

    public adapter_liat(List<Catatan> catatat) {
        this.CatatanList = catatat;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View daftar = inflater.inflate(R.layout.card_materi,parent,false);
        viewHolder isi = new viewHolder(daftar);
        return isi;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
    holder.plen.setText(CatatanList.get(position).getJudul());
        Glide.with(holder.itemView.getContext())
                .load(CatatanList.get(position).getImgUrl())
                .override(50,50)
                .into(holder.gamb);
    }

    @Override
    public int getItemCount() {
        if(CatatanList == null){
            return 0;
        }
        return CatatanList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView plen;
        CardView CM;
        ImageView gamb;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            plen = itemView.findViewById(R.id.MAtery);
            CM = itemView.findViewById(R.id.materi_card);
            gamb = itemView.findViewById(R.id.materi_img);
            CM.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent peh = new Intent(view.getContext(),detilCAtAct.class);
            int a = getAdapterPosition();
            peh.putExtra("judul", CatatanList.get(a).getJudul());
            peh.putExtra("texs",CatatanList.get(a).getTexs());
            peh.putExtra("imgUrl",CatatanList.get(a).getImgUrl());
            peh.putExtra("key",CatatanList.get(a).getKey());

            view.getContext().startActivity(peh);
        }
    }
}

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

public class adapter_materi extends RecyclerView.Adapter<adapter_materi.viewHolder>{
    List<Materi> materiList;

    public adapter_materi(List<Materi> ure) {
        this.materiList = ure;
    }

    @NonNull
    @Override
    public adapter_materi.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View daftar = inflater.inflate(R.layout.card_materi,parent,false);
        adapter_materi.viewHolder isi = new adapter_materi.viewHolder(daftar);
        return isi;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_materi.viewHolder holder, int position) {
        holder.plen.setText(materiList.get(position).getNama());
        Glide.with(holder.itemView.getContext())
                .load(materiList.get(position).img)
                .override(50,50)
                .into(holder.gamb);
    }

    @Override
    public int getItemCount() {
        if(materiList == null){
            return 0;
        }
        return materiList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
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
        if (CM.getId() == view.getId()){
            int a = getAdapterPosition();
            Intent p = new Intent(view.getContext(),subMateriActivity.class);
            p.putExtra("fromMateri", materiList.get(a).getKey());
            p.putExtra("namaMateri",materiList.get(a).getNama());
            p.putExtra("bapak",materiList.get(a).getBapak());
            view.getContext().startActivity(p);

        }
        }
    }
}


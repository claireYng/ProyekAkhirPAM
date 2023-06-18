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

public class adapter_sub extends RecyclerView.Adapter<adapter_sub.viewHolder>{

    List<Materi> list;
    public adapter_sub(List<Materi> ure) {
        this.list = ure;
    }
    @NonNull
    @Override
    public adapter_sub.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View daftar = inflater.inflate(R.layout.card_materi,parent,false);
        adapter_sub.viewHolder isi = new adapter_sub.viewHolder(daftar);
        return isi;
    }

    @Override
    public void onBindViewHolder(@NonNull adapter_sub.viewHolder holder, int position) {
        holder.plen.setText(list.get(position).getNama());
        Glide.with(holder.itemView.getContext())
                .load(list.get(position).img)
                .override(50,50)
                .into(holder.gamb);
    }

    @Override
    public int getItemCount() {
        if(list == null){
            return 0;
        }
        return list.size();
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
            int a = getAdapterPosition();
            Intent p = new Intent(view.getContext(),Catatan_activity.class);
            p.putExtra("leh", list.get(a).getKey());
            p.putExtra("pop",list.get(a).getPop());
            p.putExtra("bapak",list.get(a).getBapak());
            view.getContext().startActivity(p);
        }
    }
}

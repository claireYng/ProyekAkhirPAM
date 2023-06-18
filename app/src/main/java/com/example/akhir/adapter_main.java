package com.example.akhir;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class adapter_main extends RecyclerView.Adapter<adapter_main.viewHolder> {
    List<Courses> CourseList;

    public adapter_main(List<Courses> courses) {
        this.CourseList = courses;
    }




    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View daftar = inflater.inflate(R.layout.card_main,parent,false);
        viewHolder isi = new viewHolder(daftar);
        return isi;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.course.setText(CourseList.get(position).getJudul());
    }

    @Override
    public int getItemCount() {
        if(CourseList == null){
            return 0;
        }
        return CourseList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView course;
        CardView CC;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
        course = itemView.findViewById(R.id.course);
        CC = itemView.findViewById(R.id.maincard);
        CC.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        if (CC.getId()==view.getId()){
            int a = getAdapterPosition();
            Intent peh = new Intent(view.getContext(),Materi_activity.class);
            peh.putExtra("titelG",CourseList.get(a).getJudul());
            peh.putExtra("docid",CourseList.get(a).getKey());
            view.getContext().startActivity(peh);
        }
        }
    }
}

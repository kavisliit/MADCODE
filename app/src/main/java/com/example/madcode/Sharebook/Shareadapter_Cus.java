package com.example.madcode.Sharebook;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madcode.R;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class Shareadapter_Cus extends RecyclerView.Adapter<Shareadapter_Cus.MyViewHolder>{
    Context con;
    ArrayList<Modelshare> list;
    private static RecyclerViewClickListner listner;
    public Shareadapter_Cus(Context con, ArrayList<Modelshare> list, RecyclerViewClickListner listner) {
        this.listner = listner;
        this.con = con;
        this.list = list;
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(con).inflate(R.layout.sbook,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull Shareadapter_Cus.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Modelshare ob = list.get(position);
        holder.sbookname.setText(ob.getSbookname());
        holder.sbookauthor.setText(ob.getSbookauthor());
        holder.sbookcategory.setText(ob.getSbookcategory());
        holder.sbookdes.setText(ob.getSbookdescription());
        //holder.purl.setText(ob.getPurl());
        Glide.with(holder.surl.getContext()).load(ob.getSurl()).into(holder.surl);
    }
    //Get item count
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView sbookname,sbookauthor,sbookcategory, sbookdes;
        ImageView surl;
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            sbookname = itemView.findViewById(R.id.msbookname);
            sbookauthor = itemView.findViewById(R.id.msboookauthor);
            sbookcategory = itemView.findViewById(R.id.msboookcate);

            surl = itemView.findViewById(R.id.sbookimg2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listner.onClick(v,getAdapterPosition());
        }
    }

    public interface RecyclerViewClickListner{
        void onClick(View v, int position);
    }
}
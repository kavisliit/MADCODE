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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Shareadapter_Cus extends RecyclerView.Adapter<Shareadapter_Cus.MyViewHolder>{

    Context context;
    ArrayList<Modelshare>list;
    private static Shareadapter_Cus.RecyclerViewClickListner listner;
    public Shareadapter_Cus(Context context, ArrayList<Modelshare> list, Shareadapter_Cus.RecyclerViewClickListner listner) {
        this.listner = listner;
        this.context = context;
        this.list = list;

    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Shareadapter_Cus.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sbook,parent,false);
        return new Shareadapter_Cus.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Shareadapter_Cus.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


        Modelshare book = list.get(position);
        holder.sbookname.setText(book.getSbookname());
        holder.sbookcategory.setText(book.getSbookcategory());
        holder.sbookauthor.setText(book.getSbookauthor());
        holder.sbookdes.setText(book.getSbookdes());
        Glide.with(holder.surl.getContext()).load(book.getSurl()).into(holder.surl);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView sbookname;
        TextView sbookcategory;
        TextView sbookauthor;
        TextView sbookdes;
        ImageView surl;
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            sbookname = itemView.findViewById(R.id.msbookname);
            sbookcategory = itemView.findViewById(R.id.msboookcate);
            sbookauthor = itemView.findViewById(R.id.msboookauthor);
            sbookdes = itemView.findViewById(R.id.msbookdes);
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

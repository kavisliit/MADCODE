package com.example.madcode.Events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madcode.Events.Models.Comment;
import com.example.madcode.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Comment_adapter extends RecyclerView.Adapter<Comment_adapter.MyViewHolder>{
    Context con;
    ArrayList<Comment> list;


    public Comment_adapter(Context con, ArrayList<Comment> list) {

        this.con = con;
        this.list = list;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Comment_adapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(con).inflate(R.layout.single_comment,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Comment_adapter.MyViewHolder holder, int position) {
        Comment ob = list.get(position);
        holder.t1.setText(ob.getOname());
        holder.t2.setText(ob.getComment());
        Glide.with(holder.img.getContext()).load(ob.getOuri()).into(holder.img);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView t1,t2;
        ImageView img;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.comment_name);
            t2 = itemView.findViewById(R.id.comment_comment);
            img = itemView.findViewById(R.id.comment_image);


        }





    }



}
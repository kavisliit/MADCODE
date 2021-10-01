package com.example.madcode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madcode.Events.Models.main_model;

import java.util.ArrayList;

public class main_adapter extends RecyclerView.Adapter<main_adapter.viewHolder> {
    ArrayList<main_model> mainmodel;
    Context context;

    public main_adapter(Context context,ArrayList<main_model> mainmodel){
        this.context = context;
        this.mainmodel = mainmodel;
    }
    @NonNull
    @Override
    public main_adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_event_single,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull main_adapter.viewHolder holder, int position) {
        Glide.with(holder.imageView.getContext()).load(mainmodel.get(position).uri).into(holder.imageView);
        holder.textView.setText(mainmodel.get(position).title);
    }

    @Override
    public int getItemCount() {
        return mainmodel.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.main_event_single_img);
            textView = itemView.findViewById(R.id.main_event_single_text);
        }
    }
}

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
import com.example.madcode.Events.Models.Event;
import com.example.madcode.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Event_adapter extends RecyclerView.Adapter<Event_adapter.MyViewHolder>{
    Context con;
    ArrayList<Event> list;
    private static RecyclerViewClickListner listner;

    public Event_adapter(Context con, ArrayList<Event> list,RecyclerViewClickListner listner) {
        this.listner = listner;
        this.con = con;
        this.list = list;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(con).inflate(R.layout.single_event,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Event_adapter.MyViewHolder holder, int position) {
        Event ob = list.get(position);
        holder.name.setText(ob.getName());
        Glide.with(holder.iv.getContext()).load(ob.getUri()).into(holder.iv);
        holder.Date.setText(ob.getDate());
        holder.Time.setText(ob.getTime());
        holder.max.setText(ob.getMax());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        TextView name,venue,Date,Time,max;
        ImageView iv;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.e_txt1);
            Time = itemView.findViewById(R.id.e_txt2);
            Date = itemView.findViewById(R.id.e_txt3);
            max= itemView.findViewById(R.id.e_txt4);
            iv = itemView.findViewById(R.id.img_event);
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

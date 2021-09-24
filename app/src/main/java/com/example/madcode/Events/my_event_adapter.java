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

public class my_event_adapter extends RecyclerView.Adapter<my_event_adapter.MyViewHolder>{
    Context con;
    ArrayList<Event> list;
    private static RecyclerViewClickListner listner;

    public my_event_adapter(Context con, ArrayList<Event> list,RecyclerViewClickListner listner) {
        this.listner = listner;
        this.con = con;
        this.list = list;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public my_event_adapter.MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(con).inflate(R.layout.my_event_single,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull my_event_adapter.MyViewHolder holder, int position) {
        Event ob = list.get(position);
        holder.name.setText(ob.getName());
        holder.date.setText(ob.getDate());
       Glide.with(holder.im1.getContext()).load(ob.getUri()).into(holder.im1);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder  implements  View.OnClickListener{

        TextView name,date;
        ImageView im1;

        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.event_name_txt);
            im1 = itemView.findViewById(R.id.event_pic_btn);
            date = itemView.findViewById(R.id.event_name_txt2);
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


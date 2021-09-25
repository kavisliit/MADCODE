package com.example.madcode.Events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.Events.Models.User;
import com.example.madcode.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class member_adapter extends RecyclerView.Adapter<member_adapter.MyViewHolder2>{
    Context con;
    ArrayList<User> list;


    public member_adapter(Context con, ArrayList<User> list) {

        this.con = con;
        this.list = list;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(con).inflate(R.layout.single_member_event,parent,false);
        return new MyViewHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull member_adapter.MyViewHolder2 holder, int position) {
        User ob = list.get(position);
        holder.name.setText(ob.getUname());
        holder.name2.setText(ob.email);
       // Glide.with(holder.iv.getContext()).load(ob.getUrl()).into(holder.iv);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder2 extends RecyclerView.ViewHolder{

        TextView name,name2;
        ImageView iv;

        public MyViewHolder2(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.event_name_txt);
            name2 = itemView.findViewById(R.id.mem_text2);
            iv = itemView.findViewById(R.id.event_pic_btn);


        }


    }



}

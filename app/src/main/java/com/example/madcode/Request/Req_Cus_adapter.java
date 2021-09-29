package com.example.madcode.Request;


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

public class Req_Cus_adapter extends RecyclerView.Adapter<Req_Cus_adapter.MyViewHolder>{
    Context con;
    ArrayList<reqmodal> list;
    private static RecyclerViewClickListner listner;
    public Req_Cus_adapter(Context con, ArrayList<reqmodal> list, RecyclerViewClickListner listner) {
        this.listner = listner;
        this.con = con;
        this.list = list;
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(con).inflate(R.layout.reqsingleview,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull Req_Cus_adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        reqmodal ob = list.get(position);
        holder.book_name.setText(ob.getBook_name());
        holder.book_authur.setText(ob.getBook_authur());
        holder.book_publisher.setText(ob.getBook_publisher());
        holder.book_description.setText(ob.getBook_description());
        //holder.purl.setText(ob.getPurl());
        Glide.with(holder.ReqUrl.getContext()).load(ob.getReqUrl()).into(holder.ReqUrl);
    }
    //Get item count
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView book_name,book_authur,book_publisher, book_description;
        ImageView ReqUrl;
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            book_name = itemView.findViewById(R.id.reqheading);
            book_authur = itemView.findViewById(R.id.reqauthur);
            book_publisher = itemView.findViewById(R.id.reqpublisher);
            book_description= itemView.findViewById(R.id.reqdiscription);

            ReqUrl = itemView.findViewById(R.id.reqviewimg);
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
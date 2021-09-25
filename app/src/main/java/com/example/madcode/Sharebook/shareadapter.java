package com.example.madcode.Sharebook;

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

import java.util.ArrayList;


public class shareadapter extends RecyclerView.Adapter<com.example.madcode.Sharebook.shareadapter.MyViewHolder>{

    Context context;

    ArrayList<Modelshare>list;

    public shareadapter(Context context, ArrayList<Modelshare> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.sbook,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        Modelshare book = list.get(position);
        holder.sbookname.setText(book.getSbookname());
        holder.sbookcategory.setText(book.getSbookcategory());
        holder.sbookauthor.setText(book.getSbookauthor());
        Glide.with(holder.surl.getContext()).load(book.getSurl()).into(holder.surl);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView sbookname;
        TextView sbookcategory;
        TextView sbookauthor;
        ImageView surl;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            sbookname = itemView.findViewById(R.id.msbookname);
            sbookcategory = itemView.findViewById(R.id.msboookcate);
            sbookauthor = itemView.findViewById(R.id.msboookauthor);
            surl = itemView.findViewById(R.id.sbookimg2);
        }

    }

}

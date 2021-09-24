package com.example.madcode.Article;

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
import com.example.madcode.Article.Model.ArticleModel;
import com.example.madcode.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class Article_adapter extends RecyclerView.Adapter<Article_adapter.MyViewHolder>{
    Context con;
    ArrayList<ArticleModel> list;
    private static RecyclerViewClickListner listner;
    public Article_adapter(Context con, ArrayList<ArticleModel> list, RecyclerViewClickListner listner) {
        this.listner = listner;
        this.con = con;
        this.list = list;
    }
    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(con).inflate(R.layout.article_single_event,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull Article_adapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        ArticleModel ob = list.get(position);
        holder.Head_line.setText(ob.getHead_line());
        holder.Small_description.setText(ob.getSmall_description());
        holder.Sub_topic.setText(ob.getSub_topic());
        holder.Description.setText(ob.getDescription());
        //holder.purl.setText(ob.getPurl());
        Glide.with(holder.propic.getContext()).load(ob.getPurl()).into(holder.propic);


    }
    //Get item count
    @Override
    public int getItemCount() {
        return list.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView Head_line,Small_description,Sub_topic,Description;
        ImageView propic;


        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            Head_line = itemView.findViewById(R.id.article_txt1);
            Small_description = itemView.findViewById(R.id.article_txt2);
            Sub_topic = itemView.findViewById(R.id.article_txt3);
            Description= itemView.findViewById(R.id.article_txt4);
            //purl = itemView.findViewById(R.id.article_txt);
            propic = itemView.findViewById(R.id.image);
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
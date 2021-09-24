package com.example.madcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class ArticleMainPageCustomer extends AppCompatActivity {

    RecyclerView rec;
    DatabaseReference database;
    Article_adapter_customer adapter;
    ArrayList<ArticleModel> list;
    ArrayList<String> getArticleId = new ArrayList<>();
    String ArticleId;
    private Article_adapter_customer.RecyclerViewClickListner listner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_main_page_customer);


        setOnClickListner();
        rec = findViewById(R.id.recyclerView2);
        //get reference form database
        database = FirebaseDatabase.getInstance().getReference("ArticleModel");
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new Article_adapter_customer(this,list,listner);
        rec.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    ArticleId = dn.getKey();
                    ArticleModel ev = dn.getValue(ArticleModel.class);
                    list.add(ev);
                    getArticleId.add(ArticleId);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }



    private void setOnClickListner() {
        listner = new Article_adapter_customer.RecyclerViewClickListner() {
            @Override
            public void onClick(View v, int position) {
                Intent in = new Intent(getApplicationContext(), ArticleViewPageCustomer.class);

                in.putExtra("AID",getArticleId.get(position));
                //pass the data to view page (display)
                in.putExtra("Head_line",list.get(position).getHead_line());
                in.putExtra("Small_description",list.get(position).getSmall_description());
                in.putExtra("Sub_topic",list.get(position).getSub_topic());
                in.putExtra("Description",list.get(position).getDescription());
                in.putExtra("propic",list.get(position).getPurl());

                startActivity(in);
            }
        };

    }

}
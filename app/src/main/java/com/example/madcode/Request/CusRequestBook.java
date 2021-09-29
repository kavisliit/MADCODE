package com.example.madcode.Request;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class CusRequestBook extends AppCompatActivity {

    RecyclerView rec;
    DatabaseReference database;
    Req_Cus_adapter adapter;
    ArrayList<reqmodal> list;
    ArrayList<String> getReqId = new ArrayList<>();
    String ReqId;
    private Req_Cus_adapter.RecyclerViewClickListner listner;
    FirebaseUser reqfire;
    String requserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_request_book);

        setOnClickListner();
        rec = findViewById(R.id.allreqview);
        database = FirebaseDatabase.getInstance().getReference("reqmodal");
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new Req_Cus_adapter(this,list,listner);
        rec.setAdapter(adapter);
        requserid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    reqmodal ev = dn.getValue(reqmodal.class);
                        list.add(ev);
                        getReqId.add(ReqId);


                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    private void setOnClickListner() {
        listner = new Req_Cus_adapter.RecyclerViewClickListner() {
            @Override
            public void onClick(View v, int position) {
                Intent in = new Intent(getApplicationContext(), CusReqView.class);
                in.putExtra("RID",getReqId.get(position));
                in.putExtra("book_name",list.get(position).getBook_name());
                in.putExtra("book_authur",list.get(position).getBook_authur());
                in.putExtra("book_publisher",list.get(position).getBook_publisher());
                in.putExtra("book_description",list.get(position).getBook_description());
                in.putExtra("ReqUrl",list.get(position).getReqUrl());
                startActivity(in);

            }
        };
    }
}
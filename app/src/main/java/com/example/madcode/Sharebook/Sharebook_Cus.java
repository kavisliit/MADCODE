package com.example.madcode.Sharebook;


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

public class Sharebook_Cus extends AppCompatActivity {

    RecyclerView rec;
    DatabaseReference database;
    Shareadapter_Cus adapter;
    ArrayList<Modelshare> list;
    ArrayList<String> getSID = new ArrayList<>();
    String SID;
    private Shareadapter_Cus.RecyclerViewClickListner listner;
    FirebaseUser sharebook;
    String shareuserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharebook_cus);

        setOnClickListner();
        rec = findViewById(R.id.allshareview);
        database = FirebaseDatabase.getInstance().getReference("Modelshare");
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new Shareadapter_Cus(this,list,listner);
        rec.setAdapter(adapter);
        shareuserid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    Modelshare sh = dn.getValue(Modelshare.class);
                    list.add(sh);
                    getSID.add(SID);


                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    private void setOnClickListner() {
        listner = new Shareadapter_Cus.RecyclerViewClickListner(){
            @Override
            public void onClick(View v, int position) {
                Intent in = new Intent(getApplicationContext(), Sharebookview_Cus.class);
                in.putExtra("SID",getSID.get(position));
                in.putExtra("sbookname",list.get(position).getSbookname());
                in.putExtra("sbookauthor",list.get(position).getSbookauthor());
                in.putExtra("sbookcategory",list.get(position).getSbookcategory());
                in.putExtra("sbookdes",list.get(position).getSbookdescription());
                in.putExtra("surl",list.get(position).getSurl());
                startActivity(in);

            }
        };
    }
}
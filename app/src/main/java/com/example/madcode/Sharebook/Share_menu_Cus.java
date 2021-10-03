package com.example.madcode.Sharebook;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class Share_menu_Cus extends AppCompatActivity {


    FloatingActionButton fb;
    RecyclerView recyclerView;
    DatabaseReference database;
    Shareadapter_Cus shareadapter_cus;
    ArrayList<Modelshare> list;
    ArrayList<String> getShareBookId = new ArrayList<>();
    String ShareBookId;
    private Shareadapter_Cus.RecyclerViewClickListner listner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_menu_cus);

        setOnClickListner();
        recyclerView = findViewById(R.id.allshareview);

        database = FirebaseDatabase.getInstance().getReference("sharebook");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        shareadapter_cus = new Shareadapter_Cus(this,list,listner);
        recyclerView.setAdapter(shareadapter_cus);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @com.google.firebase.database.annotations.NotNull DataSnapshot snapshot) {
                for(DataSnapshot sb : snapshot.getChildren()){
                    ShareBookId = sb.getKey();
                    Modelshare ev = sb.getValue(Modelshare.class);
                    list.add(ev);
                    getShareBookId.add(ShareBookId);
                }
                shareadapter_cus.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }



    private void setOnClickListner() {
        listner = new Shareadapter_Cus.RecyclerViewClickListner() {
            @Override
            public void onClick(View v, int position) {
                Intent in = new Intent(getApplicationContext(), Sharebook_viewpage_cus.class);

                in.putExtra("ShareBookId",getShareBookId.get(position));

                in.putExtra("sbookname",list.get(position).getSbookname());
                in.putExtra("sbookcategory",list.get(position).getSbookcategory());
                in.putExtra("sbookauthor",list.get(position).getSbookauthor());
                in.putExtra("sbookdes",list.get(position).getSbookdes());
                in.putExtra("surl",list.get(position).getSurl());

                startActivity(in);
            }
        };

    }



    public void sharebooktocreate(View view){
        Intent intent = new Intent(this, Sharebook_create.class);
        startActivity(intent);
    }

}
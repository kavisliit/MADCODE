package com.example.madcode.Events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.Events.Models.Event;
import com.example.madcode.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class user_events extends AppCompatActivity {

    FloatingActionButton fbtn;
    RecyclerView rec;

    Event_adapter adapter;
    ArrayList<Event> list;
    FirebaseUser us;
    DatabaseReference db;
    private Event_adapter.RecyclerViewClickListner listner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_events);

        setOnClickListner();
        rec = findViewById(R.id.recyclerView2);
        us = FirebaseAuth.getInstance().getCurrentUser();
        String uid = us.getUid();
        db = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Events");
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));
        fbtn = findViewById(R.id.f_btn2);
        list = new ArrayList<>();
        adapter = new Event_adapter(this,list,listner);
        rec.setAdapter(adapter);

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    Event ev = dn.getValue(Event.class);
                    list.add(ev);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    private void setOnClickListner() {
        listner = new Event_adapter.RecyclerViewClickListner() {
            @Override
            public void onClick(View v, int position) {
                Intent in = new Intent(getApplicationContext(), event_profile.class);
                in.putExtra("username",list.get(position).getName());
                startActivity(in);
            }
        };
    }

    public void process(View view){
        startActivity(new Intent(getApplicationContext(), Add_Event.class));
    }
    }

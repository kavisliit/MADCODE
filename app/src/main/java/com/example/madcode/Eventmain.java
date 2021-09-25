package com.example.madcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.Events.Add_Event;
import com.example.madcode.Events.Event_adapter;
import com.example.madcode.Events.event_profile;
import com.example.madcode.Events.Models.Event;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class  Eventmain extends nav_activity{


    //ViewFlipper flipper1;
    FloatingActionButton fbtn;
    RecyclerView rec;
    DatabaseReference database;
    Event_adapter adapter;
    ArrayList<Event> list;
    ArrayList<String> getid = new ArrayList<>();
    String id;
    private Event_adapter.RecyclerViewClickListner listner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_eventmain);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_eventmain,null,false);
        cl.addView(v,0);


//        flipper1 = findViewById(R.id.view_flipper1);
//        int images[] = {R.drawable.event_main,R.drawable.event_main2,R.drawable.event_main3};
//
//        for(int image:images){
//            flipperimages(image);
//        }


        setOnClickListner();
        rec = findViewById(R.id.recyclerView1);
        database = FirebaseDatabase.getInstance().getReference("Events");
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));
        fbtn = findViewById(R.id.f_btn1);
        list = new ArrayList<>();
        adapter = new Event_adapter(this,list,listner);
        rec.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    id = dn.getKey();
                    Event ev = dn.getValue(Event.class);
                    list.add(ev);
                   getid.add(id);

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
                in.putExtra("eid",getid.get(position));
                in.putExtra("name",list.get(position).getName());
                in.putExtra("date",list.get(position).getDate());
                in.putExtra("time",list.get(position).getTime());
                in.putExtra("type",list.get(position).getType());
                in.putExtra("max",list.get(position).getMax());
                in.putExtra("cid",list.get(position).getCid());
                in.putExtra("uri",list.get(position).getUri());


                startActivity(in);
            }
        };
    }

    public void process(View view){
        startActivity(new Intent(getApplicationContext(), Add_Event.class));
    }


//    public void flipperimages(int image){
//        ImageView iw = new ImageView(this);
//        iw.setBackgroundResource(image);
//
//        flipper1.addView(iw);
//        flipper1.setFlipInterval(4000);
//        flipper1.setAutoStart(true);
//
//        flipper1.setInAnimation(this, android.R.anim.slide_in_left);
//        flipper1.setOutAnimation(this, android.R.anim.slide_out_right);
//    }
}


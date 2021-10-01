package com.example.madcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.Article.ArticleMainPageCustomer;
import com.example.madcode.Events.Add_Event;
import com.example.madcode.Events.Event_adapter;
import com.example.madcode.Events.event_profile;
import com.example.madcode.Events.Models.Event;
import com.example.madcode.Request.RequestBook;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class  Eventmain extends nav_activity{



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


        BottomNavigationView bv = findViewById(R.id.bottom_nav_main);
        bv.setSelectedItemId(R.id.navigation_event);

        bv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_event:
                        return true;

                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_book:
                        startActivity(new Intent(getApplicationContext(), Add_Event.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.navigation_article:
                        startActivity(new Intent(getApplicationContext(), ArticleMainPageCustomer.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_request:
                        startActivity(new Intent(getApplicationContext(), RequestBook.class));
                        overridePendingTransition(0,0);
                        return true;


                }
                return false;
            }
        });



        setOnClickListner();
        rec = findViewById(R.id.recyclerView1);
        database = FirebaseDatabase.getInstance().getReference("Events");
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));
        fbtn = findViewById(R.id.f_btn1);
        list = new ArrayList<>();
        adapter = new Event_adapter(this,list,listner);
        rec.setAdapter(adapter);

        //method for get data from DB
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



}


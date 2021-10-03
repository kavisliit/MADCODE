package com.example.madcode.Events;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.Article.My_Article;
import com.example.madcode.Events.Models.Event;
import com.example.madcode.MainActivity;
import com.example.madcode.R;
import com.example.madcode.Request.RequestBook;
import com.example.madcode.Sharebook.Share_menu;
import com.example.madcode.User.user_profile;
import com.example.madcode.login;
import com.example.madcode.nav_activity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class my_event_list extends nav_activity {

    DatabaseReference database;
    RecyclerView rec;
    FloatingActionButton fb2;
    my_event_adapter adapter;
    ArrayList<Event> list;
    ArrayList<String> eidlist = new ArrayList<>();
    String eventid;
    String id,curuser;
   private my_event_adapter.RecyclerViewClickListner listner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_my_event_list);
        fb2 = findViewById(R.id.created_event_button);


        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_my_event_list,null,false);
        cl.addView(v,0);

        DrawerLayout dl = findViewById(R.id.drawer);
        NavigationView nav = findViewById(R.id.navwiew);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(my_event_list.this, MainActivity.class));
                        break;

                    case R.id.profile:
                        startActivity(new Intent(my_event_list.this, user_profile.class));
                        break;

                    case R.id.mybooks:
                        startActivity(new Intent(my_event_list.this, Share_menu.class));
                        break;

                    case R.id.My_Requests:
                        startActivity(new Intent(my_event_list.this, RequestBook.class));
                        break;

                    case R.id.My_Articles:
                        startActivity(new Intent(my_event_list.this, My_Article.class));
                        break;

                    case R.id.My_Events:
                        startActivity(new Intent(my_event_list.this, my_event_list.class));
                        break;

                    case R.id.logout_2:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(my_event_list.this, login.class));
                        break;


                }
                return false;
            }
        });



        setOnClickListner();

        rec = findViewById(R.id.my_event_cycel);


        curuser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        list = new ArrayList<>();
        adapter = new my_event_adapter(this, list, listner);
        rec.setLayoutManager(new LinearLayoutManager(this));
        rec.setHasFixedSize(true);
        rec.setItemAnimator(new DefaultItemAnimator());
        rec.setAdapter(adapter);



            database = FirebaseDatabase.getInstance().getReference("Events");
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    for (DataSnapshot dn : snapshot.getChildren()) {
                        String id = dn.getKey();
                        Event ev = dn.getValue(Event.class);
                        if (ev.getCid().equals(curuser)) {
                            eventid = id;
                            eidlist.add(eventid);
                            list.add(ev);
                        }

                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });


    }
    private void setOnClickListner() {
        listner = new my_event_adapter.RecyclerViewClickListner() {
            @Override
            public void onClick(View v, int position) {

                Intent in = new Intent(getApplicationContext(), My_event_profile.class);
                in.putExtra("eid",eidlist.get(position));
                in.putExtra("name",list.get(position).getName());
                in.putExtra("date",list.get(position).getDate());
                in.putExtra("venue",list.get(position).getVenue());
                in.putExtra("time",list.get(position).getTime());
                in.putExtra("type",list.get(position).getType());
                in.putExtra("max",list.get(position).getMax());
                in.putExtra("cid",list.get(position).getCid());
                in.putExtra("uri",list.get(position).getUri());


                startActivity(in);
            }
        };
    }

    public void addev(View view){
        startActivity(new Intent(my_event_list.this,Add_Event.class));
    }

}
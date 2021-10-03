package com.example.madcode;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.Article.ArticleMainPageCustomer;
import com.example.madcode.Article.My_Article;
import com.example.madcode.Events.Add_Event;
import com.example.madcode.Events.Event_adapter;
import com.example.madcode.Events.Models.Event;
import com.example.madcode.Events.event_profile;
import com.example.madcode.Events.my_event_list;
import com.example.madcode.Request.CusRequestBook;
import com.example.madcode.Request.RequestBook;
import com.example.madcode.Sharebook.Share_menu;
import com.example.madcode.User.user_profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
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
    EditText search;
    String id;
    private Event_adapter.RecyclerViewClickListner listner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_eventmain);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_eventmain,null,false);
        cl.addView(v,0);
        search = findViewById(R.id.searchevent);
       DrawerLayout dl = findViewById(R.id.drawer);
       NavigationView nav = findViewById(R.id.navwiew);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(Eventmain.this,MainActivity.class));
                        break;

                    case R.id.profile:
                        startActivity(new Intent(Eventmain.this, user_profile.class));
                        break;

                    case R.id.mybooks:
                       // startActivity(new Intent(Eventmain.this, user_profile.class));
                        break;

                    case R.id.My_Requests:
                        startActivity(new Intent(Eventmain.this, RequestBook.class));
                        break;

                    case R.id.My_Articles:
                        startActivity(new Intent(Eventmain.this, My_Article.class));
                        break;

                    case R.id.My_Events:
                        startActivity(new Intent(Eventmain.this, my_event_list.class));
                        break;

                    case R.id.logout_2:
                        //startActivity(new Intent(Eventmain.this, RequestBook.class));
                        break;


                }
                return false;
            }
        });


        BottomNavigationView bv = findViewById(R.id.bottom_nav_main);
        bv.setSelectedItemId(R.id.navigation_event);

        bv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_event:

                        return true;

                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_book:
                        startActivity(new Intent(getApplicationContext(), Share_menu.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_article:
                        startActivity(new Intent(getApplicationContext(), ArticleMainPageCustomer.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_request:
                        startActivity(new Intent(getApplicationContext(), CusRequestBook.class));
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



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });


    }
    private void filter(String text){
        ArrayList<Event> filterlist = new ArrayList<>();
        for(Event item:list){
            if(item.getName().toLowerCase().contains(text.toLowerCase())){
                filterlist.add(item);
            }
        }
        adapter.filterlist(filterlist);
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


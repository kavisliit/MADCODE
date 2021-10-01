package com.example.madcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.Article.ArticleMainPageCustomer;
import com.example.madcode.Events.Add_Event;
import com.example.madcode.Events.Models.Event;
import com.example.madcode.Events.Models.main_model;
import com.example.madcode.Request.RequestBook;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends nav_activity {
    RecyclerView event1;
    DatabaseReference db;
    main_adapter ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
               BottomNavigationView bv = findViewById(R.id.bottom_nav_main);
        bv.setSelectedItemId(R.id.navigation_home);
        event1 = findViewById(R.id.event_recycle);

        bv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_event:
                        startActivity(new Intent(getApplicationContext(),Eventmain.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_home:
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


        ArrayList<main_model> eventuri = new ArrayList<>();
        db = FirebaseDatabase.getInstance().getReference("Events");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    Event ob = dn.getValue(Event.class);
                    main_model ob1 = new main_model();
                    ob1.title = ob.getName();
                    ob1.uri = ob.getUri();
                    eventuri.add(ob1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        event1.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                MainActivity.this,LinearLayoutManager.HORIZONTAL,false
        );

        event1.setLayoutManager(layoutManager);

        event1.setItemAnimator(new DefaultItemAnimator());

        ma = new main_adapter(MainActivity.this,eventuri);

        event1.setAdapter(ma);

    }


//    public void MainToMyCreate(View view){
//        Intent intent = new Intent(this, My_Article.class);
//        startActivity(intent);
//    }
//    public void MainToArticles(View view){
//        Intent intent = new Intent(this, ArticleMainPageCustomer.class);
//        startActivity(intent);
//    }
//    public void MainToEventMain(View view){
//        Intent intent = new Intent(this, user_profile.class);
//        startActivity(intent);
//    }
//    public void MainToRequestBook(View view){
//        Intent intent = new Intent(this, RequestBook.class);
//        startActivity(intent);
//    }

}
package com.example.madcode;

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

import com.example.madcode.Article.ArticleMainPageCustomer;
import com.example.madcode.Article.Model.ArticleModel;
import com.example.madcode.Article.My_Article;
import com.example.madcode.Events.Models.Event;
import com.example.madcode.Events.Models.main_model;
import com.example.madcode.Events.my_event_list;
import com.example.madcode.Request.CusRequestBook;
import com.example.madcode.Request.RequestBook;
import com.example.madcode.Request.reqmodal;
import com.example.madcode.Sharebook.Modelshare;
import com.example.madcode.Sharebook.share_menu;
import com.example.madcode.User.user_profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

//import com.example.madcode.Request.CusRequestBook;
//import com.example.madcode.Sharebook.share_menu;
//import com.example.madcode.User.user_profile;


public class MainActivity extends nav_activity {
    RecyclerView event1,article1,request1,book1;
    DatabaseReference db;
    main_adapter ma;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      // setContentView(R.layout.activity_main);

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_main,null,false);
        cl.addView(v,0);

        DrawerLayout dl = findViewById(R.id.drawer);
        NavigationView nav = findViewById(R.id.navwiew);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(MainActivity.this,MainActivity.class));
                        break;

                    case R.id.profile:
                        startActivity(new Intent(MainActivity.this, user_profile.class));
                        break;

                    case R.id.mybooks:
                        // startActivity(new Intent(Eventmain.this, user_profile.class));
                        break;

                    case R.id.My_Requests:
                        startActivity(new Intent(MainActivity.this, RequestBook.class));
                        break;

                    case R.id.My_Articles:
                        startActivity(new Intent(MainActivity.this, My_Article.class));
                        break;

                    case R.id.My_Events:
                        startActivity(new Intent(MainActivity.this, my_event_list.class));
                        break;

                    case R.id.logout_2:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this, login.class));
                        break;


                }
                return false;
            }
        });



        BottomNavigationView bv = findViewById(R.id.bottom_nav_main);
        bv.setSelectedItemId(R.id.navigation_home);
        event1 = findViewById(R.id.event_recycle);
        article1 = findViewById(R.id.article_recycle_main);
        request1 = findViewById(R.id.request_recycle);
        book1 = findViewById(R.id.book_recycle);

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
                        startActivity(new Intent(getApplicationContext(), share_menu.class));
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


        ArrayList<main_model> bookuri = new ArrayList<>();
        db = FirebaseDatabase.getInstance().getReference("sharebook");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    Modelshare ob = dn.getValue(Modelshare.class);
                    main_model ob1 = new main_model();
                    ob1.title = ob.getSbookname();
                    ob1.uri = ob.getSurl();
                    bookuri.add(ob1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        book1.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                MainActivity.this,LinearLayoutManager.HORIZONTAL,false
        );

        book1.setLayoutManager(layoutManager);

        book1.setItemAnimator(new DefaultItemAnimator());

        ma = new main_adapter(MainActivity.this,bookuri);

        book1.setAdapter(ma);



        ArrayList<main_model> articleuri = new ArrayList<>();
        db = FirebaseDatabase.getInstance().getReference("ArticleModel");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    ArticleModel ob = dn.getValue(ArticleModel.class);
                    main_model ob1 = new main_model();
                    ob1.title = ob.getHead_line();
                    ob1.uri = ob.getPurl();
                    articleuri.add(ob1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        article1.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(
                MainActivity.this,LinearLayoutManager.HORIZONTAL,false
        );

        article1.setLayoutManager(layoutManager1);

        article1.setItemAnimator(new DefaultItemAnimator());

        ma = new main_adapter(MainActivity.this,articleuri);

        article1.setAdapter(ma);






        ArrayList<main_model> requesturi = new ArrayList<>();
        db = FirebaseDatabase.getInstance().getReference("reqmodal");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    reqmodal ob = dn.getValue(reqmodal.class);
                    main_model ob1 = new main_model();
                    ob1.title = ob.getBook_name();
                    ob1.uri = ob.getReqUrl();
                    requesturi.add(ob1);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        request1.setHasFixedSize(true);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(
                MainActivity.this,LinearLayoutManager.HORIZONTAL,false
        );

        request1.setLayoutManager(layoutManager2);

        request1.setItemAnimator(new DefaultItemAnimator());

        ma = new main_adapter(MainActivity.this,requesturi);

        request1.setAdapter(ma);



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
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(
                MainActivity.this,LinearLayoutManager.HORIZONTAL,false
        );

        event1.setLayoutManager(layoutManager3);

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


   // public void MainToAllRequestBook(View view){
  //      Intent intent = new Intent(this, CusRequestBook.class);
  //      startActivity(intent);
  //  }

 //   public void MainToSharemenu(View view) {
 //       Intent intent = new Intent(this, share_menu.class);
 //       startActivity(intent);
  //  }

}
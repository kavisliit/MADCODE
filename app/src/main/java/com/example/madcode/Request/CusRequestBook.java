package com.example.madcode.Request;


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
import com.example.madcode.Article.Model.ArticleModel;
import com.example.madcode.Article.My_Article;
import com.example.madcode.Eventmain;
import com.example.madcode.Events.my_event_list;
import com.example.madcode.MainActivity;
import com.example.madcode.R;
import com.example.madcode.Sharebook.Share_menu;
import com.example.madcode.Sharebook.Share_menu_Cus;
import com.example.madcode.User.user_profile;
import com.example.madcode.login;
import com.example.madcode.nav_activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class CusRequestBook extends nav_activity {

    RecyclerView rec;
    DatabaseReference database;
    Req_Cus_adapter adapter;
    ArrayList<reqmodal> list;
    ArrayList<String> getReqId = new ArrayList<>();
    String ReqId;
    private Req_Cus_adapter.RecyclerViewClickListner listner;
    FirebaseUser reqfire;
    String requserid;

    EditText searchTextCustomerRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_cus_request_book);

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_cus_request_book,null,false);
        cl.addView(v,0);

        DrawerLayout dl = findViewById(R.id.drawer);
        NavigationView nav = findViewById(R.id.navwiew);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(CusRequestBook.this,MainActivity.class));
                        break;

                    case R.id.profile:
                        startActivity(new Intent(CusRequestBook.this, user_profile.class));
                        break;

                    case R.id.mybooks:
                         startActivity(new Intent(CusRequestBook.this, Share_menu.class));
                        break;

                    case R.id.My_Requests:
                        startActivity(new Intent(CusRequestBook.this, RequestBook.class));
                        break;

                    case R.id.My_Articles:
                        startActivity(new Intent(CusRequestBook.this, My_Article.class));
                        break;

                    case R.id.My_Events:
                        startActivity(new Intent(CusRequestBook.this, my_event_list.class));
                        break;

                    case R.id.logout_2:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(CusRequestBook.this, login.class));
                        break;


                }
                return false;
            }
        });
        BottomNavigationView bv = findViewById(R.id.bottom_nav_main);
        bv.setSelectedItemId(R.id.navigation_request);

        bv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_event:
                        startActivity(new Intent(getApplicationContext(), Eventmain.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_book:
                        startActivity(new Intent(getApplicationContext(), Share_menu_Cus.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_article:
                        startActivity(new Intent(getApplicationContext(), ArticleMainPageCustomer.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_request:
                        return true;

                }
                return false;
            }
        });

        searchTextCustomerRequest = findViewById(R.id.search_req_book_customer);
        searchTextCustomerRequest.addTextChangedListener(new TextWatcher() {
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

    private void filter(String text){
        ArrayList<reqmodal> filteredList = new ArrayList<>();
        for(reqmodal item:list){
            if(item.getBook_name().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
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
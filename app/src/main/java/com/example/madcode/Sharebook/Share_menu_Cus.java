package com.example.madcode.Sharebook;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.Article.ArticleMainPageCustomer;
import com.example.madcode.Article.My_Article;
import com.example.madcode.Eventmain;
import com.example.madcode.Events.my_event_list;
import com.example.madcode.MainActivity;
import com.example.madcode.R;
import com.example.madcode.Request.CusRequestBook;
import com.example.madcode.Request.RequestBook;
import com.example.madcode.User.user_profile;
import com.example.madcode.login;
import com.example.madcode.nav_activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
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

public class Share_menu_Cus extends nav_activity {


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
       // setContentView(R.layout.activity_share_menu_cus);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_share_menu_cus,null,false);
        cl.addView(v,0);
        DrawerLayout dl = findViewById(R.id.drawer);
        NavigationView nav = findViewById(R.id.navwiew);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(Share_menu_Cus.this, MainActivity.class));
                        break;

                    case R.id.profile:
                        startActivity(new Intent(Share_menu_Cus.this, user_profile.class));
                        break;

                    case R.id.mybooks:
                         startActivity(new Intent(Share_menu_Cus.this, Share_menu.class));
                        break;

                    case R.id.My_Requests:
                        startActivity(new Intent(Share_menu_Cus.this, RequestBook.class));
                        break;

                    case R.id.My_Articles:
                        startActivity(new Intent(Share_menu_Cus.this, My_Article.class));
                        break;

                    case R.id.My_Events:
                        startActivity(new Intent(Share_menu_Cus.this, my_event_list.class));
                        break;

                    case R.id.logout_2:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(Share_menu_Cus.this, login.class));
                        break;


                }
                return false;
            }
        });


        BottomNavigationView bv = findViewById(R.id.bottom_nav_main);
        bv.setSelectedItemId(R.id.navigation_book);

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
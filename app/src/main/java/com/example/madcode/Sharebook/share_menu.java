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
import com.example.madcode.nav_activity;
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

public class share_menu extends nav_activity{

    RecyclerView recyclerView;
    sharebook sharebook;
    FloatingActionButton fb;
    DatabaseReference database;
    com.example.madcode.Sharebook.shareadapter shareadapter;
    ArrayList<Modelshare> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_share_menu);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_share_menu,null,false);
        cl.addView(v,0);


        DrawerLayout dl = findViewById(R.id.drawer);
        NavigationView nav = findViewById(R.id.navwiew);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(share_menu.this, MainActivity.class));
                        break;

                    case R.id.profile:
                        startActivity(new Intent(share_menu.this, user_profile.class));
                        break;

                    case R.id.mybooks:
                        // startActivity(new Intent(Eventmain.this, user_profile.class));
                        break;

                    case R.id.My_Requests:
                        startActivity(new Intent(share_menu.this, RequestBook.class));
                        break;

                    case R.id.My_Articles:
                        startActivity(new Intent(share_menu.this, My_Article.class));
                        break;

                    case R.id.My_Events:
                        startActivity(new Intent(share_menu.this, my_event_list.class));
                        break;

                    case R.id.logout_2:
                        //startActivity(new Intent(Eventmain.this, RequestBook.class));
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


        recyclerView = findViewById(R.id.share_menu);
        database = FirebaseDatabase.getInstance().getReference("sharebook");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        shareadapter = new shareadapter(this,list);
        recyclerView.setAdapter(shareadapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Modelshare modelshare = dataSnapshot.getValue(Modelshare.class);
                    list.add(modelshare);

                }

                shareadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        fb = (FloatingActionButton)findViewById(R.id.addshare_book);
        fb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplication(), com.example.madcode.Sharebook.sharebook.class));
            }
        });
}
}
package com.example.madcode.Article;

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

import com.example.madcode.Article.Model.ArticleModel;
import com.example.madcode.Eventmain;
import com.example.madcode.Events.my_event_list;
import com.example.madcode.MainActivity;
import com.example.madcode.R;
import com.example.madcode.Request.CusRequestBook;
import com.example.madcode.Request.RequestBook;
import com.example.madcode.Sharebook.Share_menu;
import com.example.madcode.User.user_profile;
import com.example.madcode.login;
import com.example.madcode.nav_activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class ArticleMainPageCustomer extends nav_activity {

    RecyclerView rec;
    DatabaseReference database;
    Article_adapter_customer adapter;
    ArrayList<ArticleModel> list;
    ArrayList<String> getArticleId = new ArrayList<>();
    String ArticleId;
    private Article_adapter_customer.RecyclerViewClickListner listner;

    EditText searchTextCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_article_main_page_customer);

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_article_main_page_customer,null,false);
        cl.addView(v,0);

        DrawerLayout dl = findViewById(R.id.drawer);
        NavigationView nav = findViewById(R.id.navwiew);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(ArticleMainPageCustomer.this,MainActivity.class));
                        break;

                    case R.id.profile:
                        startActivity(new Intent(ArticleMainPageCustomer.this, user_profile.class));
                        break;

                    case R.id.mybooks:
                        // startActivity(new Intent(Eventmain.this, user_profile.class));
                        break;

                    case R.id.My_Requests:
                        startActivity(new Intent(ArticleMainPageCustomer.this, RequestBook.class));
                        break;

                    case R.id.My_Articles:
                        startActivity(new Intent(ArticleMainPageCustomer.this, My_Article.class));
                        break;

                    case R.id.My_Events:
                        startActivity(new Intent(ArticleMainPageCustomer.this, my_event_list.class));
                        break;

                    case R.id.logout_2:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(ArticleMainPageCustomer.this, login.class));
                        break;


                }
                return false;
            }
        });



        BottomNavigationView bv = findViewById(R.id.bottom_nav_main);
        bv.setSelectedItemId(R.id.navigation_article);

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
                        startActivity(new Intent(getApplicationContext(), Share_menu.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.navigation_article:

                        return true;

                    case R.id.navigation_request:
                        startActivity(new Intent(getApplicationContext(), CusRequestBook.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        //Search text call in customer
        searchTextCustomer = findViewById(R.id.article_search_bar_customer);
        searchTextCustomer.addTextChangedListener(new TextWatcher() {
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



        //article click
        setOnClickListner();
        rec = findViewById(R.id.recyclerView2);
        //get reference form database
        database = FirebaseDatabase.getInstance().getReference("ArticleModel");
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new Article_adapter_customer(this,list,listner);
        rec.setAdapter(adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    ArticleId = dn.getKey();
                    ArticleModel ev = dn.getValue(ArticleModel.class);
                    list.add(ev);
                    getArticleId.add(ArticleId);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    //Create filter method in ArticleMainPageCustomer
    private void filter(String text){
        ArrayList<ArticleModel> filteredList = new ArrayList<>();
        for(ArticleModel item:list){
            if(item.getHead_line().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }


//when click the article pass the data to article view page
    private void setOnClickListner() {
        listner = new Article_adapter_customer.RecyclerViewClickListner() {
            @Override
            public void onClick(View v, int position) {
                Intent in = new Intent(getApplicationContext(), ArticleViewPageCustomer.class);

                //pass the current page id to display
                in.putExtra("AID",getArticleId.get(position));
                //pass the data to view page (display)
                in.putExtra("Head_line",list.get(position).getHead_line());
                in.putExtra("Small_description",list.get(position).getSmall_description());
                in.putExtra("Sub_topic",list.get(position).getSub_topic());
                in.putExtra("Description",list.get(position).getDescription());
                in.putExtra("propic",list.get(position).getPurl());

                startActivity(in);
            }
        };

    }

}
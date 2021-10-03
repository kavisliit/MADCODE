package com.example.madcode.Article;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.Article.Model.ArticleModel;
import com.example.madcode.Events.my_event_list;
import com.example.madcode.MainActivity;
import com.example.madcode.R;
import com.example.madcode.Request.RequestBook;
import com.example.madcode.Sharebook.Share_menu;
import com.example.madcode.User.user_profile;
import com.example.madcode.login;
import com.example.madcode.nav_activity;
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

public class My_Article extends nav_activity {

    //variables

    RecyclerView rec;
    DatabaseReference database;
    Article_adapter adapter;
    ArrayList<ArticleModel> list;
    ArrayList<String> getArticleId = new ArrayList<>();
    String ArticleId;
    private Article_adapter.RecyclerViewClickListner listner;

    FirebaseUser fireUser;
    String CurrentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_my_article);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_my_article,null,false);
        cl.addView(v,0);

        DrawerLayout dl = findViewById(R.id.drawer);
        NavigationView nav = findViewById(R.id.navwiew);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(My_Article.this, MainActivity.class));
                        break;

                    case R.id.profile:
                        startActivity(new Intent(My_Article.this, user_profile.class));
                        break;

                    case R.id.mybooks:
                        startActivity(new Intent(My_Article.this, Share_menu.class));
                        break;

                    case R.id.My_Requests:
                        startActivity(new Intent(My_Article.this, RequestBook.class));
                        break;

                    case R.id.My_Articles:
                        startActivity(new Intent(My_Article.this, My_Article.class));
                        break;

                    case R.id.My_Events:
                        startActivity(new Intent(My_Article.this, my_event_list.class));
                        break;

                    case R.id.logout_2:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(My_Article.this, login.class));
                        break;


                }
                return false;
            }
        });



        setOnClickListner();
        rec = findViewById(R.id.recyclerView1);
        //get reference form database
        database = FirebaseDatabase.getInstance().getReference("ArticleModel");
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new Article_adapter(this,list,listner);
        rec.setAdapter(adapter);
        //current uid
        CurrentUserId= FirebaseAuth.getInstance().getCurrentUser().getUid();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    ArticleId = dn.getKey();
                    ArticleModel ev = dn.getValue(ArticleModel.class);
                    if(ev.getCurrentUserId().equals(CurrentUserId)){
                        list.add(ev);
                        getArticleId.add(ArticleId);
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
        listner = new Article_adapter.RecyclerViewClickListner() {
            @Override
            public void onClick(View v, int position) {
                Intent in = new Intent(getApplicationContext(), ArticleViewPage.class);

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


   public void MyArticleToMyCreate(View view){
       Intent intent = new Intent(this,MyArticleCreate.class);
       startActivity(intent);
   }


}

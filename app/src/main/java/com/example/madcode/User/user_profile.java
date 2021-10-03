package com.example.madcode.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.madcode.Article.My_Article;
import com.example.madcode.Events.Add_Event;
import com.example.madcode.Events.Models.User;
import com.example.madcode.Events.my_event_list;
import com.example.madcode.MainActivity;
import com.example.madcode.R;
import com.example.madcode.Request.RequestBook;
import com.example.madcode.Sharebook.Share_menu;
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

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class user_profile extends nav_activity {
    Button logout,samp;
    TextView name,email,dob,phone,greet;
    FirebaseUser user;
    String userID;
    ImageView edit,delete;
    CircleImageView im;
    DatabaseReference dbref;
    String uname,dob1,pho,em,uri,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_user_profile);

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_user_profile,null,false);
        cl.addView(v,0);

        DrawerLayout dl = findViewById(R.id.drawer);
        NavigationView nav = findViewById(R.id.navwiew);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(user_profile.this, MainActivity.class));
                        break;

                    case R.id.profile:
                        startActivity(new Intent(user_profile.this, user_profile.class));
                        break;

                    case R.id.mybooks:
                        startActivity(new Intent(user_profile.this, Share_menu.class));
                        break;

                    case R.id.My_Requests:
                        startActivity(new Intent(user_profile.this, RequestBook.class));
                        break;

                    case R.id.My_Articles:
                        startActivity(new Intent(user_profile.this, My_Article.class));
                        break;

                    case R.id.My_Events:
                        startActivity(new Intent(user_profile.this, my_event_list.class));
                        break;

                    case R.id.logout_2:
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(user_profile.this, login.class));
                        break;


                }
                return false;
            }
        });




        user = FirebaseAuth.getInstance().getCurrentUser();
        dbref = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        im = findViewById(R.id.circleImageView_usre);
        greet = findViewById(R.id.greeting);

        name = findViewById(R.id.u_name);
        email = findViewById(R.id.u_email);
        dob = findViewById(R.id.u_name2);
        phone = findViewById(R.id.u_phone);



        dbref.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                User userprofile = snapshot.getValue(User.class);
                if(userprofile != null){
                     uname = userprofile.uname;
                     dob1 = userprofile.dob;
                     pho = userprofile.phone;
                     pass = userprofile.password;
                     em = userprofile.email;
                    uri = userprofile.uri;
                    greet.setText(uname);
                    name.setText(uname);
                    email.setText(em);

                    dob.setText(dob1);
                    phone.setText(pho);


                    Glide.with(getApplicationContext()).load(uri).into(im);

                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(user_profile.this, "something wrong happen", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void newone(View view){
        startActivity(new Intent(user_profile.this, Add_Event.class));
    }


}
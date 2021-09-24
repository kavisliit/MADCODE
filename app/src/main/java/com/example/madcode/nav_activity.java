package com.example.madcode;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class nav_activity extends AppCompatActivity {

    public DrawerLayout cl;
    Toolbar tool;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        cl = findViewById(R.id.drawer);
        tool =  findViewById(R.id.toolbar);
        nav = findViewById(R.id.navwiew);
        setSupportActionBar(tool);
        toggle = new ActionBarDrawerToggle(this,cl,tool,0,0);
        cl.addDrawerListener(toggle);
        toggle.syncState();
    }
}
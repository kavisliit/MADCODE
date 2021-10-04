package com.example.madcode.Sharebook;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.madcode.R;



public class Sharebook_viewpage_cus extends AppCompatActivity {



    String ShareBookId ;
    String sbname = "not set";
    String sbname2 = "not set";
    String sbname3 = "not set";
    String sbname4 = "not set";
    String sbname5 = "not set";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharebook_viewpage_cus);




        TextView sbookname = findViewById(R.id.sbooktext1);
        TextView sbookcategory = findViewById(R.id.sbooktext2);
        TextView sbookauthor = findViewById(R.id.sbooktext3);
        TextView sbookdes = findViewById(R.id.sbooktext4);
        ImageView surl = findViewById(R.id.sbookimg);




        Bundle extras = getIntent().getExtras();
        if(extras != null){
            ShareBookId = extras.getString("ShareBookId");
            sbname = extras.getString("sbookname");
            sbname2 = extras.getString("sbookcategory");
            sbname3 = extras.getString("sbookauthor");
            sbname4 = extras.getString("sbookdes");
            sbname5 = extras.getString("surl");
        }

        sbookname.setText(sbname);
        sbookcategory.setText(sbname2);
        sbookauthor.setText(sbname3);
        sbookdes.setText(sbname4);
        Glide.with(this).load(sbname5).into(surl);
    }



    public void SharebooktoSharemenucus(View view){
        Intent intent = new Intent(this, Share_menu_Cus.class);
        startActivity(intent);
    }
}
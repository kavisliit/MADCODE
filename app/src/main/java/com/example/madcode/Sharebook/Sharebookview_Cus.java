package com.example.madcode.Sharebook;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madcode.R;


public class Sharebookview_Cus extends AppCompatActivity {

    String SID ;
    String txt = "not set";
    String txt1 = "not set";
    String txt2 = "not set";
    String txt3 = "not set";
    String txt4 = "not set";


    public void ViewToCusShare(View view){
        Intent intent = new Intent(this, Sharebook_Cus.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharebookview_cus);



        TextView sbookname = findViewById(R.id.sharehead);
        TextView sbookauthor = findViewById(R.id.shareauthor);
        TextView sbookcategory = findViewById(R.id.sharecategory);
        TextView sbookdes = findViewById(R.id.shareviewmulti);
        ImageView surl = findViewById(R.id.shareimg);



//display request view page
        Bundle extras = getIntent().getExtras();
        if(extras != null){

            SID = extras.getString("SID");
            txt = extras.getString("sbookname");
            txt1 = extras.getString("sbookauthor");
            txt2 = extras.getString("sbookcategory");
            txt3 = extras.getString("sbookdes");
            txt4 = extras.getString("surl");
        }

        sbookname.setText(txt);
        sbookauthor.setText(txt1);
        sbookcategory.setText(txt2);
        sbookdes.setText(txt3);
        Glide.with(this).load(txt4).into(surl);


    }


}
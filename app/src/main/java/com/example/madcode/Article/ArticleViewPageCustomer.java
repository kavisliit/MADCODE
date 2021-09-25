package com.example.madcode.Article;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.madcode.R;

public class ArticleViewPageCustomer extends AppCompatActivity {

    //ImageButton btnBack;
    String AID ;
    String name = "not set";
    String name2 = "not set";
    String name3 = "not set";
    String name4 = "not set";
    String name5 = "not set";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view_page_customer);

        TextView Head_line = findViewById(R.id.nametext1);
        TextView Small_description = findViewById(R.id.nametext2);
        TextView Sub_topic = findViewById(R.id.nametext3);
        TextView Description = findViewById(R.id.nametext4);
        ImageView propic = findViewById(R.id.image2);


        //display data view page
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            AID = extras.getString("AID");
            name = extras.getString("Head_line");
            name2 = extras.getString("Small_description");
            name3 = extras.getString("Sub_topic");
            name4 = extras.getString("Description");
            name5 = extras.getString("propic");
        }

        Head_line.setText(name);
        Small_description.setText(name2);
        Sub_topic.setText(name3);
        Description.setText(name4);
        Glide.with(this).load(name5).into(propic);
    }
    public void ArticleToMainArticlePage(View view){
        Intent intent = new Intent(this,ArticleMainPageCustomer.class);
        startActivity(intent);
    }
}
package com.example.madcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.madcode.Article.ArticleMainPageCustomer;
import com.example.madcode.Article.MyArticleCreate;
import com.example.madcode.Article.My_Article;
import com.example.madcode.User.user_profile;
import com.example.madcode.Request.RequestBook;


public class MainActivity extends nav_activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

    }


    public void MainToMyCreate(View view){
        Intent intent = new Intent(this, My_Article.class);
        startActivity(intent);
    }
    public void MainToArticles(View view){
        Intent intent = new Intent(this, ArticleMainPageCustomer.class);
        startActivity(intent);
    }
    public void MainToEventMain(View view){
        Intent intent = new Intent(this, user_profile.class);
        startActivity(intent);
    }

}
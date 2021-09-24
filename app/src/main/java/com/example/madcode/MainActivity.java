package com.example.madcode;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.madcode.Article.ArticleMainPageCustomer;
import com.example.madcode.Article.MyArticleCreate;
import com.example.madcode.Article.My_Article;

public class MainActivity extends nav_activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);

    }


    public void MainToCreate(View view){
        Intent intent = new Intent(this, MyArticleCreate.class);
        startActivity(intent);
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
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}
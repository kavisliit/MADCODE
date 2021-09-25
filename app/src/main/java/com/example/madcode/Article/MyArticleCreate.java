package com.example.madcode.Article;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madcode.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class MyArticleCreate extends AppCompatActivity {

    //create object for get text view
    EditText Head_line,Small_description,Sub_topic, Description,purl;
    Button submit,back;

    FirebaseUser fireUser;
    String CurrentUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_article_create);

        //connect to object to data base
        Head_line=(EditText)findViewById(R.id.add_Head_line);
        Small_description =(EditText)findViewById(R.id.add_Small_description);
        Sub_topic=(EditText)findViewById(R.id.add_Sub_topic);
        Description =(EditText)findViewById(R.id.add_Description);
        purl=(EditText)findViewById(R.id.add_purl);
        //current userID
        CurrentUserId= FirebaseAuth.getInstance().getCurrentUser().getUid();

        //back button
        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),My_Article.class));
                finish();
            }
        });

        //complete button
        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
    }

//insert data base
    private void processinsert()
    {

        Map<String,Object> map=new HashMap<>();
        map.put("Head_line",Head_line.getText().toString());
        map.put("Small_description",Small_description.getText().toString());
        map.put("Sub_topic",Sub_topic.getText().toString());
        map.put("Description",Description.getText().toString());
        map.put("purl",purl.getText().toString());
        map.put("CurrentUserId",CurrentUserId);
        //connect to database and create database name student
        FirebaseDatabase.getInstance().getReference().child("ArticleModel").push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {

                    //after the insert data base to data auto clear the text field
                    @Override
                    public void onSuccess(Void aVoid) {
                        Head_line.setText("");
                        Small_description.setText("");
                        Sub_topic.setText("");
                        Description.setText("");
                        purl.setText("");
                        //insert successful message
                        Toasty.success(getApplicationContext(), "Success!", Toast.LENGTH_SHORT, true).show();
                        Intent in = new Intent(MyArticleCreate.this,My_Article.class);
                        //Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                        startActivity(in);
                    }
                })
                //if cannot insert data base error message
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });

    }
    public void CreateToMyArticlePage(View view){
        Intent intent = new Intent(MyArticleCreate.this,My_Article.class);
        startActivity(intent);
    }
}
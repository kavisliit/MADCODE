package com.example.madcode.Article;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madcode.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Article_update_page extends AppCompatActivity {

    //create object for get text view
    EditText Head_line,Small_description,Sub_topic, Description,purl;
    Button submit,back;
    ImageButton deleteBtn;
    String AID ;
    String name = "not set";
    String name2 = "not set";
    String name3 = "not set";
    String name4 = "not set";
    String name5 = "not set";
    Task<Void> db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_update_page);

        //connect to object to data base
        Head_line=(EditText)findViewById(R.id.update_Head_line);
        Small_description =(EditText)findViewById(R.id.update_Small_description);
        Sub_topic=(EditText)findViewById(R.id.update_Sub_topic);
        Description =(EditText)findViewById(R.id.update_Description);
        purl=(EditText)findViewById(R.id.update_purl);
        deleteBtn = findViewById(R.id.update_deletebutton);


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
        purl.setText(name5);


        //back button
        back=(Button)findViewById(R.id.update_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),My_Article.class));
                finish();
            }
        });


        //Delete button
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(purl.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference("ArticleModel").child(AID).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(Article_update_page.this, "deleted succesfully", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(Article_update_page.this,My_Article.class);
                                startActivity(in);
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }
        });



        //complete button
        submit=(Button)findViewById(R.id.update_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name  = Head_line.getText().toString();
                name2  = Small_description.getText().toString();
                name3  = Sub_topic.getText().toString();
                name4  = Description.getText().toString();
                name5  = purl.getText().toString();

                        Map<String,Object> map = new HashMap<>();
                        map.put("Head_line",name);
                        map.put("Small_description",name2);
                        map.put("Sub_topic",name3);
                        map.put("Description",name4);
                        map.put("purl",name5);


                        db = FirebaseDatabase.getInstance().getReference("ArticleModel").child(AID)
                                .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(Article_update_page.this, "success", Toast.LENGTH_SHORT).show();
                                        Intent in = new Intent(Article_update_page.this,My_Article.class);
                                        startActivity(in);
                                    }
                                });

            }
        });

    }
    public void EditToMyArticlePage(View view){
        Intent intent = new Intent(Article_update_page.this,My_Article.class);
        startActivity(intent);
    }
}








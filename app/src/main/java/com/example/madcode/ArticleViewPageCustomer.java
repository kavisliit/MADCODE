package com.example.madcode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

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

        //btnBack = (ImageButton) findViewById(R.id.btnBack);


        TextView Head_line = findViewById(R.id.nametext1);
        TextView Small_description = findViewById(R.id.nametext2);
        TextView Sub_topic = findViewById(R.id.nametext3);
        TextView Description = findViewById(R.id.nametext4);
        ImageView propic = findViewById(R.id.image2);

//        //pass the value to edit page
//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ArticleViewPage.this,Article_update_page.class);
//
//                intent.putExtra("AID",AID);
//                //pass the data to view page (display)
//                intent.putExtra("Head_line",name);
//                intent.putExtra("Small_description",name2);
//                intent.putExtra("Sub_topic",name3);
//                intent.putExtra("Description",name4);
//                intent.putExtra("propic",name5);
//
//                startActivity(intent);
//            }
//        });

        //Delete button
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseDatabase.getInstance().getReference("ArticleModel").child(AID).removeValue(new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(@Nullable @org.jetbrains.annotations.Nullable DatabaseError error, @NonNull @NotNull DatabaseReference ref) {
//                        Toast.makeText(ArticleViewPage.this, "deleted succesfully", Toast.LENGTH_SHORT).show();
//                        Intent in = new Intent(ArticleViewPage.this,My_Article.class);
//                        startActivity(in);
//                    }
//                });
//            }
//        });

//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AlertDialog.Builder builder=new AlertDialog.Builder(propic.getContext());
//                builder.setTitle("Delete Panel");
//                builder.setMessage("Delete...?");
//
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        FirebaseDatabase.getInstance().getReference("ArticleModel").child(AID).removeValue(new DatabaseReference.CompletionListener() {
//                            @Override
//                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                                Toast.makeText(ArticleViewPage.this, "deleted succesfully", Toast.LENGTH_SHORT).show();
//                                Intent in = new Intent(ArticleViewPage.this,My_Article.class);
//                                startActivity(in);
//                            }
//                        });
//                    }
//                });
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                    }
//                });
//                builder.show();
//            }
//        });



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
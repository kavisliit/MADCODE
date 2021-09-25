package com.example.madcode.Sharebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.madcode.R;

public class Sharebook_viewpage extends AppCompatActivity {


    //ImageButton btnBack;
    String SID ;
    String sname = "not set";
    String sname2 = "not set";
    String sname3 = "not set";
    String sname4 = "not set";
    String sname5 = "not set";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharebook_viewpage);
        //btnBack = (ImageButton) findViewById(R.id.btnBack);


        TextView sbookname = findViewById(R.id.sbooktext1);
        TextView sbookcategory = findViewById(R.id.sbooktext2);
        TextView sbookauthor = findViewById(R.id.sbooktext3);
        TextView sbookdescription = findViewById(R.id.sbooktext4);
        ImageView surl = findViewById(R.id.sbookimg);

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
            SID = extras.getString("SID");
            sname = extras.getString("sbookname");
            sname2 = extras.getString("sbookcategory");
            sname3 = extras.getString("sbookauthor");
            sname4 = extras.getString("sbookdescription");
            sname5 = extras.getString("surl");
        }

        sbookname.setText(sname);
        sbookcategory.setText(sname2);
        sbookauthor.setText(sname3);
        sbookdescription.setText(sname4);
        Glide.with(this).load(sname5).into(surl);
    }



        public void SharebooktoSharemenu(View view){
            Intent intent = new Intent(this, share_menu.class);
            startActivity(intent);
        }
    }
package com.example.madcode.Sharebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.madcode.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sharebook_viewpage extends AppCompatActivity {


    //ImageButton btnBack;
    ImageButton buttonEdit,buttonDelete;
    String ShareBookId ;
    String sbname = "not set";
    String sbname2 = "not set";
    String sbname3 = "not set";
    String sbname4 = "not set";
    String sbname5 = "not set";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharebook_viewpage);
        //btnBack = (ImageButton) findViewById(R.id.btnBack);


        buttonEdit = (ImageButton) findViewById(R.id.editshare);
        buttonDelete = (ImageButton) findViewById(R.id.deleteshare);



        TextView sbookname = findViewById(R.id.sbooktext1);
        TextView sbookcategory = findViewById(R.id.sbooktext2);
        TextView sbookauthor = findViewById(R.id.sbooktext3);
        TextView sbookdes = findViewById(R.id.sbooktext4);
        ImageView surl = findViewById(R.id.sbookimg);

        //pass the value to edit page
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sharebook_viewpage.this, Sharebook_updatepage.class);

                intent.putExtra("ShareBookId",ShareBookId);
                //pass the data to view page (display)
                intent.putExtra("sbookname",sbname);
                intent.putExtra("sbookcategory",sbname2);
                intent.putExtra("sbookauthor",sbname3);
                intent.putExtra("sbookdes",sbname4);
                intent.putExtra("surl",sbname5);

                startActivity(intent);
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(surl.getContext());
                builder.setTitle("Delete Book Request");
                builder.setMessage("Confirm Delete?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference("sharebook").child(ShareBookId).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(Sharebook_viewpage.this, "deleted succesfully", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(Sharebook_viewpage.this, Share_menu.class);
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



//display request view page
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



        public void SharebooktoSharemenu(View view){
            Intent intent = new Intent(this, Share_menu.class);
            startActivity(intent);
        }
    }
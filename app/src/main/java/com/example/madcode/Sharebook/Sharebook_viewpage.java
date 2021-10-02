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
import com.example.madcode.Request.ReqEdit;
import com.example.madcode.Request.ReqView;
import com.example.madcode.Request.RequestBook;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sharebook_viewpage extends AppCompatActivity {


    //ImageButton btnBack;
    ImageButton buttonEdit,buttonDelete;
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


        buttonEdit = (ImageButton) findViewById(R.id.editicon);
        buttonDelete = (ImageButton) findViewById(R.id.deleteicon);



        TextView sbookname = findViewById(R.id.sbooktext1);
        TextView sbookcategory = findViewById(R.id.sbooktext2);
        TextView sbookauthor = findViewById(R.id.sbooktext3);
        TextView sbookdescription = findViewById(R.id.sbooktext4);
        ImageView surl = findViewById(R.id.sbookimg);

        //pass the value to edit page
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sharebook_viewpage.this, Sharebook_updatepage.class);

                intent.putExtra("SID",SID);
                //pass the data to view page (display)
                intent.putExtra("sbookname",sname);
                intent.putExtra("sbookauthor",sname3);
                intent.putExtra("sbookcategory",sname2);
                intent.putExtra("sbookdes",sname4);
                intent.putExtra("surl",sname5);

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
                        FirebaseDatabase.getInstance().getReference("Modelshare").child(SID).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(Sharebook_viewpage.this, "deleted succesfully", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(Sharebook_viewpage.this, sharebook.class);
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
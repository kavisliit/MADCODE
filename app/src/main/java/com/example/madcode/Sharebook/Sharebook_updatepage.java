package com.example.madcode.Sharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madcode.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class Sharebook_updatepage extends AppCompatActivity {




    EditText sbookname,sbookauthor,sbookcategory, sbookdes, surl;
    Button submit;

   // ImageButton buttonEdit,buttonDelete;
    String ShareBookId ;
    String sbname = "not set";
    String sbname2 = "not set";
    String sbname3 = "not set";
    String sbname4 = "not set";
    String sbname5 = "not set";
    Task<Void> db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharebook_updatepage);

        //connect to object to data base
        sbookname=(EditText)findViewById(R.id.editsbookname);
        sbookauthor =(EditText)findViewById(R.id.editsbookauthor);
        sbookcategory=(EditText)findViewById(R.id.editsbookcategory);
        sbookdes =(EditText)findViewById(R.id.editsbookdes);
        surl=(EditText)findViewById(R.id.editsurl);



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
        surl.setText(sbname5);


        //complete button
        submit=(Button)findViewById(R.id.updatesbook);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sbname  = sbookname.getText().toString();
                sbname2  = sbookcategory.getText().toString();
                sbname3  = sbookauthor.getText().toString();
                sbname4  = sbookdes.getText().toString();
                sbname5  = surl.getText().toString();

                if(TextUtils.isEmpty(sbookname.getText().toString()))
                {
                    Toasty.success(getApplicationContext(), "Book Name Required!", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                if(TextUtils.isEmpty(sbookauthor.getText().toString()))
                {
                    Toasty.success(getApplicationContext(), "Book Authur Required!", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                if(TextUtils.isEmpty(sbookcategory.getText().toString()))
                {
                    Toasty.success(getApplicationContext(), "Book Publisher Required!", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                if(TextUtils.isEmpty(sbookdes.getText().toString()))
                {
                    Toasty.success(getApplicationContext(), "Book Description Required!", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                if(TextUtils.isEmpty(surl.getText().toString()))
                {
                    Toasty.success(getApplicationContext(), "Image Url Required!", Toast.LENGTH_SHORT, true).show();
                    return;
                }

                Map<String,Object> map = new HashMap<>();
                map.put("sbookname",sbname);
                map.put("sbookcategory",sbname2);
                map.put("sbookauthor",sbname3);
                map.put("sbookdes",sbname4);
                map.put("surl",sbname5);


                db = FirebaseDatabase.getInstance().getReference("sharebook").child(ShareBookId)
                        .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Sharebook_updatepage.this, "success", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(Sharebook_updatepage.this, Share_menu.class);
                                startActivity(in);
                            }
                        });

            }
        });


    }
}
package com.example.madcode.Sharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

    ImageButton buttonEdit,buttonDelete;
    String SID ;
    String txt = "not set";
    String txt1 = "not set";
    String txt2 = "not set";
    String txt3 = "not set";
    String txt4 = "not set";
    Task<Void> db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharebook_updatepage);

        //connect to object to data base
        sbookname=(EditText)findViewById(R.id.EditBookName);
        sbookauthor =(EditText)findViewById(R.id.EditAuthursName);
        sbookcategory=(EditText)findViewById(R.id.EditPublisherName);
        sbookdes =(EditText)findViewById(R.id.EditBookDescription);
        surl=(EditText)findViewById(R.id.Editreqimg);
//        deleteBtn = findViewById(R.id.update_deletebutton);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            SID = extras.getString("SID");
            txt = extras.getString("sbookname");
            txt1 = extras.getString("sbookauthor");
            txt2 = extras.getString("sbookcategory");
            txt3 = extras.getString("sbookdes");
            txt4 = extras.getString("surl");
        }

        sbookname.setText(txt);
        sbookauthor.setText(txt1);
        sbookcategory.setText(txt2);
        sbookdes.setText(txt3);
        surl.setText(txt4);


        //complete button
        submit=(Button)findViewById(R.id.EditSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt  = sbookname.getText().toString();
                txt1  = sbookauthor.getText().toString();
                txt2  = sbookcategory.getText().toString();
                txt3  = sbookdes.getText().toString();
                txt4  = surl.getText().toString();

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
                map.put("sbookname",txt);
                map.put("sbookauthor",txt1);
                map.put("sbookcategory",txt2);
                map.put("sbookdes",txt3);
                map.put("surl",txt4);


                db = FirebaseDatabase.getInstance().getReference("Modelshare").child(SID)
                        .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Sharebook_updatepage.this, "success", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(Sharebook_updatepage.this,Sharebook_viewpage.class);
                                startActivity(in);
                            }
                        });

            }
        });


    }
}
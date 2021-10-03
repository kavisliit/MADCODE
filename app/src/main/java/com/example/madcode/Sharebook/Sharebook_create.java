package com.example.madcode.Sharebook;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madcode.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class Sharebook_create extends AppCompatActivity {

    EditText surl,sbookname,sbookcategory,sbookauthor,sbookdescription;
    ImageButton submit,back;

    String ShareBookUserId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharebook_create);


        surl = (EditText) findViewById(R.id.surl);
        sbookname = (EditText) findViewById(R.id.sbook_name);
        sbookcategory = (EditText) findViewById(R.id.sbook_category);
        sbookauthor = (EditText) findViewById(R.id.sbook_author);
        sbookdescription = (EditText) findViewById(R.id.sbook_description);

        ShareBookUserId= FirebaseAuth.getInstance().getCurrentUser().getUid();

        back = (ImageButton) findViewById(R.id.btn_sback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), Share_menu.class));
                finish();
            }
        });

        submit = (ImageButton) findViewById(R.id.btn_sget);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });
    }

    private void processinsert() {

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
        if(TextUtils.isEmpty(sbookdescription.getText().toString()))
        {
            Toasty.success(getApplicationContext(), "Book Description Required!", Toast.LENGTH_SHORT, true).show();
            return;
        }
        if(TextUtils.isEmpty(surl.getText().toString()))
        {
            Toasty.success(getApplicationContext(), "Image Url Required!", Toast.LENGTH_SHORT, true).show();
            return;
        }



        Map<String,Object>map=new HashMap<>();
        map.put("surl",surl.getText().toString());
        map.put( "sbookname",sbookname.getText().toString());
        map.put( "sbookcategory",sbookcategory.getText().toString());
        map.put( "sbookauthor",sbookauthor.getText().toString());
        map.put( "sbookdes",sbookdescription.getText().toString());
        map.put( "ShareBookUserId",ShareBookUserId);
        FirebaseDatabase.getInstance().getReference().child("sharebook").push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        surl.setText("");
                        sbookname.setText("");
                        sbookcategory.setText("");
                        sbookauthor.setText("");
                        sbookdescription.setText("");
                        Toast.makeText(getApplication(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e){
                        Toast.makeText(getApplication(),"Could Not Inserted",Toast.LENGTH_LONG).show();
                    }
                });
    }
}
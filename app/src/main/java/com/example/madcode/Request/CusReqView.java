package com.example.madcode.Request;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.madcode.Events.Models.User;
import com.example.madcode.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CusReqView extends AppCompatActivity {

    String RID ;
    String txt = "not set";
    String txt1 = "not set";
    String txt2 = "not set";
    String txt3 = "not set";
    String txt4 = "not set";
    String reqid= "";
    String reqphone="";

    TextView reqcontact;

    DatabaseReference db;


    public void ViewToCustList(View view){
        Intent intent = new Intent(this, CusRequestBook.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cus_req_view);



        TextView book_name = findViewById(R.id.reqheadingview);
        TextView book_authur = findViewById(R.id.reqauthurview);
        TextView book_publisher = findViewById(R.id.reqpublishreview);
        TextView book_description = findViewById(R.id.infoviewmulti);
        ImageView ReqUrl = findViewById(R.id.reqviewimg);
        reqcontact = findViewById(R.id.reqcontactview);



//display request view page
        Bundle extras = getIntent().getExtras();
        if(extras != null){

            RID = extras.getString("RID");
            txt = extras.getString("book_name");
            txt1 = extras.getString("book_authur");
            txt2 = extras.getString("book_publisher");
            txt3 = extras.getString("book_description");
            txt4 = extras.getString("ReqUrl");
            reqid = extras.getString("reqid");

        }

        book_name.setText(txt);
        book_authur.setText(txt1);
        book_publisher.setText(txt2);
        book_description.setText(txt3);
        Glide.with(this).load(txt4).into(ReqUrl);
        db = FirebaseDatabase.getInstance().getReference("Users");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn:snapshot.getChildren()){
                String id=dn.getKey();
                User ob= dn.getValue(User.class);

                if(reqid.equals(id)){
                    reqphone=ob.phone;
                    reqcontact.setText("Contact : " + reqphone);
                }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}
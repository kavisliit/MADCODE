package com.example.madcode.Article;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.madcode.Events.Models.User;
import com.example.madcode.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ArticleViewPageCustomer extends AppCompatActivity {

    //ImageButton btnBack;
    String AID ;
    String name = "not set";
    String name2 = "not set";
    String name3 = "not set";
    String name4 = "not set";
    String name5 = "not set";

    ImageButton callbutton,emalbutton;
    String CreatorsUserId, phoneNumber, Email;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_view_page_customer);

        TextView Head_line = findViewById(R.id.nametext1);
        TextView Small_description = findViewById(R.id.nametext2);
        TextView Sub_topic = findViewById(R.id.nametext3);
        TextView Description = findViewById(R.id.nametext4);
        ImageView propic = findViewById(R.id.image2);

        callbutton = findViewById(R.id.callbutton);
        emalbutton = findViewById(R.id.emailbutton);

        //call button(When click the call button go to call)
        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phoneNumber));
                startActivity(intent);

            }
        });

        //Email button(When click the email button go to email )
        emalbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+Email));
                startActivity(intent);

            }
        });


        //display data view page
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            AID = extras.getString("AID");
            name = extras.getString("Head_line");
            name2 = extras.getString("Small_description");
            name3 = extras.getString("Sub_topic");
            name4 = extras.getString("Description");
            name5 = extras.getString("propic");
            CreatorsUserId = extras.getString("CurrentUserId");
        }

        Head_line.setText(name);
        Small_description.setText(name2);
        Sub_topic.setText(name3);
        Description.setText(name4);
        Glide.with(this).load(name5).into(propic);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn:snapshot.getChildren()){
                    String id=dn.getKey();
                    User ob= dn.getValue(User.class);

                    if(CreatorsUserId.equals(id)){
                        phoneNumber=ob.phone;
                        Email = ob.email;
                        //reqcontact.setText("Contact : " + reqphone);


                    }

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void ArticleToMainArticlePage(View view){
        Intent intent = new Intent(this,ArticleMainPageCustomer.class);
        startActivity(intent);
    }
}
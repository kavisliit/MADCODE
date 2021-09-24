package com.example.madcode.Events;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.madcode.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class event_member_list extends AppCompatActivity {

    DatabaseReference database;
    String creatorname;
    int size;
    String ha;
    TextView ez;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_member_list);


        Bundle extras = getIntent().getExtras();
        String eid = extras.getString("eid");
        ez = findViewById(R.id.ez);
        ArrayList<String> members = new ArrayList<>();
       ez.setText(eid);
       String name = FirebaseAuth.getInstance().getCurrentUser().getUid();


        database = FirebaseDatabase.getInstance().getReference("Users").child(name);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if(data.getKey().equals("uname")){
                        String cname = data.getValue().toString();
                        creatorname = cname;
                        ez.setText(creatorname);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



    }
}
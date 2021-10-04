package com.example.madcode.Events;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.madcode.Events.Models.Event_group;
import com.example.madcode.Events.Models.User;
import com.example.madcode.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class event_profile extends AppCompatActivity {
    DatabaseReference database,db;
    TextView tw1,tw2,tw3,tw4,tw5,tw6;
    ImageView phone1,email1;
    User ev;
    ImageView mem,img,com;
    CircleImageView ci;
    String creatorname,eventid,nowname,nowuri;
    Button join;
    FirebaseUser user;
    Map<String,String > mapo = new HashMap<>();
    int in = 0,count = 0;
    String newid;
    String eventname = null;
    String email,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_profile);
        String date = null,time = null ,type = null,max = null,uri = null,cid = null;
        String eid;
        mem = findViewById(R.id.evenet_pic);
        tw1 = findViewById(R.id.info1);
        tw2 = findViewById(R.id.info2);
        tw3 = findViewById(R.id.info3);
        tw4 = findViewById(R.id.info4);
        tw5 = findViewById(R.id.info5);
        tw6 = findViewById(R.id.info6);
        ci = findViewById(R.id.creator_dp);
        join = findViewById(R.id.join_btn_ev);
        img = findViewById(R.id.members_btn_event);
        com = findViewById(R.id.event_profile_comment_btn);
        phone1 = findViewById(R.id.call_owner);
        email1 = findViewById(R.id.mail_owner);

        phone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phone));
                startActivity(intent);
            }
        });

        email1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"+email));
                startActivity(intent);
            }
        });

        String curid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        db = FirebaseDatabase.getInstance().getReference("Users");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    String key1 = dn.getKey();
                    User us = dn.getValue(User.class);
                    if(key1.equals(curid)){
                        nowname = us.uname;
                        nowuri = us.uri;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            eventname = extras.getString("name");

            date =extras.getString("date");
            time =extras.getString("time");
            type =extras.getString("type");
            max =extras.getString("max");
            uri =extras.getString("uri");
            cid =extras.getString("cid");
            eventid =extras.getString("eid");
            newid = eventid+"g";

           tw1.setText(eventname);
            tw2.setText(type);
            tw3.setText(date);
            tw4.setText(time);
            tw5.setText(max);
            Glide.with(getApplicationContext()).load(uri).into(mem);
            Glide.with(getApplicationContext()).load(nowuri).into(ci);

        }



        com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(event_profile.this,event_comments.class);
                in.putExtra("eid",eventid);
                in.putExtra("curid",curid);
                in.putExtra("oname",creatorname);


                startActivity(in);
            }
        });


        String neweid = eventid+"g";
        database = FirebaseDatabase.getInstance().getReference("Event_Group").child(neweid);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count = (int) snapshot.getChildrenCount();
                tw6.setText(String.valueOf(count));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        database = FirebaseDatabase.getInstance().getReference("Event_Group").child(newid);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    String k = data.getKey();
                    Event_group ev = data.getValue(Event_group.class);
                    String mid = ev.member;
                    if (mid.equals(curid)) {
                        join.setBackgroundColor(Color.RED);
                        join.setText("Leave");



                     }
                    }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        final String cid2 = cid;
       tw1 = findViewById(R.id.creat_by);

        database = FirebaseDatabase.getInstance().getReference("Users").child(cid);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if(data.getKey().equals("uname")){
                        String cname = data.getValue().toString();
                        creatorname = cname;
                        tw1.setText(creatorname);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        database = FirebaseDatabase.getInstance().getReference("Users");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn:snapshot.getChildren()){
                    String id = dn.getKey();
                    User ob = dn.getValue(User.class);
                    if(cid2.equals(id)){
                        phone = ob.phone;
                        email = ob.email;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        int nmax = Integer.parseInt(max);
        String finalType = type;
        String finalTime = time;
        join.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

                   String newn = eventid + "g";


               if (join.getText().equals("Leave")) {
                   database = FirebaseDatabase.getInstance().getReference("Event_Group").child(newn);
                   database.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           for (DataSnapshot data : snapshot.getChildren()) {
                               String k = data.getKey();
                               Event_group ev = data.getValue(Event_group.class);
                               String mid = ev.member;
                               if (mid.equals(curid)) {
                                   if (in == 0) {
                                       database.child(k).removeValue(new DatabaseReference.CompletionListener() {
                                           @Override
                                           public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                               Toasty.success(event_profile.this, "Leave successfully", Toast.LENGTH_SHORT).show();
                                               join.setBackgroundColor(event_profile.this.getResources()
                                                       .getColor(R.color.main_color));
                                               join.setText("Join");
                                               in = 1;
                                           }

                                       });
                                   }
                               }

                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {

                       }
                   });
               } else {
                   if (count < nmax){

                   database = FirebaseDatabase.getInstance().getReference("Event_Group").child(newn);
                   database.addValueEventListener(new ValueEventListener() {
                       @Override
                       public void onDataChange(DataSnapshot dataSnapshot) {
                           for (DataSnapshot data : dataSnapshot.getChildren()) {
                               Event_group ev = data.getValue(Event_group.class);
                               String mid = ev.member;
                               if (mid.equals(curid)) {
                                   if (in == 0) {
                                       in = 1;
                                       Toasty.info(event_profile.this, "already Joined", Toast.LENGTH_SHORT).show();
                                   }

                               }

                           }
                           if (in == 0) {
                               String key = database.push().getKey();
                               Map<String, Object> ob = new HashMap<>();
                               ob.put("member", curid);
                               ob.put("gkey", key);
                               ob.put("eid", eventid);
                               ob.put("cid", cid2);
                               database.push().setValue(ob);
                               in = 1;
                               join.setBackgroundColor(Color.RED);
                               join.setText("Leave");
                               Toasty.success(event_profile.this, "Joined successfully", Toast.LENGTH_SHORT).show();
                               Intent intent =  new Intent(Intent.ACTION_INSERT);
                               intent.setData(CalendarContract.Events.CONTENT_URI);
                               intent.putExtra(CalendarContract.Events.TITLE,eventname);
                               intent.putExtra(CalendarContract.Events.EVENT_LOCATION, finalType);
                               intent.putExtra(CalendarContract.Events.EVENT_TIMEZONE, finalTime);

                               if(intent.resolveActivity(getPackageManager()) != null){
                                   startActivity(intent);
                               }
                               else{
                                   Toast.makeText(event_profile.this, "there is no app that can support this action", Toast.LENGTH_SHORT).show();
                               }
                           }

                       }

                       @Override
                       public void onCancelled(DatabaseError databaseError) {
                       }
                   });
//

               }
                   else{
                       Toasty.error(event_profile.this, "Sorry event is full", Toast.LENGTH_SHORT).show();
                       return;
                   }
               }


           }
       });


        String finalUri = uri;
        String finalEventname = eventname;
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ur = finalUri;
                Intent in = new Intent(event_profile.this,event_member_list.class);
                in.putExtra("eid",eventid);
                in.putExtra("cname",cid2);
                in.putExtra("uri", ur);
                in.putExtra("ename", finalEventname);
                startActivity(in);
            }
        });

    }


}
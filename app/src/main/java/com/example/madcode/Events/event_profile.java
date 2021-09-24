package com.example.madcode.Events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

import es.dmoral.toasty.Toasty;

public class event_profile extends AppCompatActivity {
    DatabaseReference database;
    TextView tw1,tw2,tw3,tw4,tw5;
    User ev;
    ImageView mem;
    String creatorname,eventid;
    Button join;
    FirebaseUser user;
    Map<String,String > mapo = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_profile);
        String date,time,type,max,uri,cid = null;
        String eid;
        mem = findViewById(R.id.members_btn_event);
        tw1 = findViewById(R.id.info1);
        tw2 = findViewById(R.id.info2);
        tw3 = findViewById(R.id.info3);
        tw4 = findViewById(R.id.info4);
        tw5 = findViewById(R.id.info5);
        join = findViewById(R.id.join_btn_ev);
        String eventname;
        String curid = FirebaseAuth.getInstance().getCurrentUser().getUid();
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

           tw1.setText(eventname);
            tw2.setText(type);
            tw3.setText(date);
            tw4.setText(time);
            tw5.setText(max);

        }
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
//        tabLayout  = findViewById(R.id.tab_lay);
//        vp = findViewById(R.id.vp1);
//
//        tabLayout.setupWithViewPager(vp);
//        fragment_adapter ob = new fragment_adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//       // Fragment f1 = new event_fragment1();
//      //  f1.setArguments(bundle);
//        ob.addFragment(new event_fragment1(),"info");
//        ob.addFragment(new event_fragment2(),"members");
//        ob.addFragment(new event_fragment3(),"comments");
//        vp.setAdapter(ob);
       join.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String newn = cid2 +"g";



               database = FirebaseDatabase.getInstance().getReference("Event_Group").child(newn);
               database.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       for (DataSnapshot data : dataSnapshot.getChildren()) {
                           Event_group ev = data.getValue(Event_group.class);
                          String mid = ev.member;
                          if(mid.equals(curid)){
                              Toasty.info(event_profile.this, "already", Toast.LENGTH_SHORT).show();

                              return;
                          }

                           }
                       String key = database.push().getKey();
                      Map<String,Object> ob = new HashMap<>();
                      ob.put("member",curid);
                      ob.put("gkey",key);
                       ob.put("eid",eventid);
                       ob.put("cid",cid2);
                      database.push().setValue(ob);
                       Toasty.success(event_profile.this, "Joined successfully", Toast.LENGTH_SHORT).show();
                      return;

                       }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {
                   }
               });
//



           }
       });

        mem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(event_profile.this,event_member_list.class);
                in.putExtra("eid",eventid);
                startActivity(in);
            }
        });

    }


}
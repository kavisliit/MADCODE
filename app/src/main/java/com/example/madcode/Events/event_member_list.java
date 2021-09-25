package com.example.madcode.Events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.madcode.Eventmain;
import com.example.madcode.Events.Models.Event_group;
import com.example.madcode.Events.Models.User;
import com.example.madcode.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class event_member_list extends AppCompatActivity {

    DatabaseReference database,database2;
    String creatorname;
    member_adapter adapter;
    ArrayList<User> list;
    ImageView img,bbtn;
    RecyclerView rec;
    int size;
    String ha,newid;
    ArrayList<String> memidlist = new ArrayList<>();
    TextView ez,ename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_member_list);

       img = findViewById(R.id.member_list_img);
        Bundle extras = getIntent().getExtras();
        String eid = extras.getString("eid");
        String cname = extras.getString("cname");
        String uri = extras.getString("uri");
        String enamet = extras.getString("ename");
        newid = eid+"g";
        ename = findViewById(R.id.event_name_list);
        ename.setText(enamet);
        Glide.with(getApplicationContext()).load(uri).into(img);
        rec = findViewById(R.id.memebr_list_recycle_view);
        bbtn = findViewById(R.id.back_btn_profile);
        ArrayList<String> members = new ArrayList<>();

       String name = FirebaseAuth.getInstance().getCurrentUser().getUid();

        database = FirebaseDatabase.getInstance().getReference("Event_Group").child(newid);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new member_adapter(this,list);
        rec.setItemAnimator(new DefaultItemAnimator());
        rec.setAdapter(adapter);

        bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(event_member_list.this, Eventmain.class);
                startActivity(in);
            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){

                    Event_group ev = dn.getValue(Event_group.class);
                    memidlist.add(ev.member);
                    database2 = FirebaseDatabase.getInstance().getReference("Users");
                    database2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot1) {
                            for(DataSnapshot data : snapshot1.getChildren()) {
                                String key = data.getKey();
                                User ob = data.getValue(User.class);
                                 if(ev.member.equals(key)){
                                     list.add(ob);
                                 }
                            }
                            adapter.notifyDataSetChanged();


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });







    }
}
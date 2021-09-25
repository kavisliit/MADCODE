package com.example.madcode.Events;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.Events.Models.Comment;
import com.example.madcode.Events.Models.User;
import com.example.madcode.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class event_comments extends AppCompatActivity implements Comment_Dialog.Commentdialoglistner {
    FloatingActionButton acbtn;
    String commentget;
    DatabaseReference db;
    String eid,curid,oname;
    RecyclerView rec;
    ArrayList<Comment> list;
    Comment_adapter adapter;
    String nowname,nowuri;
    int ind = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_comments);
        acbtn = findViewById(R.id.action_btn_comment);

        Bundle ob = getIntent().getExtras();
         eid = ob.getString("eid");
         curid = ob.getString("curid");
         oname = ob.getString("oname");

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

        rec = findViewById(R.id.comment_recycle);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new Comment_adapter(this,list);
        rec.setItemAnimator(new DefaultItemAnimator());
        rec.setAdapter(adapter);
        String newi = eid +"c";
        db = FirebaseDatabase.getInstance().getReference("Event_Comment").child(newi);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    Comment ob = dn.getValue(Comment.class);
                    list.add(ob);

                }
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        acbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendialog();
            }
        });
    }

    private void opendialog() {
        Comment_Dialog ob = new Comment_Dialog();
        ob.show(getSupportFragmentManager(),"Example Dialog");

    }

    @Override
    public void applytext(String Comment) {
        String newid = eid +"c";
        commentget = Comment;
        db = FirebaseDatabase.getInstance().getReference("Event_Comment").child(newid);
        Map<String,Object> map = new HashMap<>();
        String key = db.push().getKey();
        map.put("comid",key);
        map.put("oid",curid);
        map.put("comment",commentget);
        map.put("oname",nowname);
        map.put("ouri",nowuri);
        db.push().setValue(map);
        ind = 1;


    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if(ind == 1) {
            super.onWindowFocusChanged(hasFocus);
            this.recreate();
        }
    }
}
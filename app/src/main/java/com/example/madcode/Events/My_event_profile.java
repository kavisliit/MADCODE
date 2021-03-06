package com.example.madcode.Events;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.madcode.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class My_event_profile extends AppCompatActivity {

    TextView tname,tdate,ttime,ttype,tmem,tvenue,tcur;
    String eid,cid,name,date,time,venue,mem,type,uri;
    ImageView edit,delete,img;
    DatabaseReference db;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_event_profile);

        tname = findViewById(R.id.my_event_name);
        tdate = findViewById(R.id.my_event_date);
        ttime = findViewById(R.id.my_event_time);
        tvenue = findViewById(R.id.my_event_venue);
        ttype = findViewById(R.id.my_event_type);
        tmem = findViewById(R.id.my_event_mem);
        edit = findViewById(R.id.my_event_edit_btn);
        delete = findViewById(R.id.my_event_delete_btn);
        img = findViewById(R.id.my_event_selected_pic);
        tcur= findViewById(R.id.my_event_mem_cur);


        Bundle extras = getIntent().getExtras();
        if(extras != null){

           name= extras.get("name").toString();
           date = extras.get("date").toString();
            time = extras.get("time").toString();
            type = extras.get("type").toString();
            venue = extras.get("venue").toString();
            mem = extras.get("max").toString();
            eid = extras.getString("eid");
            cid = extras.getString("cid");
            uri = extras.getString("uri");

            tname.setText(name);
            tdate.setText(date);
            ttime.setText(time);
            tvenue.setText(venue);
            ttype.setText(type);
            tmem.setText(mem);
          Glide.with(getApplicationContext()).load(extras.getString("uri")).into(img);





        }

        String newid = eid +"g";
        db= FirebaseDatabase.getInstance().getReference("Event_Group").child(newid);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count = (int) snapshot.getChildrenCount();
                tcur.setText(String.valueOf(count));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), My_event_edit.class);
                in.putExtra("eid",eid);
                in.putExtra("name",name);
                in.putExtra("date",date);
                in.putExtra("time",time);
                in.putExtra("type",type);
                in.putExtra("max",mem);
                in.putExtra("cid",cid);
                in.putExtra("uri",uri);
                in.putExtra("venue",venue);


                startActivity(in);
            }
        });

//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                FirebaseDatabase.getInstance().getReference("Events").child(eid).removeValue(new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(@Nullable @org.jetbrains.annotations.Nullable DatabaseError error, @NonNull @NotNull DatabaseReference ref) {
//                        Toasty.success(My_event_profile.this, "deleted succesfully", Toast.LENGTH_SHORT).show();
//                        Intent in = new Intent(My_event_profile.this,my_event_list.class);
//                        startActivity(in);
//
//                    }
//                });
//            }
//        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Are you sure you want to Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference("Events").child(eid)
                                .removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(My_event_profile.this, "deleted succesfully", Toast.LENGTH_SHORT)
                                        .show();
                                Intent in = new Intent(My_event_profile.this, my_event_list.class);
                                startActivity(in);
                            }
                        });
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }
        });

    }
}
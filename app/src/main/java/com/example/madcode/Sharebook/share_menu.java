package com.example.madcode.Sharebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class share_menu extends AppCompatActivity {

    RecyclerView recyclerView;
    sharebook sharebook;
    FloatingActionButton fb;
    DatabaseReference database;
    com.example.madcode.Sharebook.shareadapter shareadapter;
    ArrayList<Modelshare> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_menu);


        recyclerView = findViewById(R.id.share_menu);
        database = FirebaseDatabase.getInstance().getReference("sharebook");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        shareadapter = new shareadapter(this,list);
        recyclerView.setAdapter(shareadapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                    Modelshare modelshare = dataSnapshot.getValue(Modelshare.class);
                    list.add(modelshare);

                }

                shareadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        fb = (FloatingActionButton)findViewById(R.id.addshare_book);
        fb.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(getApplication(), com.example.madcode.Sharebook.sharebook.class));
            }
        });
}
}
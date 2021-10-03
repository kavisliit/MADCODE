package com.example.madcode.Request;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madcode.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class RequestBook extends AppCompatActivity {

    RecyclerView rec;
    DatabaseReference database;
    Req_adapter adapter;
    ArrayList<reqmodal> list;
    ArrayList<String> getReqId = new ArrayList<>();
    String ReqId;
    private Req_adapter.RecyclerViewClickListner listner;
    FirebaseUser reqfire;
    String requserid;

    EditText searchTextRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_book);


        searchTextRequest = findViewById(R.id.search_req_book);
        searchTextRequest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        setOnClickListner();
        rec = findViewById(R.id.recyclerViewR);
        database = FirebaseDatabase.getInstance().getReference("reqmodal");
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new Req_adapter(this,list,listner);
        rec.setAdapter(adapter);
        requserid= FirebaseAuth.getInstance().getCurrentUser().getUid();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    ReqId = dn.getKey();
                    reqmodal ev = dn.getValue(reqmodal.class);
                    if(ev.getRequserid().equals(requserid)){
                        list.add(ev);
                        getReqId.add(ReqId);
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    private void filter(String text){
        ArrayList<reqmodal> filteredList = new ArrayList<>();
        for(reqmodal item:list){
            if(item.getBook_name().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    private void setOnClickListner() {
        listner = new Req_adapter.RecyclerViewClickListner() {
            @Override
            public void onClick(View v, int position) {
                Intent in = new Intent(getApplicationContext(), ReqView.class);
                in.putExtra("RID",getReqId.get(position));
                in.putExtra("book_name",list.get(position).getBook_name());
                in.putExtra("book_authur",list.get(position).getBook_authur());
                in.putExtra("book_publisher",list.get(position).getBook_publisher());
                in.putExtra("book_description",list.get(position).getBook_description());
                in.putExtra("ReqUrl",list.get(position).getReqUrl());
                startActivity(in);

            }
        };
    }


    public void ReqAddToForm(View view){
        Intent intent = new Intent(this,bookreqform.class);
        startActivity(intent);
    }

}


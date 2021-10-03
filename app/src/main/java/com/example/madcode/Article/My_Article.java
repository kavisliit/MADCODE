package com.example.madcode.Article;

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

import com.example.madcode.Article.Model.ArticleModel;
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

public class My_Article extends AppCompatActivity {

    //variables

    RecyclerView rec;
    DatabaseReference database;
    Article_adapter adapter;
    ArrayList<ArticleModel> list;
    ArrayList<String> getArticleId = new ArrayList<>();
    String ArticleId;
    private Article_adapter.RecyclerViewClickListner listner;

    FirebaseUser fireUser;
    String CurrentUserId;
    EditText searchText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_article);
        //LayoutInflater inflater = LayoutInflater.from(this);
        //View v = inflater.inflate(R.layout.activity_my_article,null,false);
        //cl.addView(v,0);

        //Search text call
        searchText = findViewById(R.id.article_search_bar);
        searchText.addTextChangedListener(new TextWatcher() {
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
        rec = findViewById(R.id.recyclerView1);
        //get reference form database
        database = FirebaseDatabase.getInstance().getReference("ArticleModel");
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new Article_adapter(this,list,listner);
        rec.setAdapter(adapter);
        //current uid
        CurrentUserId= FirebaseAuth.getInstance().getCurrentUser().getUid();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot dn : snapshot.getChildren()){
                    ArticleId = dn.getKey();
                    ArticleModel ev = dn.getValue(ArticleModel.class);
                    if(ev.getCurrentUserId().equals(CurrentUserId)){
                        list.add(ev);
                        getArticleId.add(ArticleId);
                    }

                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    //Create filter method in My_Article
    private void filter(String text){
        ArrayList<ArticleModel> filteredList = new ArrayList<>();
        for(ArticleModel item:list){
            if(item.getHead_line().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }


    private void setOnClickListner() {
        listner = new Article_adapter.RecyclerViewClickListner() {
            @Override
            public void onClick(View v, int position) {
                Intent in = new Intent(getApplicationContext(), ArticleViewPage.class);

                in.putExtra("AID",getArticleId.get(position));
                //pass the data to view page (display)
                in.putExtra("Head_line",list.get(position).getHead_line());
                in.putExtra("Small_description",list.get(position).getSmall_description());
                in.putExtra("Sub_topic",list.get(position).getSub_topic());
                in.putExtra("Description",list.get(position).getDescription());
                in.putExtra("propic",list.get(position).getPurl());

                startActivity(in);
            }
        };

    }


   public void MyArticleToMyCreate(View view){
       Intent intent = new Intent(this,MyArticleCreate.class);
       startActivity(intent);
   }


}

package com.example.madcode.Request;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.madcode.MainActivity;
import com.example.madcode.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class bookreqform extends AppCompatActivity {

    EditText book_name,book_authur,book_publisher, book_description, ReqUrl;
    Button submit,back;
    FirebaseUser reqfire;
    String requserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookreqform);

        book_name=(EditText)findViewById(R.id.BookNametag);
        book_authur =(EditText)findViewById(R.id.Authursnametag);
        book_publisher=(EditText)findViewById(R.id.publishertag);
        book_description =(EditText)findViewById(R.id.Bookdescriptiontag);
        ReqUrl=(EditText)findViewById(R.id.reqimg);
        requserid= FirebaseAuth.getInstance().getCurrentUser().getUid();


        back=(Button)findViewById(R.id.canclereq);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        submit=(Button)findViewById(R.id.submitreq);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });

    }

    private void processinsert()
    {


        Map<String,Object> map=new HashMap<>();
        map.put("book_name",book_name.getText().toString());
        map.put("book_authur",book_authur.getText().toString());
        map.put("book_publisher",book_publisher.getText().toString());
        map.put("book_description",book_description.getText().toString());
        map.put("ReqUrl",ReqUrl.getText().toString());
        map.put("requserid",requserid);


        FirebaseDatabase.getInstance().getReference().child("reqmodal").push().setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {




            @Override
            public void onSuccess(Void aVoid) {
                book_name.setText("");
                book_authur.setText("");
                book_publisher.setText("");
                book_description.setText("");
                ReqUrl.setText("");


                Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(bookreqform.this,RequestBook.class);
                startActivity(intent);
            }
        })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                    }
                });



    }


    public void ReqAddToForm(View view){
        Intent intent = new Intent(this,RequestBook.class);
        startActivity(intent);
    }

}
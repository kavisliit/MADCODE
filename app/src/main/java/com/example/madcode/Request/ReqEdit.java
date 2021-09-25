package com.example.madcode.Request;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.madcode.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class ReqEdit extends AppCompatActivity {




    EditText book_name,book_authur,book_publisher, book_description, ReqUrl;
    Button submit;

    ImageButton buttonEdit,buttonDelete;
    String RID ;
    String txt = "not set";
    String txt1 = "not set";
    String txt2 = "not set";
    String txt3 = "not set";
    String txt4 = "not set";
    Task<Void> db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_edit);

        //connect to object to data base
        book_name=(EditText)findViewById(R.id.EditBookName);
        book_authur =(EditText)findViewById(R.id.EditAuthursName);
        book_publisher=(EditText)findViewById(R.id.EditPublisherName);
        book_description =(EditText)findViewById(R.id.EditBookDescription);
        ReqUrl=(EditText)findViewById(R.id.Editreqimg);
//        deleteBtn = findViewById(R.id.update_deletebutton);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            RID = extras.getString("RID");
            txt = extras.getString("book_name");
            txt1 = extras.getString("book_authur");
            txt2 = extras.getString("book_publisher");
            txt3 = extras.getString("book_description");
            txt4 = extras.getString("ReqUrl");
        }

        book_name.setText(txt);
        book_authur.setText(txt1);
        book_publisher.setText(txt2);
        book_description.setText(txt3);
        ReqUrl.setText(txt4);


        //complete button
        submit=(Button)findViewById(R.id.EditSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txt  = book_name.getText().toString();
                txt1  = book_authur.getText().toString();
                txt2  = book_publisher.getText().toString();
                txt3  = book_description.getText().toString();
                txt4  = ReqUrl.getText().toString();

                if(TextUtils.isEmpty(book_name.getText().toString()))
                {
                    Toasty.success(getApplicationContext(), "Book Name Required!", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                if(TextUtils.isEmpty(book_authur.getText().toString()))
                {
                    Toasty.success(getApplicationContext(), "Book Authur Required!", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                if(TextUtils.isEmpty(book_publisher.getText().toString()))
                {
                    Toasty.success(getApplicationContext(), "Book Publisher Required!", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                if(TextUtils.isEmpty(book_description.getText().toString()))
                {
                    Toasty.success(getApplicationContext(), "Book Description Required!", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                if(TextUtils.isEmpty(ReqUrl.getText().toString()))
                {
                    Toasty.success(getApplicationContext(), "Image Url Required!", Toast.LENGTH_SHORT, true).show();
                    return;
                }

                Map<String,Object> map = new HashMap<>();
                map.put("book_name",txt);
                map.put("book_authur",txt1);
                map.put("book_publisher",txt2);
                map.put("book_description",txt3);
                map.put("ReqUrl",txt4);


                db = FirebaseDatabase.getInstance().getReference("reqmodal").child(RID)
                        .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(ReqEdit.this, "success", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(ReqEdit.this,RequestBook.class);
                                startActivity(in);
                            }
                        });

            }
        });


    }
}
package com.example.madcode.Request;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.madcode.R;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

public class ReqView extends AppCompatActivity {

    ImageButton buttonEdit,buttonDelete;
    String AID ;
    String txt = "not set";
    String txt1 = "not set";
    String txt2 = "not set";
    String txt3 = "not set";
    String txt4 = "not set";


    public void ViewToList(View view){
        Intent intent = new Intent(this, RequestBook.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_view);



        buttonEdit = (ImageButton) findViewById(R.id.editicon);
        buttonDelete = (ImageButton) findViewById(R.id.deleteicon);

        TextView book_name = findViewById(R.id.reqheadingview);
        TextView book_authur = findViewById(R.id.reqauthurview);
        TextView book_publisher = findViewById(R.id.reqpublishreview);
        TextView book_description = findViewById(R.id.infoviewmulti);
        ImageView ReqUrl = findViewById(R.id.reqviewimg);

        //pass the value to edit page
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReqView.this,ReqEdit.class);

                intent.putExtra("AID",AID);
                //pass the data to view page (display)
                intent.putExtra("book_name",txt);
                intent.putExtra("book_authur",txt1);
                intent.putExtra("book_publisher",txt2);
                intent.putExtra("book_description",txt3);
                intent.putExtra("ReqUrl",txt4);

                startActivity(intent);
            }
        });


        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ReqUrl.getContext());
                builder.setTitle("Delete Book Request");
                builder.setMessage("Confirm Delete?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference("reqmodal").child(AID).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(ReqView.this, "deleted succesfully", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(ReqView.this,RequestBook.class);
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



//display data view page
        Bundle extras = getIntent().getExtras();
        if(extras != null){

            AID = extras.getString("AID");
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
        Glide.with(this).load(txt4).into(ReqUrl);


    }


}
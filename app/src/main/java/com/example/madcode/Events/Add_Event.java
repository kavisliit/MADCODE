package com.example.madcode.Events;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.madcode.Eventmain;
import com.example.madcode.R;
import com.example.madcode.nav_activity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class Add_Event extends nav_activity {
    FirebaseUser us;
    int hour,minite;
    DatabaseReference db;
    EditText name,type,date,time,venue,max_mem,uri;
    Button create,back,browse,upload;
    ImageView imgview;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;
    static  String imguri;
    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_add_event);
        LayoutInflater inflater = LayoutInflater.from(this);
        View my = inflater.inflate(R.layout.activity_add_event,null,false);
        cl.addView(my,0);





        uri = findViewById(R.id.get_event_uri);
        name = findViewById(R.id.ptext1);
        type = findViewById(R.id.ptext2);
        date = findViewById(R.id.ptext5);
        time = findViewById(R.id.ptext6);
        venue = findViewById(R.id.ptext3);
        max_mem = findViewById(R.id.mem_ex);
        back = findViewById(R.id.back_btn);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        //method for datepicker dialog
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_Event.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date1 = day+"/"+month+"/"+year;
                        date.setText(date1);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

       // method for back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Eventmain.class));
                  finish();
            }
        });

        //method for event creation
        create = findViewById(R.id.create_btn);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                eventcreate();

            }
        });

        //method for timepickerdialog
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Poptimepicker(v);
            }
        });

    }

    //method and validations
    private void eventcreate() {

        if(TextUtils.isEmpty(name.getText().toString())){
            Toasty.error(Add_Event.this, "Event name required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(type.getText().toString())){
            Toasty.error(Add_Event.this, "Event type Required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(date.getText().toString())){
            Toasty.error(Add_Event.this, "Date  Required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(time.getText().toString())){
            Toasty.error(Add_Event.this, "Time Required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(venue.getText().toString())){
            Toasty.error(Add_Event.this, "Event Venue Required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(max_mem.getText().toString())){
            Toasty.error(Add_Event.this, "No of Members Required", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(uri.getText().toString())){
            Toasty.error(Add_Event.this, "URL Required", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            //enter the data info firebase db
            us = FirebaseAuth.getInstance().getCurrentUser();
            String uid = us.getUid();
            Map<String, Object> map = new HashMap<>();
            map.put("name", name.getText().toString());
            map.put("type", type.getText().toString());
            map.put("date", date.getText().toString());
            map.put("time", time.getText().toString());
            map.put("venue", venue.getText().toString());
            map.put("max", max_mem.getText().toString());
            map.put("uri", uri.getText().toString());
            map.put("cid", uid);
            db = FirebaseDatabase.getInstance().getReference("Events");
            db.push().setValue(map)


                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        //clear the text filelds
                        @Override
                        public void onSuccess(Void unused) {
                            name.setText("");
                            time.setText("");
                            type.setText("");
                            venue.setText("");
                            date.setText("");
                            max_mem.setText("");
                            uri.setText("");


                            Toasty.success(Add_Event.this, "Inserted successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toasty.error(Add_Event.this, "Could not Insert", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }




    public void Poptimepicker(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int shour, int smin) {

                hour = shour;
                minite = smin;
                time.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minite));

            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hour,minite,true);
        timePickerDialog.setTitle("select time");
        timePickerDialog.show();
    }
}

package com.example.madcode.Events;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.madcode.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class My_event_edit extends AppCompatActivity {
    int hour,minite;
    EditText tname,tdate,ttime,ttype,tmem,tvenue,turi;
    String eid,cid,name,date,time,venue,mem,type,uri;
    Button savebtn;
    ImageView img;
    Task<Void> db;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_event_edit);

        tname = findViewById(R.id.my_evet_edit_name);
        tdate = findViewById(R.id.my_evet_edit_date);
        ttime = findViewById(R.id.my_evet_edit_time);
        tvenue = findViewById(R.id.my_evet_edit_venue);
        ttype = findViewById(R.id.my_evet_edit_type);
        tmem = findViewById(R.id.my_evet_edit_mem);
        savebtn = findViewById(R.id.my_event_edit_btn2);
        img = findViewById(R.id.my_evet_edit_img);
        turi = findViewById(R.id.my_evet_edit_uri);
        ttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Poptimepicker(v);
            }
        });


        Bundle extras = getIntent().getExtras();
        if(extras != null){

            name= extras.get("name").toString();
            date = extras.get("date").toString();
            time = extras.get("time").toString();
            type = extras.get("type").toString();
            mem = extras.get("max").toString();
            eid = extras.getString("eid");
            cid = extras.getString("cid");
            uri = extras.getString("uri");
            venue = extras.getString("venue");

            tname.setText(name);
            tdate.setText(date);
            ttime.setText(time);
            tvenue.setText(venue);
            ttype.setText(type);
            tmem.setText(mem);
            turi.setText(uri);
            Glide.with(getApplicationContext()).load(extras.getString("uri")).into(img);





        }
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name  = tname.getText().toString();
                date  = tdate.getText().toString();
                time  = ttime.getText().toString();
                venue  = tvenue.getText().toString();
                type  = ttype.getText().toString();
                mem  = tmem.getText().toString();
                uri = turi.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toasty.error(My_event_edit.this, "Name is required", Toast.LENGTH_SHORT, true).show();
                    
                    return;
                }
                if(TextUtils.isEmpty(time)){
                    Toasty.error(My_event_edit.this, "Time is Required.", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                if(TextUtils.isEmpty(venue)){
                    Toasty.error(My_event_edit.this, "Venue is Required.", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                if(TextUtils.isEmpty(type)){
                    Toasty.error(My_event_edit.this, "Type is required.", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                if(TextUtils.isEmpty(date)){
                    Toasty.error(My_event_edit.this, "Date is required.", Toast.LENGTH_SHORT, true).show();
                    return;
                }
                Map<String,Object> map = new HashMap<>();
                map.put("name",name);
                map.put("type",type);
                map.put("date",date);
                map.put("time",time);
                map.put("venue",venue);
                map.put("max",mem);
                map.put("uri",uri);
                map.put("cid",cid);
                db = FirebaseDatabase.getInstance().getReference("Events").child(eid)
                        .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toasty.success(My_event_edit.this, "success", Toast.LENGTH_SHORT).show();
                                Intent in = new Intent(My_event_edit.this,my_event_list.class);
                                startActivity(in);

                            }
                        });

            }
        });

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

//

        tdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(My_event_edit.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date1 = day+"/"+month+"/"+year;
                        tdate.setText(date1);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


    }
    public void Poptimepicker(View view){
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int shour, int smin) {

                hour = shour;
                minite = smin;
                ttime.setText(String.format(Locale.getDefault(),"%02d:%02d",hour,minite));

            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,onTimeSetListener,hour,minite,true);
        timePickerDialog.setTitle("select time");
        timePickerDialog.show();
    }
}
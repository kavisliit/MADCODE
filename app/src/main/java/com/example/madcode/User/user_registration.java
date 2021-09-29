package com.example.madcode.User;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madcode.Events.Models.User;
import com.example.madcode.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class user_registration extends AppCompatActivity {

    TextView name,uri,password,email,phone,dob;
    Button create;
    User ob ;
    DatabaseReference dbref;
    private ProgressBar pb;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        name = findViewById(R.id.user_txt1);
        password = findViewById(R.id.user_txt3);
        uri = findViewById(R.id.user_txt7);
        email = findViewById(R.id.user_txt4);
        phone = findViewById(R.id.user_txt5);
        dob = findViewById(R.id.user_txt6);
        create = findViewById(R.id.creat);
        mAuth = FirebaseAuth.getInstance();


        //method that execute when user click the register button
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(TextUtils.isEmpty(name.getText().toString())){
                        Toasty.error(user_registration.this, "Enter Name", Toast.LENGTH_SHORT).show();
                    }

                    else if(TextUtils.isEmpty(password.getText().toString())){
                        Toasty.error(user_registration.this, "Enter password", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(email.getText().toString())){
                        Toasty.error(user_registration.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(phone.getText().toString())){
                        Toasty.error(user_registration.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                    }
                    else if(TextUtils.isEmpty(dob.getText().toString())){
                        Toasty.error(user_registration.this, "Enter DOB", Toast.LENGTH_SHORT).show();
                    }
                    else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                        Toasty.error(user_registration.this, "Enter valid Email", Toast.LENGTH_SHORT).show();
                    }
                    else if(password.getText().toString().length() < 6){
                        Toasty.error(user_registration.this, "Min length should be 6 characters", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        String email1 = email.getText().toString().trim();
                        String pass = password.getText().toString().trim();

                        mAuth.createUserWithEmailAndPassword(email1,pass)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                        if(task.isSuccessful()){
                                            //insert data into the firebase db
                                            Map<String,Object> map = new HashMap<>();
                                            map.put("uname",name.getText().toString());
                                            map.put("uri",uri.getText().toString());
                                            map.put("password",password.getText().toString());
                                            map.put("email",email.getText().toString());
                                            map.put("phone",phone.getText().toString());
                                            map.put("dob",dob.getText().toString());
                                            FirebaseDatabase.getInstance().getReference("Users")
                                                    .child(FirebaseAuth.getInstance()
                                                    .getCurrentUser().getUid()).setValue(map)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                                                            if(task.isSuccessful()) {
                                                                Toasty.success(user_registration.this, "Successfully created", Toast.LENGTH_SHORT).show();
                                                                clearcontrols();
                                                            }
                                                            else{
                                                                Toasty.error(user_registration.this, "Error", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });


                                        }
                                    }
                                });


                    }

                }
                catch (NumberFormatException e){
                    Toasty.error(user_registration.this, "Invalid Number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //clear all input fields
    private void clearcontrols(){
        name.setText("");
        uri.setText("");
        password.setText("");
        dob.setText("");
        phone.setText("");
        email.setText("");
    }



}

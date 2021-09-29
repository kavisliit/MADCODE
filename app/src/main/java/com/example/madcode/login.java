package com.example.madcode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madcode.User.Forget_password;
import com.example.madcode.User.user_profile;
import com.example.madcode.User.user_registration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;

public class login extends AppCompatActivity implements View.OnClickListener {
    TextView pass,email,forget;
    private TextView register;
    FirebaseAuth mAuth;
    Button create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ProgressBar pb;
      email = findViewById(R.id.user_email_login);
      pass  = findViewById(R.id.pass_txt);
      forget = findViewById(R.id.forget_pass);
      forget.setOnClickListener(this);
      create = findViewById(R.id.login_btn);
      create.setOnClickListener(this);
        register = findViewById(R.id.reg);
        register.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg:
                startActivity(new Intent(this, user_registration.class));
                break;

            case R.id.login_btn:
                userlogin();
                break;

            case R.id.forget_pass:
                startActivity(new Intent(login.this, Forget_password.class));
                break;
        }
    }

    private void userlogin() {

        String emails = email.getText().toString().trim();
        String passwo = pass.getText().toString().trim();
        if(emails.isEmpty()){
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emails).matches()){
            email.setError("please enter a valid email");
            email.requestFocus();
            return;
        }
        if(passwo.isEmpty()){
            pass.setError("password is required!!");
            pass.requestFocus();
            return;
        }
        if(passwo.length() < 6){
            pass.setError("password length must be min 6");
            pass.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(emails,passwo).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        startActivity(new Intent(login.this, MainActivity.class));
                    }
                    else{
                        user.sendEmailVerification();
                        Toast.makeText(login.this, "Check your Email", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(login.this, "Failed!! please check your credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.example.madcode.User;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madcode.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class Forget_password extends AppCompatActivity {
    EditText email;
    Button btn;
    ProgressBar pb;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        email = findViewById(R.id.rest_mail);
        btn = findViewById(R.id.rest_mail_button);
        pb = findViewById(R.id.pbar1);
        pb.setVisibility(View.INVISIBLE);
        auth = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpassword();
            }
        });
    }

    private void resetpassword() {
        String em = email.getText().toString().trim();
        if(em.isEmpty()){
            email.setError("email is required!!");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(em).matches()){
            email.setError("please provide valid email");
            email.requestFocus();
            return;
        }
        pb.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(em).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Forget_password.this, "Check your email to reset your password", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Forget_password.this, "Try again something wrong happen", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
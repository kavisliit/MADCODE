package com.example.madcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainSpashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    //variables
    Animation topAnimation,bottomAnimation;
    ImageView image;
    TextView logo, slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_spash_screen);

        //call Animation
        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        image = findViewById(R.id.imageView4);
        logo = findViewById(R.id.textView34);
        slogan = findViewById(R.id.textView35);

        image.setAnimation(topAnimation);
        logo.setAnimation(bottomAnimation);
        slogan.setAnimation(bottomAnimation);

        //pass the next activity after 5000 Millisecond
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent (MainSpashScreenActivity.this,login.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);


    }
}
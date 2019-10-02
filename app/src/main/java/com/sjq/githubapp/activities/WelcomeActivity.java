package com.sjq.githubapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sjq.githubapp.R;

import androidx.appcompat.app.AppCompatActivity;



public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                WelcomeActivity.this.finish();

            }
        }, 2000);
    }
}

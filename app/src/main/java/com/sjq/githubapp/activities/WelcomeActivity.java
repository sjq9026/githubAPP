package com.sjq.githubapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sjq.githubapp.R;
import com.sjq.githubapp.util.UtilsSharePre;

import androidx.appcompat.app.AppCompatActivity;


public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_welcome);
        final String user_name = UtilsSharePre.getInstance().getPreferenceString(WelcomeActivity.this,UtilsSharePre.USER_NAME,"");
        final String token = UtilsSharePre.getInstance().getPreferenceString(WelcomeActivity.this,UtilsSharePre.TOKEN,"");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = null;

                if(user_name.isEmpty() || token.isEmpty()){
                    intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                }else{
                    intent = new Intent(WelcomeActivity.this,MainActivity.class);
                }
                startActivity(intent);
                WelcomeActivity.this.finish();

            }
        }, 1500);
    }



}

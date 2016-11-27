package com.enrique.segsyl.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.enrique.segsyl.R;
import com.enrique.segsyl.Utils.PreferenceManager;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        (new Handler()).postDelayed(new Runnable() {
            public void run() {
                goNext();
            }
        }, SPLASH_TIME_OUT);
    }

    private void goNext() {
        if(!PreferenceManager.getInstance(this).getPrefernceAlumnoString().isEmpty()){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }

    }
}

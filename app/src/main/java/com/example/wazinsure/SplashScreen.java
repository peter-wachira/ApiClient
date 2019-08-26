package com.example.wazinsure;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                        startActivity(intent);


                        finish();

                    }
                },
                1500);

    }
}

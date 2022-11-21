package com.serkanozcelik.thepurge5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBaslatma();


    }

    private void loginBaslatma() {
        Intent loginIntent =new Intent(MainActivity.this,LoginActivity.class);
        startActivity(loginIntent);
    }
}
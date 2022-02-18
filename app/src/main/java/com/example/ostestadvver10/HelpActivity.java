package com.example.ostestadvver10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpActivity extends AppCompatActivity {

    Button helpBackButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupcontrols();



    }
    protected void setupcontrols ()
    {
        helpBackButton= findViewById(R.id.aboutBackButton);
        helpBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HelpActivity.this, MainMenuActivity.class);
                startActivity(intent);


            }
        });
    }
}
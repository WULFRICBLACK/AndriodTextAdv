package com.example.ostestadvver10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity
{
    public static final String MY_PREFS = "prefs";
    public static final String MY_KEY = "pos";
    SharedPreferences sharedPrefs;

    Button newGameButton;
    Button loadButton;

    Button aboutButton;
    Button helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        setupControls();

    }   //  protected void onCreate(Bundle savedInstanceState)

    protected void setupControls()
    {
        newGameButton = findViewById(R.id.NewGameButton);

        newGameButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        loadButton = findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);

                sharedPrefs = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
                int pos = sharedPrefs.getInt(MY_KEY, 0);

                intent.putExtra(MainActivity.MY_KEY,Integer.toString(pos));

                startActivity(intent);
            }
        });
        aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AboutActivity.class);

                startActivity(intent);

            }
        });

        helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), HelpActivity.class);

                startActivity(intent);

            }
        });


    }   //  protected void setupControls()

}   //  public class MainMenuActivity extends AppCompatActivity
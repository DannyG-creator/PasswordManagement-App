package com.example.passwordmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.regex.Pattern;

public class Main extends AppCompatActivity {

    String Scores;
    TextView Score;
    String Password;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        Password = settings.getString("password", "");

        Score = (TextView) findViewById(R.id.Score);

        if (Password.length() > 12) {
            score = score + 60;
        }else if (Password.length() <= 12 && Password.length() > 8) {
            score = score + 50;
        }else{
            score = score + 25;
        }
        if(Pattern.compile( "[a-z]" ).matcher( Password ).find()){
            score = score + 10;
        }
        if(Pattern.compile( "[A-Z]" ).matcher( Password ).find()){
            score = score + 10;
        }
        if(Pattern.compile( "[0-9]" ).matcher( Password ).find()){
            score = score + 10;
        }
        if(Pattern.compile( "[!@#$%^&*()]" ).matcher( Password ).find()){
            score = score + 10;
        }

        Scores = String.valueOf(score);
        Score.setText(Scores + "%");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.passwords:
                        startActivity(new Intent(getApplicationContext()
                                , Passwords.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.generate:
                        startActivity(new Intent(getApplicationContext()
                                , Generate.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
   }
}
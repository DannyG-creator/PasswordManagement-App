package com.example.passwordmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Random;

public class Generate extends AppCompatActivity {

    Button GeneratePassword;
    Button Copy;
    TextView GeneratedPassword;

    ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);


    BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

    bottomNavigationView.setSelectedItemId(R.id.generate);

    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , Main.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.passwords:
                        startActivity(new Intent(getApplicationContext()
                                , Passwords.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.generate:
                        return true;
                }
                return false;
            }
    });

        GeneratePassword = (Button)findViewById(R.id.GeneratePassword);
        Copy = (Button)findViewById(R.id.Copy);
        GeneratedPassword = (TextView)findViewById(R.id.GeneratedPassword);

        clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);

        Copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = GeneratedPassword.getText().toString();

                if (!text.equals("")){
                    ClipData clipData = ClipData.newPlainText("text",text);
                    clipboardManager.setPrimaryClip(clipData);
                }
            }
        });
        GeneratePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeneratedPassword.setText(GenerateString(13));
            }
        });
    }

    private String GenerateString(int length){
        char[] chars = 13 "QWERTYUIOPASDFGHJKLZXCVBNMmnbvcxzlkjhgfdsapoiuytrewq1234567890!@#$%^&*()".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for ( int i = 0; i<length;i++)
        {
            char c = chars[random.nextInt(chars.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
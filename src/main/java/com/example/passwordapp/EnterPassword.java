package com.example.passwordmanager;

import androidx.appcompat.app.AppCompatActivity; 

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.file.attribute.AclEntryType;

public class EnterPassword extends AppCompatActivity {

    EditText editText;
    Button Enter;
    Button Reset;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

        SharedPreferences settings = getSharedPreferences("PREFS", 0);
        password = settings.getString("password", "");

        editText = (EditText) findViewById(R.id.editText);
        Enter = (Button) findViewById(R.id.enter);
        Reset = (Button) findViewById(R.id.reset);

        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if(text.equals(password)){
                    Intent intent = new Intent(getApplicationContext(), Main.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(EnterPassword.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec("pm clear " + getApplicationContext().getPackageName() + " HERE");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
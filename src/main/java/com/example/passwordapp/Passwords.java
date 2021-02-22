package com.example.passwordmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Passwords extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";
    ListView list_view;
    EditText AddAccount;
    EditText AddPassword;
    DatabaseHelper myDB;
    ArrayList<User> userList;
    User user;

    EditText editText1, editText2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwords);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button);

        AddAccount = (EditText) findViewById(R.id.AddAccount);
        AddPassword = (EditText) findViewById(R.id.AddPassword);
        myDB = new DatabaseHelper(this);

        userList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        int numRows = data.getCount();
        if (numRows == 0) {
            Toast.makeText(Passwords.this, "Empty", Toast.LENGTH_SHORT).show();
        } else {
            while (data.moveToNext()) {
                user = new User(data.getString(1), data.getString(2));
                userList.add(user);
            }
            TwoColumnListAdapter adapter = new TwoColumnListAdapter(this, R.layout.list_adapter, userList);
            list_view = (ListView) findViewById(R.id.list_view);
            list_view.setAdapter(adapter);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.passwords);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                _addItem();
                break;
            case R.id.sort:
                _sortItems();
                break;
        }
        return true;
    }

    private void _sortItems() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Passwords.this);
        View v = LayoutInflater.from(Passwords.this).inflate(R.layout.sort_dialog, null, false);
        builder.setView(v);
        builder.show();
    }

    private void _addItem() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Passwords.this);
        View v = LayoutInflater.from(Passwords.this).inflate(R.layout.item_dialog, null, false);

        builder.setView(v);
        final EditText AddAccount = v.findViewById(R.id.AddAccount);
        final EditText AddPassword = v.findViewById(R.id.AddPassword);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String account = AddAccount.getText().toString();
                String password = AddPassword.getText().toString();
                if(account.length() != 0 && password.length() != 0){
                    AddData(account, password);
                    AddAccount.setText("");
                    AddPassword.setText("");
                }else{
                    Toast.makeText(Passwords.this, "No Text", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void AddData(String accounts, String passwords){
        boolean insertData = myDB.addData(accounts, passwords);

        if(insertData==true){
            Toast.makeText(Passwords.this, "Data Saved", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(Passwords.this, "Data Not Saved", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.passwordmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class TwoColumnListAdapter extends ArrayAdapter<User> {

    private LayoutInflater inflator;
    private ArrayList<User> users;
    private int viewResourceID;

    public TwoColumnListAdapter(Context context, int textViewResourceID, ArrayList<User> users) {
        super(context, textViewResourceID, users);
        this.users = users;
        inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewResourceID = textViewResourceID;
    }

    public View getView(int position, View convertView, ViewGroup parents){
        convertView = inflator.inflate(viewResourceID,null);

        User user = users.get(position);

        if (user != null) {
            TextView accounts = (TextView) convertView.findViewById(R.id.textAccounts);
            TextView passwords = (TextView) convertView.findViewById(R.id.textPasswords);

            if (accounts != null) {
                accounts.setText(user.getAccount());
            }
            if (passwords != null) {
                passwords.setText(user.getPassword());
            }
        }
        return convertView;
    }
}
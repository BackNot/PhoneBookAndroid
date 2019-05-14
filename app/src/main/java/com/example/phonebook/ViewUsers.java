package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ViewUsers extends AppCompatActivity {

    /* When activity is created , populate it with all the users that are in the DB.
     * Use ShowAllUsers query to populate them in a list (allUsers)
     * Append every user to a textView and place a new line after each record.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);
        TextView textView = (TextView)findViewById(R.id.txtUsers);
        List<User> allUsers = MainActivity.myDatabase.MyConreteDAO().ShowAllUsers();
        textView.setText(" \n"); // clear the textview
        for (User user : allUsers)
        {
            textView.append(String.valueOf(user.getID())+" ");
            textView.append(String.valueOf(user.getFirstName())+" ");
            textView.append(String.valueOf(user.getNumber())+" ");
            textView.append(String.valueOf(user.getInformation())+" ");
            textView.append(String.valueOf(user.getCategory())+" ");
            textView.append("\n");
        }
    }
}

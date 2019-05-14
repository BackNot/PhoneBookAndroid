package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
public static DbContext myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabase = Room.databaseBuilder(getApplicationContext(),DbContext.class,"UserDb" ).allowMainThreadQueries().build();
    }
    public void GoToAddUserActivity(View view)
    {
        Intent k = new Intent(MainActivity.this, AddUserActivity.class);
        startActivity(k);
    }
    public void GoToViewUsersActivity(View view)
    {
        Intent k = new Intent(MainActivity.this, ViewUsers.class);
        startActivity(k);
    }
    public void GoToRemoveUserActivity(View view)
    {
        Intent k = new Intent(MainActivity.this, RemoveUser.class);
        startActivity(k);
    }
    public void GoToFindUserActivity(View view)
    {
        Intent k = new Intent(MainActivity.this, FindUser.class);
        startActivity(k);
    }
}

package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FindUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);
        final EditText enteredId = (EditText)findViewById(R.id.txtEnterId);
        Button btn = (Button)findViewById(R.id.button6);
       btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                int userId = Integer.parseInt(enteredId.getText().toString());
                if(userId>=0) {
                    // everything is OK. Find the user in DB.
                  User foundUser=  MainActivity.myDatabase.MyConreteDAO().ShowUserWithId(userId);
                  if(foundUser!=null) {
                      // Pass the id to the new activity. We will edit the contact there.
                      Intent i = new Intent(FindUser.this, EditUser.class);
                      i.putExtra("id", userId);
                      startActivity(i);
                  }
                  else Toast.makeText(getApplicationContext(),"No such record exist.",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(),"Enter positive ID!",Toast.LENGTH_SHORT).show();
            }});
            }
    }


package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class RemoveUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_user);
        final EditText enteredId = (EditText)findViewById(R.id.txtRemoveUser);
        Button deleteAll = (Button) findViewById(R.id.btnDeleteAll);
        Button deleteUser = (Button)findViewById(R.id.btnDelete);
        deleteUser.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                int userId;
                // Check if entered ID is number.
                if(AddUserActivity.isInteger(enteredId.getText().toString()))
                {
                    userId = (Integer.parseInt(enteredId.getText().toString()));

                    // Search for corresponding entity in DB and delete it if it exist.
                   User foundUser = MainActivity.myDatabase.MyConreteDAO().ShowUserWithId(userId);
                    if (foundUser != null) {
                        MainActivity.myDatabase.MyConreteDAO().DeleteUser(foundUser);
                        Toast.makeText(getApplicationContext(),"User deleted.",Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(getApplicationContext(),"No such record exist",Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "Please, enter numeric ID", Toast.LENGTH_SHORT).show();
        }
        }); // end onClickListener

        // Find all users and delete them one by one.
            deleteAll.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                   List<User> allUsers =  MainActivity.myDatabase.MyConreteDAO().ShowAllUsers();
                   for (User user : allUsers)
                   {
                      User userToDelete =  MainActivity.myDatabase.MyConreteDAO().ShowUserWithId(user.getID());
                      MainActivity.myDatabase.MyConreteDAO().DeleteUser(userToDelete);
                   }
                   Toast.makeText(getApplicationContext(),"Everything is deleted.",Toast.LENGTH_SHORT).show();
                }
            }); // end onClickListener
    }
}

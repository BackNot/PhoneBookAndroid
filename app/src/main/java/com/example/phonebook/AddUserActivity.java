package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddUserActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        // Get the fields
        final EditText txtid = (EditText)findViewById(R.id.txtId);
        final EditText txtname = (EditText)findViewById(R.id.txtName);
        final EditText txtinformation = (EditText)findViewById(R.id.txtInformation);
        final EditText txtcategory = (EditText)findViewById(R.id.txtCategory);
        final EditText txtnumber = (EditText)findViewById(R.id.txtNumber);
        Button buttonOne = (Button) findViewById(R.id.button);
        // When user click on the button , perform the validations and if everything is correct add
        // to the database. If something is wrong tell the user to fix it.
            buttonOne.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                int userId;
                String userName = txtname.getText().toString();
                String userInformation = txtinformation.getText().toString();
                String userCategory = txtcategory.getText().toString();
                String userNumber = txtnumber.getText().toString();

                int errorCounter = 0; // Error counter. If !=0 don't allow user addition.

              // Check if the entered ID is a number with our custom method (see below).
                    if(isInteger(txtid.getText().toString())) {
                        userId = Integer.parseInt(txtid.getText().toString());

                        // Check if ID is unique. If it is create the user
                        if (MainActivity.myDatabase.MyConreteDAO().ShowUserWithId(userId) == null) {
                            errorCounter += IsValidField(userName,15,"Please, use shorter name");
                            errorCounter += IsValidField(userInformation,13,"Please, use shorter information");
                            errorCounter += IsValidField(userCategory,10,"Please, use shorter category");
                            errorCounter += IsPositiveNumber(userId,"Please, do not use negative ID");
                           if(errorCounter==0) { // if every validation has passed we are ready to add the user.

                               User user = new User();
                               user.setID(userId);
                               user.setFirstName(userName);
                               user.setCategory(userCategory);
                               user.setNumber(userNumber);
                               user.setInformation(userInformation);
// add it
                               MainActivity.myDatabase.MyConreteDAO().AddUser(user);
                               Toast.makeText(getApplicationContext(), "User Added :)", Toast.LENGTH_SHORT).show();
                           } // end if(isValid)
                           } else
                            Toast.makeText(getApplicationContext(), "Sorry, but this ID is taken.", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(getApplicationContext(), "Please, enter correct ID.", Toast.LENGTH_SHORT).show();

            }
        });    }

        // Check if user id is positive. Show custom error if not.
    public int IsPositiveNumber(int id,String errorMsg)
    {
        if(id>=0)return 0;
        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_SHORT).show();
        return 1;
    }
        // Check if the field's length is not greater than the wanted. Show custom error message.
    public int IsValidField(String field,int len,String errorMsg)
         {
            if(field.length()<=len) return 0;
            Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_SHORT).show();
            return 1;
         }
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
}

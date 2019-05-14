package com.example.phonebook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        // Get the editTexts and the button
        final EditText name = (EditText)findViewById(R.id.txtEditName);
        final EditText number = (EditText)findViewById(R.id.txtEditNumber);
        final EditText info = (EditText)findViewById(R.id.txtEditInformation);
        final EditText category = (EditText)findViewById(R.id.txtEditCategory);
        Button btn = (Button)findViewById(R.id.button7);
        // Get the id from the previous activity
        Bundle b = getIntent().getExtras();
        final int id = b.getInt("id");
        // Get the user
       final User user = MainActivity.myDatabase.MyConreteDAO().ShowUserWithId(id);
            name.setText(user.getFirstName());
            number.setText(user.getNumber());
            info.setText(user.getInformation());
            category.setText(user.getCategory());

        btn.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                // Collect updated fields and validate them.
                String userName = name.getText().toString();
                String userInformation = info.getText().toString();
                String userCategory = category.getText().toString();
                String userNumber = number.getText().toString();

                int errorCounter = 0; // Error counter. If !=0 don't allow user addition.
                errorCounter += IsValidField(userName,15,"Please, use shorter name");
                errorCounter += IsValidField(userInformation,13,"Please, use shorter information");
                errorCounter += IsValidField(userCategory,10,"Please, use shorter category");

                if(errorCounter==0) { // if every validation has passed we are ready to add the user.


                    user.setID(id);
                    user.setFirstName(userName);
                    user.setCategory(userCategory);
                    user.setNumber(userNumber);
                    user.setInformation(userInformation);
// add it
                    MainActivity.myDatabase.MyConreteDAO().UpdateUser(user);
                    Toast.makeText(getApplicationContext(), "User Updated :)", Toast.LENGTH_SHORT).show();
                }
            }});
    }
    // Params: Field to validate, allowed length , custom error message
    // Method returns 0 and 1.
    public int IsValidField(String field,int len,String errorMsg)
    {
        if(field.length()<=len) return 0;
        Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_SHORT).show();
        return 1;
    }
    }


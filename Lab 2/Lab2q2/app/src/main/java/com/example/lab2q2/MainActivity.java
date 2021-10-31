package com.example.lab2q2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.lab2q2.MESSAGE";
    EditText NameEditText, PhoneEditText, PasswordEditText, EmailEditText;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submitButton = findViewById(R.id.button);
        NameEditText = findViewById(R.id.editTextTextPersonName);
        PhoneEditText = findViewById(R.id.editTextPhone);
        PasswordEditText = findViewById(R.id.editTextTextPassword);
        EmailEditText = findViewById(R.id.editTextTextEmailAddress);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Name = NameEditText.getText().toString();
                final String Pass = PasswordEditText.getText().toString();
                final String Email = EmailEditText.getText().toString();
                final String Phone = PhoneEditText.getText().toString();

                if (Name.length() == 0) {
                    NameEditText.requestFocus();
                    NameEditText.setError("FIELD CAN NOT BE EMPTY");
                }
                else if (!Name.matches("[a-zA-Z ]+")) {
                    NameEditText.requestFocus();
                    NameEditText.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(Pass.length()==0)
                {
                    PasswordEditText.requestFocus();
                    PasswordEditText.setError("FIELD CANNOT BE EMPTY");
                }
                else if(Phone.length()==0)
                {
                    PhoneEditText.requestFocus();
                    PhoneEditText.setError("FIELD CANNOT BE EMPTY");
                }
                /*
                else if (!Phone.matches("[0-9]+")) {
                    PhoneEditText.requestFocus();
                    PhoneEditText.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                 */

                else if(Email.length()==0)
                {
                    EmailEditText.requestFocus();
                    EmailEditText.setError("FIELD CANNOT BE EMPTY");
                }
                else {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    EditText editText = (EditText) findViewById(R.id.editTextTextPersonName);
                    String message = ("Welcome to the app user: " + editText.getText().toString());
                    emailIntent.putExtra(EXTRA_MESSAGE, message);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{Email});
                    //need this to prompts email client only
                    emailIntent.setType("message/rfc822");
                    startActivity(emailIntent);

                }
            }
        });
    }

}


    /*
    public void buttonClicked(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Thank you, your request is being processed!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
     */

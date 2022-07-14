package com.example.addisu;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.provider.ContactsContract; import android.view.View; import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText fName, lName, phoneNumber, email;
    Button addButton;

    DatabaseHandler dba;

    @Override

    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);

        dba = new DatabaseHandler(MainActivity.this); fName = findViewById(R.id.fNameTV); lName = findViewById(R.id.lNameTV);

        phoneNumber = findViewById(R.id.phoneNumberTV); email = findViewById(R.id.emailAddressTV); addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View v) {
                saveData();
            }
        });

    }

    public void saveData(){
        Contact contact = new Contact();
        String firstName = fName.getText().toString();

        String lastName = lName.getText().toString();
        String phone = phoneNumber.getText().toString();
        String emailAddress = email.getText().toString();

        if(firstName.equals("") || lastName.equals("") || phone.equals("") || emailAddress.equals("")){

            Toast.makeText(MainActivity.this, "Fields cannot be empty.", Toast.LENGTH_LONG).show();

        } else{ contact.setfName(firstName); contact.setlName(lastName); contact.setPhone(phone); contact.setEmail(emailAddress);

            dba.addContact(contact);
            dba.close();

            fName.setText("");
            lName.setText("");
            phoneNumber.setText("");

            email.setText("");

            startActivity(new Intent(MainActivity.this, ContactListActivity.class));
        }
    }
}

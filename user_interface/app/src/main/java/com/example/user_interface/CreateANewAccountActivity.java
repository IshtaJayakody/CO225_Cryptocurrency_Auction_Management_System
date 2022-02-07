package com.example.user_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateANewAccountActivity extends AppCompatActivity {

    FirebaseDatabase rootNode ; //root node
    DatabaseReference subNode ; // sub nodes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_a_new_account);
    }

    //call when the client click on the button "Create account now"
    public void createAccount(View view) {

        EditText clientNameEditText , clientPasswordEditText , clientPhoneEditText ; //to get the data entered by the client

        rootNode = FirebaseDatabase.getInstance();//calling to the root node of the database
        subNode = rootNode.getReference("clients"); //select the node "clients" in the root node


        clientNameEditText = (EditText) findViewById(R.id.editTextPersonNameId);
        clientPhoneEditText = (EditText) findViewById(R.id.editTextPhoneID) ;
        clientPasswordEditText = (EditText) findViewById(R.id.editTextPasswordID) ;

        //convert to Strings
        String name = clientNameEditText.getText().toString() ;
        String phone = clientPhoneEditText.getText().toString() ;
        String password = clientPasswordEditText.getText().toString();

        //create a client
        ClientObject clientObject = new ClientObject(name , password,phone) ;

        //put into the object into the database subnode
        subNode.child(phone).setValue(clientObject) ;

        //create an intent
        Intent intent = new Intent(this, SelectLoginActivity.class);

        //start the intended activity
        startActivity(intent);
    }

}
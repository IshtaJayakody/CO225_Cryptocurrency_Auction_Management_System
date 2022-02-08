package com.example.user_interface;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignInAsAClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_as_aclient);
    }

    //call when the client click on the button "login as an Administrator"
    public void signInAsAClient(View view){
        checkAndSignIn();
    }


    //to validate the user name
    private boolean validAccountNumber(){
        EditText clientAccountNumberEditText = (EditText) findViewById(R.id.editTextTextAccountNumber);
        String clientAccountNumber = clientAccountNumberEditText.getText().toString() ;

        if(clientAccountNumber.isEmpty()){
            Toast.makeText(SignInAsAClientActivity.this , "Account number cannot be empty !",Toast.LENGTH_SHORT).show();
            return false ;
        }else return true ;

    }

    //to validate the password
    private boolean validPassword(){
        EditText clientPasswordEditText = (EditText) findViewById(R.id.editTextTextPassword2);
        String clientPassword = clientPasswordEditText.getText().toString() ;

        if(clientPassword.isEmpty()){
            Toast.makeText(SignInAsAClientActivity.this , "Password cannot be empty !",Toast.LENGTH_SHORT).show();
            return false ;
        }else return true ;

    }

    //to check whether client exists in the database and log into the account
    private void checkAndSignIn(){
        EditText clientAccountNumberEditText = (EditText) findViewById(R.id.editTextTextAccountNumber);
        String clientAccountNumberEntered = clientAccountNumberEditText.getText().toString() ;

        EditText clientPasswordEditText = (EditText) findViewById(R.id.editTextTextPassword2);
        String clientPasswordEntered = clientPasswordEditText.getText().toString() ;


        if(validAccountNumber() && validPassword()){

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("clients"); //select the client node
            Query checkClient = databaseReference.orderByChild("phoneNumber").equalTo(clientAccountNumberEntered) ;

            checkClient.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        String passwordDatabase = snapshot.child(clientAccountNumberEntered).child("password").getValue(String.class);

                        if(clientPasswordEntered.equals(passwordDatabase)){
                            String clientPassword = snapshot.child(clientAccountNumberEntered).child("password").getValue(String.class);
                            String clientPhone = snapshot.child(clientAccountNumberEntered).child("phoneNumber").getValue(String.class);
                            String clientName = snapshot.child(clientAccountNumberEntered).child("clientName").getValue(String.class);
                            int clientAccountBalance = snapshot.child(clientAccountNumberEntered).child("accountBalance").getValue(Integer.class);

                            //create an intent
                            Intent intent = new Intent(getApplicationContext() , ClientHome.class) ;
                            intent.putExtra("client_name",clientName ) ;
                            intent.putExtra("client_phone",clientPhone ) ;
                            intent.putExtra("client_password",clientPassword ) ;
                            intent.putExtra("client_account_balance",Integer.toString(clientAccountBalance) ) ;

                            Toast.makeText(SignInAsAClientActivity.this , "Welcome !",Toast.LENGTH_SHORT).show();


                            //start the intended activity
                            startActivity(intent) ;

                        }else {
                            Toast.makeText(SignInAsAClientActivity.this , "Wrong password !",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(SignInAsAClientActivity.this , "No client with the given Account Number !",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else return;
    }



}
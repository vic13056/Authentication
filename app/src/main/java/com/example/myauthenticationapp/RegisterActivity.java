package com.example.myauthenticationapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText mfullname,memail,mpassword,mphone;
    Button mregister;
    TextView mlogin;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mfullname = findViewById(R.id.edtfullname);
        memail = findViewById(R.id.edtemail);
        mpassword = findViewById(R.id.edtpassword);
        mphone = findViewById(R.id.edtphone);
        mregister = findViewById(R.id.btnregister);
        mlogin = findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();
        //when the register button is clicked


        //check if the user is already logged in or created an account before

        if (fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get the value(email and password) thats has been entered by the user,both are string
                //convert the values to string

                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();

                //validate the data that has been entered in the email and password
                //check if the data has been entered or not

                if (email.isEmpty()){
                    memail.setError("Enter Email");
                    return;
                }
                if (password.isEmpty()){
                    mpassword.setError("Password Required");
                    return;
                }
                //password length,check if its less than 6 characters(optional)
                if (password.length()<6){
                    mpassword.setError("Password must not be less than six characters ");
                    return;
                }
                //if all this data is passed we start registering the user
                //initiate the progress bar to inform the user we have started the process of registration
                progressBar.setVisibility(View.VISIBLE);
                //now register the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //process of registering a user is called a task
                        if (task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(RegisterActivity.this, "Error Occured" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });


            }
        });

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }
}

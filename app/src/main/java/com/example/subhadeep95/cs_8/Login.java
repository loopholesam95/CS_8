package com.example.subhadeep95.cs_8;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {

    EditText emailText,passText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        emailText = (EditText)findViewById(R.id.email);
        passText = (EditText)findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();
    }

    public void signin(View view)
    {
        String email = emailText.getText().toString();
        String pass = passText.getText().toString();

        if(!validateForm(email,pass)) {
            return;
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(Login.this, "You are have either not registered or your internet is slow.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Login.this,"Welcome to CS8",Toast.LENGTH_SHORT).show();
                        //String name = fetchName();
                        startActivity(new Intent(Login.this,classRoutine.class));
                        finish();
                    }
                }
            });
        }
    }

    public boolean validateForm(String email,String pass)
    {
        if(email.startsWith("14") && email.endsWith("@kiit.ac.in") && pass.length()>7) {
            return true;
        }
        else if(email.startsWith("14") && email.endsWith("@kiit.ac.in")) {
            Toast.makeText(Login.this,"Please enter a password of mimimum 8 characters",Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            Toast.makeText(Login.this,"Please type a valid KIIT email address",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void signup(View view)
    {
        startActivity(new Intent(Login.this,SignUp.class));
        finish();
    }

    public void forgot(View view)
    {
        //startActivity(new Intent(Login.this,.class));
    }

}

package com.example.subhadeep95.cs_8;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText nameText,phoneText,emailText,passText,repassText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        mAuth = FirebaseAuth.getInstance();

        nameText = (EditText)findViewById(R.id.name);
        phoneText = (EditText)findViewById(R.id.phone);
        emailText = (EditText)findViewById(R.id.email);
        passText = (EditText)findViewById(R.id.password);
        repassText = (EditText)findViewById(R.id.repassword);
    }

    public void onClickSignUp(View view)
    {
        String name = nameText.getText().toString();
        String phone = phoneText.getText().toString();
        String email = emailText.getText().toString();
        String pass = passText.getText().toString();
        String repass = repassText.getText().toString();
        if(!validateForm(email,pass,repass)) {
            return;
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(SignUp.this, "You are either already registered or your internet is slow.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(SignUp.this,"Welcome to CS8",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUp.this,Login.class));
                    }
                }
            });
        }
    }

    public boolean validateForm(String email,String pass, String repass)
    {
        if(email.startsWith("14") && email.endsWith("@kiit.ac.in") && pass.length()>7 && pass.equals(repass)) {
            return true;
        }
        else if(email.startsWith("14") && email.endsWith("@kiit.ac.in")  && pass.length()>7) {
            Toast.makeText(SignUp.this,"Password and repassword is not same",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(email.startsWith("14") && email.endsWith("@kiit.ac.in") && pass.equals(repass)) {
            Toast.makeText(SignUp.this,"Please enter a password of mimimum 8 characters",Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            Toast.makeText(SignUp.this,"Please type a valid email address",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}

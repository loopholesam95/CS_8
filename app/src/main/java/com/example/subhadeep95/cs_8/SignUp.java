package com.example.subhadeep95.cs_8;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileOutputStream;

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
        final String name = nameText.getText().toString();
        final String phone = phoneText.getText().toString();
        final String email = emailText.getText().toString().toLowerCase();
        final String pass = passText.getText().toString();
        String repass = repassText.getText().toString();
        if(validateForm(email,pass,repass,phone)) {
            final ProgressDialog progressDialog = ProgressDialog.show(SignUp.this,"Signing Up...","Please Wait",false,false);

            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (!task.isSuccessful()) {
                        Toast.makeText(SignUp.this, "You are either already registered or your internet is slow.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(SignUp.this,"SignUp completed",Toast.LENGTH_SHORT).show();
                        sendData(name,phone,email,pass);
                        savedata(name);
                        startActivity(new Intent(SignUp.this,Login.class));
                    }
                }
            });
        }
    }

    public boolean validateForm(String email,String pass, String repass, String phone)
    {
        if(phone.length()!= 10) {
            Toast.makeText(SignUp.this,"Invalid Phone number",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!email.startsWith("14") && !email.endsWith("@kiit.ac.in"))
        {
            Toast.makeText(SignUp.this,"Please type a valid email address",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(pass.length()<8) {
            Toast.makeText(SignUp.this,"Please enter a password of mimimum 8 characters ",Toast.LENGTH_SHORT).show();
            return false;
        }

        else if(!pass.equals(repass)) {
            Toast.makeText(SignUp.this,"Password and repassword is not same ",Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    public void sendData(String name,String phone,String email,String pass)
    {
        String url = "https://cs-8-cc5a1.firebaseio.com/User/";
        DatabaseReference reference = FirebaseDatabase.getInstance().getReferenceFromUrl(url);
        User_Structure user_structure = new User_Structure();
        user_structure.setName(name);
        user_structure.setPhone(phone);
        user_structure.setEmail(email);
        user_structure.setPass(pass);
        reference.push().setValue(user_structure);
    }

    public void savedata(String name)
    {
        String FILENAME = "name.txt";
        try {
            FileOutputStream fos = getApplication().openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(name.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onCLickSignIn(View view) {
        startActivity(new Intent(SignUp.this,Login.class));
        finish();
    }
}

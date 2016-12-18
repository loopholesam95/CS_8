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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class Login extends AppCompatActivity {

    EditText emailText,passText;
    String name;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        emailText = (EditText)findViewById(R.id.email);
        passText = (EditText)findViewById(R.id.password);

        if(!loadData().equals("")) {
            startActivity(new Intent(Login.this,classRoutine.class));
            finish();
        }
        else
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        mAuth = FirebaseAuth.getInstance();

    }

    public void signin(View view)
    {
        final String email = emailText.getText().toString();
        final String pass = passText.getText().toString();

        if(validateForm(email,pass))
        {
            final ProgressDialog progressDialog = ProgressDialog.show(Login.this,"Signing You In...","Please Wait",false,false);

            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if (!task.isSuccessful()) {
                        Toast.makeText(Login.this, "You are have either not registered or your internet is slow.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Login.this,"Welcome to CS8",Toast.LENGTH_SHORT).show();
                        fetchName(email,pass);
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

    protected String loadData() {
        String FILENAME = "name.txt";
        String out = "";

        try {
            FileInputStream fis1 = getApplication().openFileInput(FILENAME);
            BufferedReader br1 = new BufferedReader(new InputStreamReader(fis1));
            String sLine1;
            while (((sLine1 = br1.readLine()) != null)) {
                out += sLine1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return out;
    }

    public void fetchName(final String email, final String pass)
    {
        String url = "https://cs-8-cc5a1.firebaseio.com/User/";
        DatabaseReference reference = FirebaseDatabase.getInstance().getReferenceFromUrl(url);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    User_Structure user_structure = snapshot.getValue(User_Structure.class);
                    if(user_structure.getEmail().equalsIgnoreCase(email) && user_structure.getPass().equalsIgnoreCase(pass)) {
                        name = user_structure.getName();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        savedata(name);
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
}
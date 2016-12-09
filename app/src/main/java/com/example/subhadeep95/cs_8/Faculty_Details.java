package com.example.subhadeep95.cs_8;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.PopupMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Faculty_Details extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String Name[] = {"Saurabh Bilgaiyan","Abhishek Ray","P.K.Panigrahi","Subhasis Dash","Arup Abhinna Acharya","P.K.Panigrahi","Abhishek Ray"};
    String Email[] = {"saurabhbilgaiyan01@gmail.com",
                      "arayfcs@kiit.ac.in",
                      "prabinprakash1@gmail.com",
                      "sdasfcs@kiit.ac.in",
                      "aacharyafcs@kiit.ac.in",
                      "prabinprakash1@gmail.com",
                      "arayfcs@kiit.ac.in"
                        };
    String Phone[] = {"1112","1234","5678","5467","8907","4536","5555"};
    int Image[] = {R.drawable.saurav,R.drawable.abhi,R.drawable.prabin,R.drawable.dash,R.drawable.arup,R.drawable.prabin,R.drawable.abhi};

    TextView tName,tEmail,tPhone;
    ImageView tPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty__details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tName = (TextView)findViewById(R.id.tName);
        tEmail = (TextView)findViewById(R.id.tEmail);
        tPhone = (TextView)findViewById(R.id.tPhone);
        tPhoto = (ImageView)findViewById(R.id.tPhoto);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
        Spinner spin = (Spinner)findViewById(R.id.spinner1);
        if (spin != null) {
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position!=0)
                    {
                        tName.setText(Name[position-1]);
                        tPhone.setText(Phone[position-1]);
                        tEmail.setText(Email[position-1]);
                        tPhoto.setImageResource(Image[position-1]);
                    }
                    else
                    {
                        tName.setText("");
                        tPhone.setText("");
                        tEmail.setText("");
                        tPhoto.setImageResource(0);
                    }

                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.faculty__details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id== R.id.home){
           startActivity(new Intent(Faculty_Details.this,Latest_details.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_facutly_details)
        {
            startActivity(new Intent(Faculty_Details.this,Faculty_Details.class));
        }
        else if (id == R.id.nav_student_details)
        {
            startActivity(new Intent(Faculty_Details.this,Student_details.class));
        }
        else if (id == R.id.nav_notice)
        {
            startActivity(new Intent(Faculty_Details.this,Notice.class));
        }
        else if (id == R.id.nav_latest_details)
        {
            startActivity(new Intent(Faculty_Details.this,Latest_details.class));
        }
        finish();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

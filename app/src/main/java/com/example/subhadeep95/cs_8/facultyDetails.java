package com.example.subhadeep95.cs_8;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class facultyDetails extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String Name[],Email[],Phone[];
    int Image[] = {R.drawable.saurav,R.drawable.abhi,R.drawable.prabin,R.drawable.dash,R.drawable.arup,R.drawable.prabin,R.drawable.abhi};

    TextView tName,tEmail,tPhone;
    ImageView tPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty__details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Name = getResources().getStringArray(R.array.teacherName);
        Email = getResources().getStringArray(R.array.teacherEmail);
        Phone = getResources().getStringArray(R.array.teacherPhone);

        tName = (TextView)findViewById(R.id.tName);
        tEmail = (TextView)findViewById(R.id.tEmail);
        tPhone = (TextView)findViewById(R.id.tPhone);
        tPhoto = (ImageView)findViewById(R.id.tPhoto);

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
                    if(position!=0) {
                        tName.setText(Name[position-1]);
                        tPhone.setText(Phone[position-1]);
                        tEmail.setText(Email[position-1]);
                        tPhoto.setImageResource(Image[position-1]);
                    }
                    else {
                        Toast.makeText(facultyDetails.this,"Please select the subject",Toast.LENGTH_SHORT).show();
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

         if (id == R.id.nav_student_details)
        {
            startActivity(new Intent(facultyDetails.this,studentDetails.class));
        }
        else if (id == R.id.nav_notice)
        {
            startActivity(new Intent(facultyDetails.this,Notice.class));
        }
        else if (id == R.id.nav_tutor_mentor)
        {
            startActivity(new Intent(facultyDetails.this,tutorMentor.class));
        }

        finish();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

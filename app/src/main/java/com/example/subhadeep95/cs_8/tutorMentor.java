package com.example.subhadeep95.cs_8;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class tutorMentor extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView tmName,tmEmail,tmPhone;
    ImageView tmPhoto;
    int Image[] ={R.drawable.smoh,R.drawable.sray,R.drawable.hpatt,R.drawable.rnramakant};
    private String Name[],Email[],Phone[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_mentor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Name = getResources().getStringArray(R.array.tutorName);
        Email = getResources().getStringArray(R.array.tutorEmail);
        Phone = getResources().getStringArray(R.array.tutorPhone);

        tmName = (TextView)findViewById(R.id.tmName);
        tmEmail = (TextView)findViewById(R.id.tmEmail);
        tmPhone = (TextView)findViewById(R.id.tmPhone);
        tmPhoto = (ImageView)findViewById(R.id.tmPhoto);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Spinner spin = (Spinner)findViewById(R.id.spinner2);
        if (spin != null) {
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position != 0) {
                        tmName.setText(Name[position]);
                        tmPhone.setText(Phone[position - 1]);
                        tmEmail.setText(Email[position - 1]);
                        tmPhoto.setImageResource(Image[position - 1]);
                    } else {
                        Toast.makeText(tutorMentor.this, "Please select the subject", Toast.LENGTH_SHORT).show();
                        tmName.setText("");
                        tmPhone.setText("");
                        tmEmail.setText("");
                        tmPhoto.setImageResource(0);
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
        getMenuInflater().inflate(R.menu.tutor_mentor, menu);
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
        int id = item.getItemId();
        if (id == R.id.nav_facutly_details)
        {
            startActivity(new Intent(tutorMentor.this,facultyDetails.class));
        }
        else if (id == R.id.nav_student_details)
        {
            startActivity(new Intent(tutorMentor.this,studentDetails.class));
        }
        else if (id == R.id.nav_notice)
        {
            startActivity(new Intent(tutorMentor.this,Notice.class));
        }
        finish();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

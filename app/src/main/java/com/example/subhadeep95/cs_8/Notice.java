package com.example.subhadeep95.cs_8;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Notice extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ListView listView;

    private List<listnotice> mProductList;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Notice.this,AddNotice.class));
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView = (ListView)findViewById(R.id.listview_notice);

        mProductList = new ArrayList<>();

        if (isNetworkAvailable()) {
            setNotice();
        } else {
            Toast.makeText(Notice.this, "Internet Not Connected", Toast.LENGTH_SHORT).show();
        }

    }

    public void setNotice()
    {
        final ProgressDialog progressDialog = ProgressDialog.show(Notice.this,"Fetching  Messages...","Please wait",false,false);

        final String url = "https://cs-8-cc5a1.firebaseio.com/Notice/";
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl(url);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                mProductList.clear();
                String name[] = new String[(int)dataSnapshot.getChildrenCount()];
                String date[] = new String[(int)dataSnapshot.getChildrenCount()];
                String message[] = new String[(int)dataSnapshot.getChildrenCount()];
                final String url[] = new String[(int)dataSnapshot.getChildrenCount()];
                int i=0;

                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    listnotice notice_structure = snapshot.getValue(listnotice.class);
                    name[i] = notice_structure.getName();
                    date[i] = notice_structure.getdate();
                    message[i] = notice_structure.getnotice();
                    url[i] = notice_structure.getUrl();
                    mProductList.add(new listnotice(name[i],date[i],message[i],url[i]));
                    i++;
                }

                listViewAdapter notice = new listViewAdapter(Notice.this,mProductList);
                listView.setAdapter(notice);
                listView.setSelection(notice.getCount()-1);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        if(url[i].equals(""))
                        {
                            Toast.makeText(Notice.this,"No image content",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Notice.this,"Wait while fetching image vontent",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Notice.this,imageNoticeShow.class);
                            intent.putExtra("URL",url[i]);
                            startActivity(intent);
                        }
                    }
                });

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        return networkInfo != null && networkInfo.isConnected();
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
        getMenuInflater().inflate(R.menu.notice, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.logout)
        {
            mAuth.signOut();
            savedata("");
            startActivity(new Intent(this,Login.class));
            finish();
            return true;
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_facutly_details)
        {
            startActivity(new Intent(Notice.this,facultyDetails.class));
        }
        else if (id == R.id.nav_student_details)
        {
            startActivity(new Intent(Notice.this,studentDetails.class));
        }

        else if (id == R.id.nav_tutor_mentor)
        {
            startActivity(new Intent(Notice.this,tutorMentor.class));
        }

        finish();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

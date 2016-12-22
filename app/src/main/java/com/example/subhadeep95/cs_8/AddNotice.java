package com.example.subhadeep95.cs_8;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.Map;

public class AddNotice extends AppCompatActivity {

    private static final int PICK_IMAGE_ID = 0;
    private ImageView imageNotice;
    private EditText textNotice;
    Bitmap bitmap;
    int count;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notice);
        
        imageNotice = (ImageView)findViewById(R.id.imageNotice);
        textNotice = (EditText)findViewById(R.id.textNotice);


        String url = "https://cs-8-cc5a1.firebaseio.com/Notice/";
        DatabaseReference reference = FirebaseDatabase.getInstance().getReferenceFromUrl(url);
        Query query = reference.orderByChild("notice");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                count = (int)dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void onClickAddImageNotice(View view) {
        Intent chooseImageIntent = ImagePicker.getPickImageIntent(this);
        startActivityForResult(chooseImageIntent, PICK_IMAGE_ID);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case PICK_IMAGE_ID:
                if(isNetworkAvailable())
                {
                    bitmap = ImagePicker.getImageFromResult(this, resultCode, data);
                    if(bitmap!=null)
                    {
                        imageNotice.setImageBitmap(bitmap);
                    }
                    else
                    {
                        imageNotice.setImageResource(R.drawable.imagepose);
                        Toast.makeText(AddNotice.this, "Operation Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(AddNotice.this,"Internet Not Connected",Toast.LENGTH_SHORT).show();
                }
                break;
            default: super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    public void uploadNotice(View view)
    {
        uploadImage(bitmap);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        return networkInfo != null && networkInfo.isConnected();
    }

    private void uploadImage(final Bitmap bitmap) {

        final String text = textNotice.getText().toString();

        if (bitmap != null) {

            final ProgressDialog loading = ProgressDialog.show(this, "Uploading...", "Please wait...", false, false);
            String UPLOAD_URL = "http://parlorbeacon.com/CS8/uploadpic.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String string) {
                            loading.dismiss();
                            if (!string.equalsIgnoreCase("Error")) {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cs-8-cc5a1.firebaseio.com/Notice/");
                                listnotice listnotice = new listnotice();
                                listnotice.setName(loadData());
                                listnotice.setnotice(text);
                                @SuppressLint("SimpleDateFormat")
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                Calendar calendar = Calendar.getInstance();
                                listnotice.setdate(sdf.format(calendar.getTime()));
                                listnotice.setUrl(string);
                                reference.push().setValue(listnotice);
                                Toast.makeText(AddNotice.this, "Notice Uploaded", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(AddNotice.this,Notice.class));
                                finish();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    loading.dismiss();
                    Toast.makeText(AddNotice.this, volleyError.getMessage(), Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //Converting Bitmap to String
                    String image = getStringImage(bitmap);

                    Map<String, String> params = new Hashtable<>();
                    params.put("image", image);
                    params.put("title",String.valueOf(count+1));

                    return params;
                }
            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                    50000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://cs-8-cc5a1.firebaseio.com/Notice/");
            listnotice listnotice = new listnotice();
            listnotice.setName(loadData());
            listnotice.setnotice(text);
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar calendar = Calendar.getInstance();
            listnotice.setdate(sdf.format(calendar.getTime()));
            listnotice.setUrl("");
            reference.push().setValue(listnotice);
            Toast.makeText(AddNotice.this, "Notice Uploaded", Toast.LENGTH_LONG).show();
            startActivity(new Intent(AddNotice.this,Notice.class));
            finish();
        }

    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
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

}

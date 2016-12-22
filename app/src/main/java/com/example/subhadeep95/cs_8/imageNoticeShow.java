package com.example.subhadeep95.cs_8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class imageNoticeShow extends AppCompatActivity {

    ImageView imageViewNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_notice_show);

        imageViewNotice = (ImageView)findViewById(R.id.imageNoticeSee);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String value = extras.getString("URL");
            Picasso.with(imageNoticeShow.this).load(value).into(imageViewNotice);
        }
    }
}

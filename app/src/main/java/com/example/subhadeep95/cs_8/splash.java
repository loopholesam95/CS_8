package com.example.subhadeep95.cs_8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

       Thread timer = new Thread(){

           public void run()
           {
               try
               {
                   sleep(1000);
                   startActivity(new Intent(splash.this,Login.class));
                   finish();
               }
               catch(InterruptedException e)
               {
                   e.printStackTrace();
               }
           }
       };

        timer.start();

    }
}

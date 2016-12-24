package com.example.subhadeep95.cs_8;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class main_1 extends Fragment {

    ImageView img;
    TextView txt;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.activity_main_1,container,false);

        txt= (TextView)v.findViewById(R.id.day);
        img= (ImageView)v.findViewById(R.id.ttimage);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        switch (day) {
            case Calendar.SUNDAY:
                img.setImageResource(R.drawable.sun);
                txt.setText("SUNDAY");
                break;
            case Calendar.MONDAY:
                img.setImageResource(R.drawable.mon);
                txt.setText("MONDAY");
                break;
            case Calendar.TUESDAY:
                img.setImageResource(R.drawable.tue);
                txt.setText("TUESDAY");
                break;
            case Calendar.WEDNESDAY:
                img.setImageResource(R.drawable.wed);
                txt.setText("WEDNESDAY");
                break;
            case Calendar.THURSDAY:
                img.setImageResource(R.drawable.thu);
                txt.setText("THURSDAY");
                break;
            case Calendar.FRIDAY:
                img.setImageResource(R.drawable.fri);
                txt.setText("FRIDAY");
                break;
            case Calendar.SATURDAY:
               img.setImageResource(R.drawable.sat);
                txt.setText("SATURDAY");
                break;
        }

        return v;
    }
}

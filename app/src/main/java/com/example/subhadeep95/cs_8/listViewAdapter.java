package com.example.subhadeep95.cs_8;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class listViewAdapter extends ArrayAdapter {
    private Activity mContext;
    private List<listnotice> mProductList;

    public listViewAdapter(Activity context, List<listnotice> mProductList) {
        super(context, R.layout.items_notice,mProductList.toArray());
        this.mContext=context;
        this.mProductList=mProductList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View v = inflater.inflate(R.layout.items_notice, null,true);

        TextView tvName = (TextView)v.findViewById(R.id.n_name);
        TextView tvDate = (TextView)v.findViewById(R.id.n_date);

        //Set text to TextView
        tvName.setText(mProductList.get(position).getName());
        tvDate.setText(mProductList.get(position).getdate());

        return v;
    }




}

package com.example.subhadeep95.cs_8;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Subhadeep95 on 11/30/2016.
 */
public class listView_adapter extends BaseAdapter {
    private Context mContext;
    private List<listnotice> mProductList;

    public listView_adapter(Context mContext, List<listnotice> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.items_notice, null);
        TextView tvName = (TextView)v.findViewById(R.id.n_name);
        TextView tvDate = (TextView)v.findViewById(R.id.n_date);

        //Set text for TextView
        tvName.setText(mProductList.get(position).getName());
        tvDate.setText(mProductList.get(position).getdate());

        //Save product id to tag
        v.setTag(mProductList.get(position).getId());

        return v;
    }




}

package com.example.a7invensun.verifydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.MainActivity;
import com.example.a7invensun.verifydemo.util.ListData;

import java.util.List;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class MyActivityAdapter extends BaseAdapter {
    private Context context;
    private List<ListData> listData;
    public MyActivityAdapter(MainActivity mainActivity, List<ListData> listData) {
        this.context = mainActivity;
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,null);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(listData.get(position).getDadaName());
        return view;
    }
}

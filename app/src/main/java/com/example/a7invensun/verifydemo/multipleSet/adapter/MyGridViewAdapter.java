package com.example.a7invensun.verifydemo.multipleSet.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a7invensun.verifydemo.R;

import java.util.List;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class MyGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    public MyGridViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.set_list_item,null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        if (isEditor){
            if(location==position){
                holder.setTypeContent.setSelected(true);
                holder.setTypeContent.setBackgroundResource(R.drawable.set_text_background);
            }
            else {
                holder.setTypeContent.setSelected(false);
                holder.setTypeContent.setBackgroundResource(R.drawable.white_shape);
            }
        }else {
            if(location==position){
                holder.setTypeContent.setSelected(true);
                holder.setTypeContent.setBackgroundResource(R.drawable.set_text_background);
            }
            else {
                holder.setTypeContent.setSelected(false);
                holder.setTypeContent.setBackground(null);
            }
        }
        holder.setTypeContent.setText(mList.get(position));
        return view;
    }

    private int location;
    private boolean isEditor;
    public void setSeclection(int position) {
        location = position;
    }

    public void setEditor(boolean editor){
        isEditor = editor;
    }

    class ViewHolder {
        private TextView setTypeContent;
        public ViewHolder(View view) {
            setTypeContent = view.findViewById(R.id.set_type_content);
        }
    }

}

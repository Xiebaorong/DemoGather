package com.example.a7invensun.verifydemo.multipleSet.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.multipleSet.adapter.MyGridViewAdapter;

import java.util.List;

/**
 * Created by 7invensun on 2018/8/17.
 */

public class MySetView extends LinearLayout {
    private LinearLayout reRoot;
    private GridView setGrid;
    private TextView setTitleText;
    private MyGridViewAdapter adapter;
    public MySetView(Context context) {
        super(context);
    }

    public MySetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 初始化各个控件
     */
    public MySetView init() {
        LayoutInflater.from(getContext()).inflate(R.layout.multiple_set_item, this,true);
        reRoot = findViewById(R.id.re_root);
        setGrid = findViewById(R.id.set_grid);
        setTitleText = findViewById(R.id.set_title);
        return this;
    }

    /**
     * 初始化并添加设置标题
     * @param setTitle
     * @return
     */
    public MySetView init(String setTitle) {
        init();
        setTextTitle(setTitle);
        return this;
    }

    public MySetView setTextTitle(String setTitle) {
        setTitleText.setText(setTitle);
        return this;
    }

    /**
     * 自定义选项
     * @param context
     * @param list
     * @return
     */
    public MySetView setType(Context context,List<String> list){
        adapter = new MyGridViewAdapter(context,list);
        setGrid.setAdapter(adapter);
        return this;
    }

    /**
     * 默认选定设置项
     * @param location
     * @return
     */
    public MySetView initSetType(int location){
        adapter.setSeclection(location);
        adapter.notifyDataSetChanged();
        return this;
    }

    /**
     * 根据选定，改变样式
     * @param isEditor
     */
    public void setEditor(boolean isEditor) {
        adapter.setEditor(isEditor);
        adapter.notifyDataSetChanged();
    }

    /**
     * item点击
     * @param list
     * @param tag
     * @return
     */
    public MySetView setOnGridItemClickListener(List<String> list, final int tag) {
        setGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSeclection(position);
                adapter.notifyDataSetChanged();
            }
        });

        return this;
    }

    /**
     * 取消点击
     */
    public void banClick() {
        setGrid.setOnItemClickListener(null);
    }
}


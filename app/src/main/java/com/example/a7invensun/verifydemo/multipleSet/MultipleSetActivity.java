package com.example.a7invensun.verifydemo.multipleSet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.multipleSet.util.MySetView;

import java.util.ArrayList;
import java.util.List;

public class MultipleSetActivity extends AppCompatActivity {
    public static boolean isMove = true;
    private LinearLayout reRoot;
    private boolean isClick;
    private List<String> soundList;
    private List<String> sizeList;
    private MySetView mySet1,mySet2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_set);
        reRoot = findViewById(R.id.re_root);

        setData();
        mySet1 = new MySetView(this).init("声音").setType(this, soundList).initSetType(1);
        mySet2 = new MySetView(this).init("大小").setType(this, sizeList).setOnGridItemClickListener(sizeList, 2).initSetType(4);
        reRoot.addView(mySet1);
        reRoot.addView(mySet2);

    }

    /**
     * 自定义设置选项
     */
    private void setData() {
        soundList = new ArrayList<>();
        soundList.add("20");
        soundList.add("30");
        sizeList = new ArrayList<>();
        sizeList.add("10");
        sizeList.add("40");
        sizeList.add("90");
    }


    public void setClick(View view){
        isClick = !isClick;
        if (isClick){
            mySet1.setOnGridItemClickListener(soundList, 1);
            mySet2.setOnGridItemClickListener(soundList, 1);
            mySet1.setEditor(true);
            mySet2.setEditor(true);


        }else {
            isMove = false;
            mySet1.setEditor(false);
            mySet2.setEditor(false);
            mySet1.banClick();
            mySet2.banClick();
        }
    }
}
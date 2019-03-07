package com.example.zhongdun.verifydemo.badge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.a7invensun.verifydemo.R;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class BadgeActivity extends AppCompatActivity {
    private static final String TAG = "BadgeActivity";
    LinkedBlockingQueue<PersonInfoModel> queue;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badge);
        imageView = findViewById(R.id.iv_image);
        queue = new LinkedBlockingQueue<>();

    }


    private int index;
    List<PersonInfoModel> list = new ArrayList<>();

    public void getxinxi(View view) throws UnknownHostException, ParseException {
//
//        try {
//            list.add(System.currentTimeMillis() + "");
//            Thread.sleep(100);
//            list.add(System.currentTimeMillis() + "");
//            Thread.sleep(100);
//            list.add(System.currentTimeMillis() + "");
//            Thread.sleep(100);
//            list.add(System.currentTimeMillis() + "");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private static String strPath = Environment.getExternalStorageDirectory().toString();
    public static final String PATH = "/Android_ZD_WT/IDCardProdTest/";
    private static String kernalResourcePath = strPath + PATH;
    public static String strHeadImagePath = kernalResourcePath + "HeadImage.jpg";

    public void duilie(View view) {
        /**
         *排序
         *
         */
//        index++;
//        String time = System.currentTimeMillis() + "";
//        String timeStamp2Date = timeStamp2Date(time);
//        PersonInfoModel infoModel = new PersonInfoModel();
//        infoModel.setNAME("张虎虎" + index);
//        if (index == 1) {
//            infoModel.setENTRYTIME("2019-02-28 10:42:01");
//        } else if (index == 3) {
//            infoModel.setENTRYTIME(timeStamp2Date);
//        }else if (index ==2){
//            infoModel.setENTRYTIME("2019-02-28 11:42:01");
//        }else if (index == 4){
//            infoModel.setENTRYTIME("2019-03-28 11:42:01");
//        }else {
//            infoModel.setENTRYTIME(timeStamp2Date);
//        }
//        list.add(infoModel);
//        Collections.sort(list, new EntryTimeComparator()); // 根据价格排序
//        Collections.reverse(list);


        /**
         * 亮度调整
         */
        Bitmap bitmap = BitmapFactory.decodeFile(strHeadImagePath);
        if (bitmap != null) {
            index++;
            Bitmap bitmap1 = doBrightness(bitmap, 100);
            imageView.setImageBitmap(bitmap1);
        }

    }

    public static Bitmap doBrightness(Bitmap src, int value) {
        // image size
        int width = src.getWidth();
        int height = src.getHeight();
        // create output bitmap
        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
        // color information
        int A, R, G, B;
        int pixel;
        // scan through all pixels
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // get pixel color
                pixel = src.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);

                // increase/decrease each channel
                R += value;
                if (R > 255) {
                    R = 255;
                } else if (R < 0) {
                    R = 0;
                }

                G += value;
                if (G > 255) {
                    G = 255;
                } else if (G < 0) {
                    G = 0;
                }

                B += value;
                if (B > 255) {
                    B = 255;
                } else if (B < 0) {
                    B = 0;
                }
                // apply new pixel color to output bitmap
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        // return final image
        return bmOut;
    }


    class EntryTimeComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            PersonInfoModel p1 = (PersonInfoModel) o1;
            PersonInfoModel p2 = (PersonInfoModel) o2;

            return p1.getENTRYTIME().compareTo(p2.getENTRYTIME());
        }
    }


    public static String timeStamp2Date(String time) {
        Long timeLong = Long.parseLong(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
        Date date;
        try {
            date = sdf.parse(sdf.format(timeLong));
            Log.e(TAG, "timeStamp2Date: " + sdf.format(date));
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.example.zhongdun.verifydemo.colormatrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.a7invensun.verifydemo.R;

public class ColorMatrixActivity extends AppCompatActivity {
    private Bitmap srcBitmap;
    private ImageView dstimage = null;
    private ImageView iv_mageView = null;
    private SeekBar SaturationseekBar = null;
    private SeekBar BrightnessseekBar = null;
    private SeekBar ContrastseekBar = null;
    private int imgHeight, imgWidth;

    private static String strPath = Environment.getExternalStorageDirectory().toString();
    public static final String PATH = "/Android_ZD_WT/IDCardProdTest/";
    private static String kernalResourcePath = strPath + PATH;
    public static String strHeadImagePath = kernalResourcePath + "HeadImage.jpg";

    public static final int PICTURE = 0;
    public static final int MAX_WIDTH = 240;
    public static final int MAX_HEIGHT = 240;
    private static final String TAG = "ColorMatrixActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);

        dstimage = (ImageView) findViewById(R.id.dstImageView);
        iv_mageView = (ImageView) findViewById(R.id.iv_mageView);
        SaturationseekBar = (SeekBar) findViewById(R.id.Saturationseekbar);
        BrightnessseekBar = (SeekBar) findViewById(R.id.Brightnessseekbar);
        ContrastseekBar = (SeekBar) findViewById(R.id.Contrastseekbar);

        srcBitmap = BitmapFactory.decodeFile(strHeadImagePath);
        imgHeight = srcBitmap.getHeight();
        imgWidth = srcBitmap.getWidth();


        Matrix matrix = new Matrix();
        Log.e(TAG, "onCreate: " + srcBitmap.getWidth() + "_____" + srcBitmap.getHeight());
        matrix.setScale(90f / srcBitmap.getWidth(), 70f / srcBitmap.getHeight());
        Bitmap newIdcardBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, true);
        Bitmap Facebitmap = doBrightness(newIdcardBitmap, 100);
        BitmapDrawable bd = new BitmapDrawable(newIdcardBitmap);//接收bitmap

        bd.setAntiAlias(true);//消除锯齿


        AreaAveragingScaleUtils utils = new AreaAveragingScaleUtils(srcBitmap);
        Bitmap scaledBitmap = utils.getScaledBitmap(srcBitmap.getWidth(), srcBitmap.getHeight());


        Rect rect = new Rect(20,20,20,20);


        // 得到新的图片
//        dstimage.setImageBitmap(bitmap);
        iv_mageView.setImageBitmap(scaledBitmap);


        SaturationseekBar
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        // 创建一个相同尺寸的可变的位图区,用于绘制调色后的图片
                        Bitmap bmp = Bitmap.createBitmap(imgWidth, imgHeight,
                                Bitmap.Config.ARGB_8888);
                        ColorMatrix cMatrix = new ColorMatrix();
                        // 设置饱和度
                        cMatrix.setSaturation((float) (progress / 100.0));

                        Paint paint = new Paint();
                        paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));

                        Canvas canvas = new Canvas(bmp);
                        // 在Canvas上绘制一个已经存在的Bitmap。这样，dstBitmap就和srcBitmap一摸一样了
                        canvas.drawBitmap(srcBitmap, 0, 0, paint);

                        dstimage.setImageBitmap(bmp);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

        BrightnessseekBar
                .setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        Bitmap bmp = Bitmap.createBitmap(imgWidth, imgHeight,
                                Bitmap.Config.ARGB_8888);
                        int brightness = progress - 127;
                        ColorMatrix cMatrix = new ColorMatrix();
                        cMatrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1,
                                0, 0, brightness,// 改变亮度
                                0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});

                        Paint paint = new Paint();
                        paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));

                        Canvas canvas = new Canvas(bmp);
                        // 在Canvas上绘制一个已经存在的Bitmap。这样，dstBitmap就和srcBitmap一摸一样了
                        canvas.drawBitmap(srcBitmap, 0, 0, paint);
                        dstimage.setImageBitmap(bmp);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

        ContrastseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Bitmap bmp = Bitmap.createBitmap(imgWidth, imgHeight,
                        Bitmap.Config.ARGB_8888);
                // int brightness = progress - 127;
                float contrast = (float) ((progress + 64) / 128.0);
                ColorMatrix cMatrix = new ColorMatrix();
                cMatrix.set(new float[]{contrast, 0, 0, 0, 0, 0,
                        contrast, 0, 0, 0,// 改变对比度
                        0, 0, contrast, 0, 0, 0, 0, 0, 1, 0});

                Paint paint = new Paint();
                paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));

                Canvas canvas = new Canvas(bmp);
                // 在Canvas上绘制一个已经存在的Bitmap。这样，dstBitmap就和srcBitmap一摸一样了
                canvas.drawBitmap(srcBitmap, 0, 0, paint);

                dstimage.setImageBitmap(bmp);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private Bitmap doBrightness(Bitmap bitmap, int value) {
        Bitmap bmp = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        int brightness = value;
        ColorMatrix cMatrix = new ColorMatrix();
        cMatrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1,
                0, 0, brightness,// 改变亮度
                0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColorFilter(new ColorMatrixColorFilter(cMatrix));

        Canvas canvas = new Canvas(bmp);
        // 在Canvas上绘制一个已经存在的Bitmap。这样，dstBitmap就和srcBitmap一摸一样了
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return bmp;
    }





}

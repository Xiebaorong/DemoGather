package com.example.a7invensun.verifydemo.cameraDemo.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Created by 7invensun on 2018/9/13.
 */

public class ConvertUtil {
    private static final String TAG = "ConvertUtil";
    private static int R = 0;
    private static int G = 1;
    private static int B = 2;
    private static final boolean VERBOSE = false;
    private static final int COLOR_FormatI420 = 1;
    private static final int COLOR_FormatNV21 = 2;
    private static int height;
    private static int width;

    private static class RGB {
        public int r, g, b;
    }

    private static RGB yuvTorgb(byte Y, byte U, byte V) {
        RGB rgb = new RGB();
        rgb.r = (int) ((Y & 0xff) + 1.4075 * ((V & 0xff) - 128));
        rgb.g = (int) ((Y & 0xff) - 0.3455 * ((U & 0xff) - 128) - 0.7169 * ((V & 0xff) - 128));
        rgb.b = (int) ((Y & 0xff) + 1.779 * ((U & 0xff) - 128));
        rgb.r = (rgb.r < 0 ? 0 : rgb.r > 255 ? 255 : rgb.r);
        rgb.g = (rgb.g < 0 ? 0 : rgb.g > 255 ? 255 : rgb.g);
        rgb.b = (rgb.b < 0 ? 0 : rgb.b > 255 ? 255 : rgb.b);
        return rgb;
    }


    /**
     * YUV_420_888 转 NV21
     *
     * @param image
     * @param width
     * @param colorFormat @return
     */
    private static byte[] getDataFromImage(Image image, int width, int colorFormat) {

        Rect crop = image.getCropRect();
        Image.Plane[] planes = image.getPlanes();

        byte[] data = new byte[width * height * 3 / 2];
        byte[] rowData = new byte[planes[0].getRowStride()];

        int channelOffset = 0;
        int outputStride = 1;
        for (int i = 0, leg = planes.length; i < leg; i++) {
            switch (i) {
                case 0:
                    channelOffset = 0;
                    outputStride = 1;
                    break;
                case 1:
                    channelOffset = width * height + 1;
                    outputStride = 2;
                    break;
                case 2:
                    channelOffset = width * height;
                    outputStride = 2;
                    break;

            }
            ByteBuffer buffer = planes[i].getBuffer();
            int rowStride = planes[i].getRowStride();
            int pixelStride = planes[i].getPixelStride();

            int shift = (i == 0) ? 0 : 1;
            int w = width >> shift;
            int h = height >> shift;
            buffer.position(rowStride * (crop.top >> shift) + pixelStride * (crop.left >> shift));
            for (int row = 0; row < h; row++) {
                int length;
                if (pixelStride == 1 && outputStride == 1) {
                    length = w;
                    buffer.get(data, channelOffset, length);
                    channelOffset += length;
                } else {
                    length = (w - 1) * pixelStride + 1;
                    buffer.get(rowData, 0, length);
                    for (int col = 0; col < w; col++) {
                        data[channelOffset] = rowData[col * pixelStride];
                        channelOffset += outputStride;
                    }
                }
                if (row < h - 1) {
                    buffer.position(buffer.position() + rowStride - length);
                }
            }
        }
        image.close();

        return data;
    }

    //NV21是YUV420格式，排列是(Y), (VU)，是2 plane
    private static int[] NV21ToRGB(byte[] src, int width, int height) {
        int numOfPixel = width * height;
        int positionOfV = numOfPixel;
        int[] rgb = new int[numOfPixel * 3];

        for (int i = 0; i < height; i++) {
            int startY = i * width;
            int step = i / 2 * width;
            int startV = positionOfV + step;
            for (int j = 0; j < width; j++) {
                int Y = startY + j;
                int V = startV + j / 2;
                int U = V + 1;
                int index = Y * 3;
                RGB tmp = yuvTorgb(src[Y], src[U], src[V]);
                rgb[index + R] = tmp.r;
                rgb[index + G] = tmp.g;
                rgb[index + B] = tmp.b;
            }
        }
        return rgb;
    }

    static int i;

    public static synchronized void YUV420ToRGB(final Context context, final Image image) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                i++;
                Log.e(TAG, "getDataFromImage: start------" + i + "-----" + System.currentTimeMillis());
                if (width == 0 || height == 0) {
                    Rect rect = image.getCropRect();
                    height = rect.height();
                    width = rect.width();
                }
                byte[] dataFromImage = getDataFromImage(image, width, height);
//                byte[] dataFromImage1 = getDataFromImage(image, 2);
//                NV21ToRGB(dataFromImage, width, height);
                int[] rgb = decodeYUV420SP(dataFromImage, width, height);
                Log.e(TAG, "getDataFromImage: stop------" + i + "-----" + System.currentTimeMillis());
                bytesToImageFile(context,dataFromImage);
//                String fileName = Environment.getExternalStorageDirectory().toString()
//                        + File.separator
//                        + "AppTest"
//                        + File.separator
//                        + "PicTest_" + System.currentTimeMillis() + ".jpg";
//                File file = new File(fileName);
//                if (!file.getParentFile().exists()) {
//                    file.getParentFile().mkdir();//创建文件夹
//                }
//                FileOutputStream outStream = null;
//                try {
//                    Log.e(TAG, "run: 111111111111" );
//                    outStream = new FileOutputStream(file);
////                    byte[] itob = itob(rgb);
////                    bos.write(dataFromImage);
//
//                    //https://blog.csdn.net/sunnyfans/article/details/8286906
//                    YuvImage yuvImage = new YuvImage(dataFromImage, ImageFormat.NV21, width, height, null);
//                    //保存图像
//                    Log.e(TAG, "run: 111222222222222" );
//                    outStream = new FileOutputStream(file);
//                    yuvImage.compressToJpeg(new Rect(0, 0, width, height), 100, outStream);
//                    Log.e(TAG, "run: 333333333333333" );
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }finally {
//                    if (outStream!=null){
//                        try {
//                            outStream.close();
//                        } catch (IOException e) {
//
//                        }
//                    }
//                }
            }

        }).start();

    }

    private static void bytesToImageFile(Context context,byte[] bytes) {
        File file = null;
        try {
            String fileName = Environment.getExternalStorageDirectory().toString()
                    + File.separator
                    + "AppTest"
                    + File.separator
                    + "PicTest_" + System.currentTimeMillis() + ".jpg";
             file = new File(fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();//创建文件夹
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes, 0, bytes.length);
            fos.flush();
            fos.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            MediaScannerConnection.scanFile(context, new String[]{file.getAbsolutePath()}, null, null);
            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(Uri.fromFile(file));    // 需要更新的文件路径
            context.sendBroadcast(intent);
        }
    }

    public static byte[] itob(int[] intarr) {
        int bytelength = intarr.length * 4;//长度
        byte[] bt = new byte[bytelength];//开辟数组
        int curint = 0;
        for (int j = 0, k = 0; j < intarr.length; j++, k += 4) {
            curint = intarr[j];
            bt[k] = (byte) ((curint >> 24) & 0b1111_1111);//右移4位，与1作与运算
            bt[k + 1] = (byte) ((curint >> 16) & 0b1111_1111);
            bt[k + 2] = (byte) ((curint >> 8) & 0b1111_1111);
            bt[k + 3] = (byte) ((curint >> 0) & 0b1111_1111);
        }


        return bt;
    }

    static public int[] decodeYUV420SP(byte[] yuv420sp, int width, int height) {
        final int frameSize = width * height;
        int[] rgb = new int[frameSize];

        for (int j = 0, yp = 0; j < height; j++) {
            int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
            for (int i = 0; i < width; i++, yp++) {
                int y = (0xff & ((int) yuv420sp[yp])) - 16;
                if (y < 0) y = 0;
                if ((i & 1) == 0) {
                    v = (0xff & yuv420sp[uvp++]) - 128;
                    u = (0xff & yuv420sp[uvp++]) - 128;
                }

                int y1192 = 1192 * y;
                int r = (y1192 + 1634 * v);
                int g = (y1192 - 833 * v - 400 * u);
                int b = (y1192 + 2066 * u);

                if (r < 0) r = 0;
                else if (r > 262143) r = 262143;
                if (g < 0) g = 0;
                else if (g > 262143) g = 262143;
                if (b < 0) b = 0;
                else if (b > 262143) b = 262143;

                rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000) | ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
            }
        }

        return rgb;
    }

    private static byte[] getDataFromImage(Image image, int colorFormat) {
        if (colorFormat != COLOR_FormatI420 && colorFormat != COLOR_FormatNV21) {
            throw new IllegalArgumentException("only support COLOR_FormatI420 " + "and COLOR_FormatNV21");
        }

        Rect crop = image.getCropRect();
        int format = image.getFormat();
        int width = crop.width();
        int height = crop.height();
        Image.Plane[] planes = image.getPlanes();
        byte[] data = new byte[width * height * ImageFormat.getBitsPerPixel(format) / 8];
        byte[] rowData = new byte[planes[0].getRowStride()];
        if (VERBOSE) Log.v(TAG, "get data from " + planes.length + " planes");
        int channelOffset = 0;
        int outputStride = 1;
        for (int i = 0; i < planes.length; i++) {
            switch (i) {
                case 0:
                    channelOffset = 0;
                    outputStride = 1;
                    break;
                case 1:
                    if (colorFormat == COLOR_FormatI420) {
                        channelOffset = width * height;
                        outputStride = 1;
                    } else if (colorFormat == COLOR_FormatNV21) {
                        channelOffset = width * height + 1;
                        outputStride = 2;
                    }
                    break;
                case 2:
                    if (colorFormat == COLOR_FormatI420) {
                        channelOffset = (int) (width * height * 1.25);
                        outputStride = 1;
                    } else if (colorFormat == COLOR_FormatNV21) {
                        channelOffset = width * height;
                        outputStride = 2;
                    }
                    break;

            }
            ByteBuffer buffer = planes[i].getBuffer();
            int rowStride = planes[i].getRowStride();
            int pixelStride = planes[i].getPixelStride();
            if (VERBOSE) {
                Log.v(TAG, "pixelStride " + pixelStride);
                Log.v(TAG, "rowStride " + rowStride);
                Log.v(TAG, "width " + width);
                Log.v(TAG, "height " + height);
                Log.v(TAG, "buffer size " + buffer.remaining());
            }
            int shift = (i == 0) ? 0 : 1;
            int w = width >> shift;
            int h = height >> shift;
            buffer.position(rowStride * (crop.top >> shift) + pixelStride * (crop.left >> shift));
            for (int row = 0; row < h; row++) {
                int length;
                if (pixelStride == 1 && outputStride == 1) {
                    length = w;
                    buffer.get(data, channelOffset, length);
                    channelOffset += length;
                } else {
                    length = (w - 1) * pixelStride + 1;
                    buffer.get(rowData, 0, length);
                    for (int col = 0; col < w; col++) {
                        data[channelOffset] = rowData[col * pixelStride];
                        channelOffset += outputStride;
                    }
                }
                if (row < h - 1) {
                    buffer.position(buffer.position() + rowStride - length);
                }
            }
            if (VERBOSE) Log.v(TAG, "Finished reading data from plane " + i);
        }
        return data;
    }

}

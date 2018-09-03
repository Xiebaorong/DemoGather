package com.example.a7invensun.verifydemo.cameraDemo.util;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * BMP文件工具
 */
public class BmpUtil {

    private static final String TAG=BmpUtil.class.getSimpleName();
    /**
     * 获得BMP文件头
     * @param size
     * @return
     */
    private static byte[] addBMPImageHeader(int size) {
        byte[] buffer = new byte[14];
        buffer[0] = 0x42; // BmpFileHeader-bfType
        buffer[1] = 0x4D;
        buffer[2] = (byte) (size >> 0); // BmpFileHeader-bfSize
        buffer[3] = (byte) (size >> 8);
        buffer[4] = (byte) (size >> 16);
        buffer[5] = (byte) (size >> 24);
        buffer[6] = 0x00; // BmpFileHeader-bfReserved1
        buffer[7] = 0x00;
        buffer[8] = 0x00; // BmpFileHeader-bfReserved2
        buffer[9] = 0x00;
        buffer[10] = 0x36; // BmpFileHeader-bfOffBits
        buffer[11] = 0x04;
        buffer[12] = 0x00;
        buffer[13] = 0x00;
        return buffer;
    };

    /**
     * 获得BMP文件信息头
     * @param w
     * @param h
     * @param bitCount
     * @param imageSize
     * @return
     */
    private static byte[] addBMPImageInfosHeader(int w, int h, int bitCount, int imageSize) {
        byte[] buffer = new byte[40];
        buffer[0] = 0x28; // BmpInfoHeader-biSize
        buffer[1] = 0x00;
        buffer[2] = 0x00;
        buffer[3] = 0x00;
        buffer[4] = (byte) (w >> 0); // BmpInfoHeader-biWidth
        buffer[5] = (byte) (w >> 8);
        buffer[6] = (byte) (w >> 16);
        buffer[7] = (byte) (w >> 24);
        buffer[8] = (byte) (h >> 0); // BmpInfoHeader-biHeight
        buffer[9] = (byte) (h >> 8);
        buffer[10] = (byte) (h >> 16);
        buffer[11] = (byte) (h >> 24);
        buffer[12] = 0x01; // BmpInfoHeader-biPlanes
        buffer[13] = 0x00;
        buffer[14] = (byte) (bitCount >> 0);// BmpInfoHeader-biBitCount
        buffer[15] = (byte) (bitCount >> 8);
        buffer[16] = 0x00; // BmpInfoHeader-biCompression
        buffer[17] = 0x00;
        buffer[18] = 0x00;
        buffer[19] = 0x00;
        buffer[20] = (byte) (imageSize >> 0);// BmpInfoHeader-biSizeImage
        buffer[21] = (byte) (imageSize >> 8);
        buffer[22] = (byte) (imageSize >> 16);
        buffer[23] = (byte) (imageSize >> 24);
        buffer[24] = 0x00; // BmpInfoHeader-biXPelsPerMeter
        buffer[25] = 0x00;
        buffer[26] = 0x00;
        buffer[27] = 0x00;
        buffer[28] = 0x00; // BmpInfoHeader-biYPelsPerMeter
        buffer[29] = 0x00;
        buffer[30] = 0x00;
        buffer[31] = 0x00;
        buffer[32] = 0x00; // BmpInfoHeader-biClrUsed
        buffer[33] = 0x00;
        buffer[34] = 0x00;
        buffer[35] = 0x00;
        buffer[36] = 0x00; // BmpInfoHeader-biClrImportant
        buffer[37] = 0x00;
        buffer[38] = 0x00;
        buffer[39] = 0x00;
        return buffer;
    };

    /**
     * 获得BMP文件RGBQUAD
     * @return
     */
    private static byte[] addBMPImageRGBQUAD() {
        byte[] buffer = new byte[4 * 256];
        for (int i = 0; i < 256; ++i) {
            buffer[4 * i] = (byte) i;
            buffer[4 * i + 1] = (byte) i;
            buffer[4 * i + 2] = (byte) i;
            buffer[4 * i + 3] = (byte) 0;
        }
        return buffer;
    };

    /**
     * 保存BMP图像到指定文件
     * @param image
     * @param width
     * @param height
     * @param file
     * @throws FileNotFoundException
     * @throws IOException
     */
    private static void saveImage(byte[] image, int width, int height, File file) throws FileNotFoundException,IOException {
        FileOutputStream out = new FileOutputStream(file);
        int bitCount = 8;
        int lineByte = ((width * bitCount >> 3) + 3) / 4 * 4;
        int bufSize = lineByte * height;
        int rgbQuadSize = 4 * 256;
        int bfSize = 54 + rgbQuadSize + bufSize;
        out.write(addBMPImageHeader(bfSize), 0, 14);
        out.write(addBMPImageInfosHeader(width, -height, bitCount, bufSize), 0, 40);
        out.write(addBMPImageRGBQUAD(), 0, rgbQuadSize);
        out.write(image, 0, image.length);
        out.flush();
        out.close();
    }
    private static void saveTest(byte[] image, int width, int height, File file) throws FileNotFoundException,IOException {
        FileOutputStream out = new FileOutputStream(file);
        out.write(image, 0, width * height);
        out.flush();
        out.close();
    }

    /**
     * 将Bitmap字节流保存成BMP文件存放在外部存储空间的指定目录的指定文件中,自动在文件中加入文件头信息
     * @param context
     * @param image （不包含文件头的）文件流
     * @param width 图像宽度
     * @param height 图像高度
     * @param targetDirName 保存的指定目录名
     * @param targetBmpFileName 保存的指定文件名
     * @throws FileUtil.NoExternalStoragePermissionException 未能获取外部存储卡读写权限异常
     * @throws FileUtil.NoExternalStorageMountedException 外部存储卡未挂载异常
     * @throws FileUtil.DirHasNoFreeSpaceException 目录缺少足够的存储空间异常
     * @throws IOException 文件读写IO异常
     */
    public static void saveImage(Context context, byte[] image, int width, int height, String targetDirName, String targetBmpFileName) throws FileUtil.NoExternalStoragePermissionException, FileUtil.DirHasNoFreeSpaceException, FileUtil.NoExternalStorageMountedException, IOException {
        Log.d(TAG,"to saveImage: "+targetDirName+"/"+targetBmpFileName);
        File targetFile = FileUtil.createFile(context,false,targetDirName,targetBmpFileName,image.length);
        saveImage(image,width,height,targetFile);
    }
}

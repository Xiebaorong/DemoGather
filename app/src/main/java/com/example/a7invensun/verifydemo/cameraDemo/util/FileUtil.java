package com.example.a7invensun.verifydemo.cameraDemo.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件辅助工具，提供注视点标定数据的保存、读取和BMP文件流的封装。
 */
public class FileUtil {

    private static final String TAG = FileUtil.class.getSimpleName();

    /**
     * 无外部存储卡访问权限
     */
    public static class NoExternalStoragePermissionException extends Exception {
        public NoExternalStoragePermissionException(String message) {
            super(message);
        }
    }

    /**
     * 无存储卡挂载异常
     */
    public static class NoExternalStorageMountedException extends Exception {
        public NoExternalStorageMountedException(String message) {
            super(message);
        }
    }

    /**
     * 文件夹无足够空闲空间异常
     */
    public static class DirHasNoFreeSpaceException extends Exception {
        public DirHasNoFreeSpaceException(String message) {
            super(message);
        }
    }

    /**
     * 创建内部私有目录
     * @param context
     * @param dirPathName
     * @return
     */
    public static File createPrivateDir(Context context, String dirPathName){
        File dir = context.getFilesDir();
        File privateDir = new File(dir,dirPathName);
        if(privateDir.exists()){
            if(!privateDir.isDirectory()){
                privateDir.delete();
            }
        }
        privateDir.mkdirs();
        return privateDir;
    }

    /**
     * 在外部存储空间或内部私有空间创建文件夹
     * @param context
     * @param dirName 目录名
     * @return 创建的文件夹
     * @throws NoExternalStoragePermissionException 未能获取外部存储卡读写权限异常
     * @throws NoExternalStorageMountedException 外部存储卡未挂载异常
     */
    public static File createExternalDir(Context context, String dirName) throws NoExternalStoragePermissionException, NoExternalStorageMountedException {
       if(((ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED))){
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File parentDir = Environment.getExternalStorageDirectory();
                File dir = new File(parentDir,dirName);
                if(dir.exists()){
                    if(!dir.isDirectory()){
                        dir.delete();
                    }
                }
                dir.mkdirs();
                return dir;
            }else{

                throw new NoExternalStorageMountedException("外部存储卡未挂载");
            }
        }else{
            throw new NoExternalStoragePermissionException("无法获取对外部存储空间的读写权限，请在Activity中加入获取权限的代码并在AndroidManifest中声明权限");
        }

    }


    /**
     * 在外部存储空间或内部私有空间新建指定文件夹中的指定文件
     * @param context
     * @param dirPath 目录路径名称
     * @param fileName 文件名
     * @param needFreeSpace 需要的空间空间，以字节计
     * @return 创建的文件
     * @throws NoExternalStoragePermissionException 未能获取外部存储卡读写权限异常
     * @throws NoExternalStorageMountedException 外部存储卡未挂载异常
     * @throws DirHasNoFreeSpaceException 目录缺少足够的存储空间异常
     * @throws IOException 文件读写IO异常
     */
    public static File createFile(Context context, boolean isPrivate, String dirPath, String fileName, long needFreeSpace) throws NoExternalStoragePermissionException, NoExternalStorageMountedException, DirHasNoFreeSpaceException, IOException {
        File dir = null;
        if(isPrivate){
            dir = createPrivateDir(context,dirPath);
        }else{
            dir = createExternalDir(context,dirPath);
        }
        long freeSpace = dir.getFreeSpace();
        //需要保留50MB空间
        if(freeSpace>needFreeSpace){
            File targetFile = new File(dir,fileName);
            if(targetFile.exists()){
                targetFile.delete();
            }
            targetFile.createNewFile();
            Log.e(TAG, "createFile:"+ targetFile.getAbsolutePath());
            return targetFile;
        }else{
            throw new DirHasNoFreeSpaceException("目录"+dir.getAbsolutePath()+"缺少足够的空间以保存文件");
        }
    }

    /**
     * 将输入流写入到指定文件
     * @param context
     * @param isPrivate 是否私有
     * @param dirPath 目录路径
     * @param targetFileName 目标文件名
     * @param inputStream 输入流
     * @return 写入完成的文件
     * @throws IOException 文件读写IO异常
     * @throws NoExternalStoragePermissionException 未能获取外部存储卡读写权限异常
     * @throws NoExternalStorageMountedException 外部存储卡未挂载异常
     * @throws DirHasNoFreeSpaceException 目录缺少足够的存储空间异常
     */
    private static File writeInputStreamToFile(Context context, boolean isPrivate, String dirPath,
                                               String targetFileName, InputStream inputStream) throws IOException, NoExternalStoragePermissionException, DirHasNoFreeSpaceException, NoExternalStorageMountedException {
        File targetFile = createFile(context,isPrivate,dirPath,targetFileName,inputStream.available());
        byte[] buffer = new byte[2048];
        int byteCount = 0;
        FileOutputStream out = new FileOutputStream(targetFile);
        while ((byteCount = inputStream.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, byteCount);
        }
        out.flush();
        out.close();
        inputStream.close();
        return targetFile;
    }


    /**
     * 写入字节流到指定目录的指定文件
     * @param context
     * @param isPrivate 是否私有
     * @param dirPath 目录相对当前类型的根目录路径
     * @param targetFileName 目标文件名
     * @return 写入完成的文件
     * @throws NoExternalStoragePermissionException 未能获取外部存储卡读写权限异常
     * @throws NoExternalStorageMountedException 外部存储卡未挂载异常
     * @throws DirHasNoFreeSpaceException 目录缺少足够的存储空间异常
     * @throws IOException 文件读写IO异常
     */
    public static File writeByteDataToTargetFile(Context context, byte[] data, boolean isPrivate, String dirPath,
                                                 String targetFileName) throws NoExternalStoragePermissionException, DirHasNoFreeSpaceException, NoExternalStorageMountedException, IOException {

        InputStream inputStream = new ByteArrayInputStream(data);
        return writeInputStreamToFile(context,isPrivate,dirPath,targetFileName,inputStream);
    }

    /**
     * 读取指定目录的指定文件到字节数组
     * @param context
     * @param isPrivate 是否私有
     * @param dirPath 目录相对当前类型的根目录路径
     * @param targetFileName 目标文件名
     * @return 读取的字节数组
     * @throws IOException IO异常
     * @throws FileNotFoundException 目标文件未找到
     */
    public static byte[] readByteDateFromTargetFile(Context context, boolean isPrivate, String dirPath,
                                                    String targetFileName) throws IOException, NoExternalStoragePermissionException, NoExternalStorageMountedException {
        File dir = null;
        if(isPrivate){
            dir = createPrivateDir(context,dirPath);
        }else{
            dir = createExternalDir(context,dirPath);
        }
        File file = new File(dir, targetFileName);
        if (file.exists()) {
            FileInputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int byteCount = 0;
            while ((byteCount = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, byteCount);
            }
            out.flush();
            out.close();
            inputStream.close();
            byte[] result=out.toByteArray();
            return result;
        }else{
            throw new FileNotFoundException("未找到指定文件");
        }
    }



}

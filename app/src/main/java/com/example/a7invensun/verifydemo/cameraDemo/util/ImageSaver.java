package com.example.a7invensun.verifydemo.cameraDemo.util;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImageSaver {
    private final Context mContext;
//    private String dirName;// = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/dirName");//fileName;
    private Executor mExecutor;

    public ImageSaver(Context context) {
        this.mContext = context;
//        this.dirName = dirName;
        mExecutor = Executors.newFixedThreadPool(10);
    }

    public void save(final String fileNamePrefix, final byte[] image, final int width, final int height, long seq, final String dirName) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                String strBmpName = String.format("%s.bmp", fileNamePrefix);
                try {
                    BmpUtil.saveImage(mContext,image, width, height, dirName, strBmpName);
                } catch (FileUtil.NoExternalStoragePermissionException e) {
                    e.printStackTrace();
                } catch (FileUtil.DirHasNoFreeSpaceException e) {
                    e.printStackTrace();
                } catch (FileUtil.NoExternalStorageMountedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

package com.example.a7invensun.verifydemo.cameraDemo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.a7invensun.verifydemo.R;
import com.example.a7invensun.verifydemo.cameraDemo.util.ConvertUtil;
import com.example.a7invensun.verifydemo.cameraDemo.util.FaceView;
import com.example.a7invensun.verifydemo.cameraDemo.util.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Camera2Activity extends AppCompatActivity {
    private static final String TAG = "Camera2Activity";
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.start_camera)
    Button startCamera;
    @BindView(R.id.image_callback)
    Button imageCallBack;
    @BindView(R.id.texture)
    TextureView myTextureView;
    @BindView(R.id.photograph_button)
    Button photographButton;
    @BindView(R.id.faceView)
    FaceView faceView;
    private int mWidth, mHeight;
    private String mCameraId;
    private CameraDevice mCameraDevice;
    private CaptureRequest.Builder mCaptureRequestBuilder;
    private HandlerThread mCameraThread;
    private Handler mCameraHandler;
    private CameraManager manager;
    private ImageReader mImageReader;//允许应用程序直接访问呈现表面的图像数据
    private Size mCaptureSize, cPixelSize;
    private CaptureRequest mCaptureRequest;
    private CameraCaptureSession mCameraCaptureSession;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    faceView.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    faceView.setVisibility(View.GONE);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2);
        ButterKnife.bind(this);
        faceView.invalidate();
        requestWritePermission();

    }

    @Override
    protected void onResume() {
        super.onResume();
        startCameraThread();
        initCamera();
    }

    private void startCameraThread() {
        mCameraThread = new HandlerThread("CameraThread");
        mCameraThread.start();
        mCameraHandler = new Handler(mCameraThread.getLooper());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initCamera() {
        //获取摄像头的管理者CameraManager
        manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            for (String cameraId : manager.getCameraIdList()) {
                CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraId);
                //默认打开前置摄像头
                if (characteristics.get(CameraCharacteristics.LENS_FACING) == CameraCharacteristics.LENS_FACING_BACK) {
                    continue;//跳过循环体中剩余的语句而强行执行下一次循环
                }
                //获取StreamConfigurationMap，它是管理摄像头支持的所有输出格式和尺寸
                StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                //获取人脸检测参数
//                int[] FD = characteristics.get(CameraCharacteristics.STATISTICS_INFO_AVAILABLE_FACE_DETECT_MODES);
//                int maxFD = characteristics.get(CameraCharacteristics.STATISTICS_INFO_MAX_FACE_COUNT);
//
//                if (FD.length > 0) {
//                    List<Integer> fdList = new ArrayList<>();
//                    for (int FaceD : FD) {
//                        fdList.add(FaceD);
//                        Log.e(TAG, "initCamera: FD type:" + FaceD);
//                    }
//                    Log.e(TAG, "initCamera: FD count" + maxFD);
//
//                    if (maxFD > 0) {
//
//                    }
//                }

                //获取相机支持的最大拍照尺寸

                mCaptureSize = map.getOutputSizes(SurfaceHolder.class)[0];
                cPixelSize = characteristics.get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE);//获取成像尺寸
//                setupImageReader();
                mCameraId = cameraId;
                break;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void setupImageReader() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            mImageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
//                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                @Override
//                public void onImageAvailable(ImageReader reader) {
//
////                    mCameraHandler.post(new imageSaver(reader.acquireNextImage()));
//                }
//            }, mCameraHandler);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mWidth = frameLayout.getWidth();
        mHeight = frameLayout.getHeight();

        Log.d(TAG, "onWindowFocusChanged: width = " + mWidth + "   height = " + mHeight);
    }

    /**
     * 点击开启相机
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.start_camera)
    public void startCameraClick() {
        openCamera();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void openCamera() {
        //检查权限
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            //打开相机，第一个参数指示打开哪个摄像头，第二个参数stateCallback为相机的状态回调接口，第三个参数用来确定Callback在哪个线程执行，为null的话就在当前线程执行
            manager.openCamera(mCameraId, new CameraDevice.StateCallback() {
                @Override
                public void onOpened(@NonNull CameraDevice camera) {
                    Log.e(TAG, "onOpened: 打开相机");
                    mCameraDevice = camera;
                }

                @Override
                public void onDisconnected(@NonNull CameraDevice camera) {

                }

                @Override
                public void onError(@NonNull CameraDevice camera, int error) {
                }
            }, mCameraHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启预览
     */

    @OnClick(R.id.image_callback)
    public void startPreviewClick() {
        startPreview();
    }


    private void startPreview() {
        SurfaceTexture mSurfaceTexture = myTextureView.getSurfaceTexture();
        //设置TextureView的缓冲区大小
        mSurfaceTexture.setDefaultBufferSize(mWidth, mHeight);
        //获取Surface显示预览数据
        final Surface mSurface = new Surface(mSurfaceTexture);
        try {
            //创建CaptureRequestBuilder，TEMPLATE_PREVIEW == 创建一个适合于相机预览窗口的请求
            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            //YUV_420_888 格式保存在CSDN个人博客中
            mImageReader = ImageReader.newInstance(mCaptureSize.getWidth(), mCaptureSize.getHeight(), ImageFormat.YUV_420_888, 2);

            mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, mCameraHandler);
            //设置Surface作为预览数据的显示界面
            mCaptureRequestBuilder.addTarget(mSurface);
            //添加后ImageReader.OnImageAvailableListener 开始响应,
            mCaptureRequestBuilder.addTarget(mImageReader.getSurface());
            // 人脸识别
////            mCaptureRequestBuilder.set(CaptureRequest.STATISTICS_FACE_DETECT_MODE,
////                    CameraMetadata.STATISTICS_FACE_DETECT_MODE_FULL);
            //创建相机捕获会话，第一个参数是捕获数据的输出Surface列表-----获取实时帧时使用 添加mImageReader.getSurface() 显示图像,同时能够保存至文件夹(与mCaptureRequestBuilder.addTarget(mImageReader.getSurface()); 共用)，
            // 第二个参数是CameraCaptureSession的状态回调接口，当它创建好后会回调onConfigured方法，
            // 第三个参数用来确定Callback在哪个线程执行，为null的话就在当前线程执行
//            mCameraDevice.createCaptureSession(Arrays.asList(mSurface), new CameraCaptureSession.StateCallback() {
            mCameraDevice.createCaptureSession(Arrays.asList(mSurface, mImageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(CameraCaptureSession session) {
                    try {
                        //创建捕获请求
                        mCameraCaptureSession = session;
                        mCaptureRequest = mCaptureRequestBuilder.build();
                        //设置反复捕获数据的请求，这样预览界面就会一直有数据显示
                        session.setRepeatingRequest(mCaptureRequest, new CameraCaptureSession.CaptureCallback() {
                            private void FaceProcess(CaptureResult request) {
//                                Face[] faces = request.get(CaptureResult.STATISTICS_FACES);
//                                if (faces.length > 0) {
//                                    handler.sendEmptyMessage(1);
//                                    Log.e(TAG, "process: face detected " + faces.length);
//                                    Rect bounds = faces[0].getBounds();
//                                    float y = cPixelSize.getHeight() / 2 - bounds.top;
//                                    Log.e(TAG, "process:--->height: " + mCaptureSize.getWidth() + "--- top: " + y + "---left: " + bounds.left + "---right: " + bounds.right);
//                                    faceView.setFaceView(bounds,cPixelSize,faces);
//                                    faceView.invalidate();
//                                }else {
//                                    handler.sendEmptyMessage(2);
//                                }


                            }


                            @Override
                            public void onCaptureProgressed(@NonNull CameraCaptureSession
                                                                    session, @NonNull CaptureRequest request, @NonNull CaptureResult
                                                                    partialResult) {

                                FaceProcess(partialResult);
                                Log.e(TAG, "onCaptureProgressed: 1111111");
                            }

                            @Override
                            public void onCaptureCompleted(@NonNull CameraCaptureSession
                                                                   session, @NonNull CaptureRequest
                                                                   request, @NonNull TotalCaptureResult result) {
                                FaceProcess(result);
                                Log.e(TAG, "onCaptureProgressed: 2222222");
                            }
                        }, mCameraHandler);
                    } catch (
                            CameraAccessException e)

                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(CameraCaptureSession session) {
                    Log.e(TAG, "onConfigureFailed:");
                }
            }, mCameraHandler);
        } catch (
                CameraAccessException e)

        {
            e.printStackTrace();
        }
    }


    private ImageReader.OnImageAvailableListener mOnImageAvailableListener
            = new ImageReader.OnImageAvailableListener() {
        @Override
        public void onImageAvailable(final ImageReader reader) {
            Image image = null;
            Log.e(TAG, "onImageAvailable: ---------------------------");
            try {
                image = reader.acquireLatestImage();
                if (image == null) {
                    return;
                }
//                imageSaver(image);F
                Log.e(TAG, "onImageAvailable: 2222222222222");
                ConvertUtil.YUV420ToRGB(getApplicationContext(), image);
            } catch (Exception e) {
                e.printStackTrace();
                if (image != null) {
                    image.close();
                }
            } finally {
            }
        }
    };
    private static final int RESULT_WRITE_PERMISSION = 2;

    /**
     * 可读可写权限判断
     */
    private void requestWritePermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RESULT_WRITE_PERMISSION);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick(R.id.photograph_button)
    public void photographClick() {
        lockFocus();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void lockFocus() {
        try {
            // 对焦
//            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_START);
            mCameraCaptureSession.capture(mCaptureRequestBuilder.build(), mCaptureCallback, mCameraHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private CameraCaptureSession.CaptureCallback mCaptureCallback = new CameraCaptureSession.CaptureCallback() {
        @Override
        public void onCaptureProgressed(CameraCaptureSession session, CaptureRequest request, CaptureResult partialResult) {
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onCaptureCompleted(CameraCaptureSession session, CaptureRequest request, TotalCaptureResult result) {
            takePicture();
        }
    };
    private static final SparseIntArray ORIENTATION = new SparseIntArray();

    static {
        ORIENTATION.append(Surface.ROTATION_0, 0);
        ORIENTATION.append(Surface.ROTATION_90, 90);
        ORIENTATION.append(Surface.ROTATION_180, 270);
        ORIENTATION.append(Surface.ROTATION_270, 180);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void takePicture() {
        try {
            final CaptureRequest.Builder mCaptureBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);

            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            mCaptureBuilder.addTarget(mImageReader.getSurface());
            mCaptureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATION.get(rotation));
            CameraCaptureSession.CaptureCallback captureCallback = new CameraCaptureSession.CaptureCallback() {
                @Override
                public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
                    Toast.makeText(getApplicationContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
                    unLockFocus();
                }
            };
            mCameraCaptureSession.stopRepeating();
            mCameraCaptureSession.capture(mCaptureBuilder.build(), captureCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void unLockFocus() {
        try {
//            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_TRIGGER_START);
            mCameraCaptureSession.setRepeatingRequest(mCaptureRequest, null, mCameraHandler);
            startPreview();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void imageSaver(final Image image) {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                File mImageFile;
                ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);
                FileOutputStream fos = null;
                try {
                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    mImageFile = FileUtil.createFile(getApplicationContext(), false, "CameraV2/", "IMG_" + timeStamp + ".jpg", 1074000000);
                    fos = new FileOutputStream(mImageFile);
                    fos.write(data, 0, data.length);
                } catch (FileUtil.NoExternalStoragePermissionException | FileUtil.NoExternalStorageMountedException | FileUtil.DirHasNoFreeSpaceException | IOException e) {
                    Log.e(TAG, "setupImageReader: " + e.getMessage());
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (image != null) {
                        image.close();
                    }

                }
            }
        }).start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onPause() {
        super.onPause();
        if (mCameraCaptureSession != null) {
            mCameraCaptureSession.close();
            mCameraCaptureSession = null;
        }

        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }

        if (mImageReader != null) {
            mImageReader.close();
            mImageReader = null;
        }
    }

}

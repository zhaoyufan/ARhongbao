package com.example.kyt.arhongbao.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kyt.arhongbao.R;
import com.example.kyt.arhongbao.api.ApiUtil;
import com.example.kyt.arhongbao.api.RxSubscribe;
import com.example.kyt.arhongbao.model.User;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kyt on 2017/2/22.
 */

public class CanghbActivity extends AppCompatActivity implements SurfaceHolder.Callback,SensorEventListener, android.hardware.Camera.PictureCallback{
    private TextView back,title;
    private Button btn_czz;
    private SurfaceView mSurfaceView;
    private android.hardware.Camera camera;
    private Sensor mSensor;
    private SensorManager mSensorManager;
    private Vibrator mVibrator;
    private CameraManager mCameraManager;

    private float[] gravity = new float[3];

    private final int START_SHAKE=0x11;
    private final int START_TAKEPIC=0x12;

    private Bitmap bitmap;
    private static String BASE_PATH = Environment.getExternalStorageDirectory().getPath()+"/";

    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case START_SHAKE:
                    camera.autoFocus(null);
                    mVibrator.vibrate(50);
                    break;
                case START_TAKEPIC:
                    //自动拍照
                    camera.takePicture(null,null,null,CanghbActivity.this);
                    break;
            }
        }
    };


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window=getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_canghb);
        back = (TextView) findViewById(R.id.common_title_bar_left);
        title = (TextView) findViewById(R.id.common_title);
        title.setText("藏红包");
        mVibrator= (Vibrator) getSystemService(VIBRATOR_SERVICE);

        mCameraManager= (CameraManager) getSystemService(CAMERA_SERVICE);

        mSurfaceView= (SurfaceView) findViewById(R.id.canghb_surfview);
        mSurfaceView.getHolder().setFixedSize(1280,960);
        mSurfaceView.getHolder().setFormat(PixelFormat.TRANSPARENT);
        mSurfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mSurfaceView.getHolder().addCallback(this);

        mSensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(mSensorManager==null){
            Toast.makeText(CanghbActivity.this,"设别不支持传感器!",Toast.LENGTH_SHORT).show();
        }
        mSensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_NORMAL);

        btn_czz= (Button) findViewById(R.id.czz);
        btn_czz.setOnClickListener(new View.OnClickListener() {//BTN藏在这
            @Override
            public void onClick(View v) {
                upload();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });

    }
    public void upload() {
        Log.i("aaa", "上传！");
        File file1 = null;
        File file2 = null;
        //String path2 = Environment.getExternalStorageDirectory()+"/img/bb.jpg";

        file1 = new File(BASE_PATH+"abc.jpg");
        file2 = new File(BASE_PATH+"img/aa.jpg");
        final RequestBody requestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        final RequestBody requestBody2 =
                RequestBody.create(MediaType.parse("multipart/form-data"), file2);
        Map<String,RequestBody> maps = new HashMap<String,RequestBody>();
        maps.put("uploadingFiles\"; filename=\"abc.jpg", requestBody);
        maps.put("uploadingFiles\"; filename=\"aa.jpg", requestBody2);
        ApiUtil.createApiService().upload(maps)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscribe<User>() {
                    @Override
                    protected void _onNext(User user) {
                        Log.i("aaa","成功！");
                    }

                    @Override
                    protected void _onError(String message) {
                        Log.i("aaa",message.toString());
                    }
                });

//        Log.d("aaa", "上！！！");
//        ApiUtil.createApiService().uploadfile(requestBody)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new RxSubscribe<User>() {
//                    @Override
//                    protected void _onNext(User user) {
//                        Log.i("aaa","成功！");
//                    }
//
//                    @Override
//                    protected void _onError(String message) {
//                        Log.i("aaa",message.toString());
//                    }
//                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //注册加速度传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),//传感器TYPE类型
                SensorManager.SENSOR_DELAY_UI);//采集频率
        //注册重力传感器
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera= android.hardware.Camera.open();
            camera.setPreviewDisplay(mSurfaceView.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        camera.setDisplayOrientation(90);//旋转90度
        android.hardware.Camera.Parameters parameters = camera.getParameters();//获取参数
        parameters.setPreviewSize(1280, 960); // 设置大小
        camera.setParameters(parameters);
        camera.startPreview();
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (camera != null) {
            camera.stopPreview();
        }
        camera.release();
        camera = null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == null) {
            return;
        }
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            final float alpha = (float) 0.8;
            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

            if ((Math.abs(event.values[0] - gravity[0]) < 0.02 && Math.abs(event.values[1] - gravity[1]) < 0.02 && Math.abs(event.values[2] - gravity[2]) < 0.02)) {
                mSensorManager.unregisterListener(this);
                Toast.makeText(CanghbActivity.this,"静止，拍摄",Toast.LENGTH_SHORT).show();
                Log.d("MM", "没有晃动");
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(2000);
                            //开始震动
                            mHandler.obtainMessage(START_SHAKE).sendToTarget();
                            mHandler.obtainMessage(START_TAKEPIC).sendToTarget();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


    @Override
    public void onPictureTaken(byte[] data, android.hardware.Camera camera) {
        if (data != null) {
            try {
                bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                saveBitmap();
            } catch (Exception e) {
                Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "数据出错啦..", Toast.LENGTH_SHORT).show();
        }
    }
    /** 保存方法 */
    public void saveBitmap() {
        Log.e("aaa", "保存图片");
        File f = new File(BASE_PATH, "abc.jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            Log.i("aa", "已经保存");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

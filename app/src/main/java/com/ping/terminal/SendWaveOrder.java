package com.ping.terminal;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 接收波形界面。
 * Created by Mr.sorrow on 2016/11/6.
 */
public class SendWaveOrder extends Activity {

    private SurfaceHolder holder;
    private Paint paint;
    final int HEIGHT = 630;
    final int WIDTH = 1750;
    final int X_OFFSET = 150;
    private int cx = X_OFFSET;
    private boolean stopState = false;
    private boolean stopThread = false;
    private boolean isFirstRec = true;
    private int count = 0;
    private TextView tv_recCheck;

    int centerY = HEIGHT / 2 + 10;
    int[] y_data = {};
    Timer timer = new Timer();
    TimerTask task = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave);

        final SurfaceView surface = (SurfaceView) findViewById(R.id.sv_wave);
        tv_recCheck = (TextView) findViewById(R.id.tv_recCheck);

        holder = surface.getHolder();
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(10);

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //drawBack(holder);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                drawBack(holder);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                timer.cancel();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {

                while(!stopThread) {
                    byte[] buffer = new byte[1024];
                    BluetoothManager bluetoothManager = BluetoothManager.getManager();
                    int bytes = bluetoothManager.receiveData(buffer);
                    if (bytes > 0) {
                        int[] buf_data = new int[bytes];
                        for (int i = 0; i < bytes; i++) {
                            buf_data[i] = buffer[i];
                        }
                        y_data = MessageUtil.intAppend(y_data,buf_data);
                    }
                }
            }
        }).start();

    }

    @Override
    protected void onResume() {
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        stopThread = true;
        super.onDestroy();
    }

    public void recCheck(View view){
        if(y_data.length==0)
        {
            Toast.makeText(SendWaveOrder.this,"no data,please send again!",Toast.LENGTH_SHORT).show();
        }else {
            count ++;
            if(count > 1){
                isFirstRec = false;
            }
            if(isFirstRec){
                y_data = new int[0];
                tv_recCheck.setText("successful，please send data...");
            }else{
              //
            }
        }
    }

    public void clearWave(View view){
        y_data = new int[0];
        Toast.makeText(SendWaveOrder.this,"cleared",Toast.LENGTH_SHORT).show();
    }

    public void stopWave(View view){
        Button button = (Button) view;
        if(stopState == false){
            stopState = true;
            button.setText("start");
        }else{
            stopState = false;
            button.setText("stop");
        }
    }

    public void show(View view){
        if(y_data.length == 0){
            Toast.makeText(SendWaveOrder.this,"no data!",Toast.LENGTH_SHORT).show();
        }else {
            drawBack(holder);
            cx = X_OFFSET;
            if (task != null) {
                task.cancel();
            }
            task = new TimerTask() {
                @Override
                public void run() {
                    if (stopState == false) {
                        int cy = centerY - y_data[cx - X_OFFSET];
                        Canvas canvas = holder.lockCanvas(new Rect(cx, cy - 2, cx + 2, cy + 2));
                        canvas.drawPoint(cx, cy, paint);
                        cx++;
                        if (cx > WIDTH || (cx-X_OFFSET) > y_data.length - 1) {
                            task.cancel();
                            task = null;
                        }
                        holder.unlockCanvasAndPost(canvas);
                    }
                }
            };
            timer.schedule(task, 0, 5);
        }
    }

    private void drawBack(SurfaceHolder holder){
        Canvas canvas = holder.lockCanvas();
        canvas.drawColor(Color.BLACK);
        Paint p = new Paint();
        p.setColor(Color.WHITE);
        p.setStrokeWidth(4);

        //x轴
        canvas.drawLine(X_OFFSET, centerY, WIDTH, centerY, p);
        //y轴
        canvas.drawLine(X_OFFSET, 55, X_OFFSET, HEIGHT, p);
        //x轴箭头
        canvas.drawLine(WIDTH-30, centerY+15, WIDTH, centerY, p);
        canvas.drawLine(WIDTH-30, centerY-15, WIDTH, centerY, p);
        //y轴箭头
        canvas.drawLine(X_OFFSET, 55, X_OFFSET-15, 85, p);
        canvas.drawLine(X_OFFSET, 55, X_OFFSET+15, 85, p);

        holder.unlockCanvasAndPost(canvas);
        holder.lockCanvas(new Rect(0, 0, 0, 0));
        holder.unlockCanvasAndPost(canvas);
    }
}

package com.ping.terminal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 接收图片界面。
 * Created by Mr.sorrow on 2016/10/12.
 */
public class SendPictureOrder extends Activity{
    private ImageView imageView;
    private Bitmap bitmap;
    private String FILE_NAME = "rec.jpg";
    private String content;
    private boolean stopThread = false;
    private boolean isFirstRec = true;
    private int count = 0;
    private TextView tv_recCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        content = "";
        bitmap = null;

        imageView = (ImageView) findViewById(R.id.iv_picture);
        tv_recCheck = (TextView) findViewById(R.id.tv_recCheck);

        new Thread(new Runnable() {
            @Override
            public void run() {

                while(!stopThread) {
                    byte[] buffer = new byte[1024];
                    BluetoothManager bluetoothManager = BluetoothManager.getManager();
                    int bytes = bluetoothManager.receiveData(buffer);
                    if (bytes > 0) {
                        byte[] buf_data = new byte[bytes];
                        for (int i = 0; i < bytes; i++) {
                            buf_data[i] = buffer[i];
                        }
                        String s = "";
                        s = MessageUtil.byte2StringNoSpace(buf_data);
                        content = content + s;
                    }
                }
            }
        }).start();

    }

    public void read(View view){
        if(content.equals(""))
        {
            Toast.makeText(SendPictureOrder.this,"未接收到数据,请尝试重发！",Toast.LENGTH_SHORT).show();
        }else {
            count ++;
            if(count > 1){
                isFirstRec = false;
            }
            if(isFirstRec){
                content = "";
                mHandler.sendEmptyMessage(1);
                Button button = (Button)view;
                button.setText("接收");
            }else{
                write(SendPictureOrder.this, content);
                bitmap = readJpg(SendPictureOrder.this);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    public void clearFile(View view){
        content = "";
        Toast.makeText(SendPictureOrder.this,"已清除！",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        stopThread = true;
        super.onDestroy();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 1:
                    tv_recCheck.setText("握手校验成功，请继续发送...");
                    break;
                default:
                    break;
            }
        }

    };


    private void write(Context context,String src){
        FileOutputStream out = null;
        try {
            out = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            byte[] bytes = src.getBytes();
            for (int i = 0; i < bytes.length; i += 2)
            {
                out.write(MessageUtil.charToInt(bytes[i]) * 16 + MessageUtil.charToInt(bytes[i + 1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap readJpg(Context context){
        FileInputStream in = null;
        try {
            in = context.openFileInput(FILE_NAME); //获得输入流
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            in.close();
        }
        catch(Exception e){}
        return null;
    }
}

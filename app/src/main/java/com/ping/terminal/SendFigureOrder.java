package com.ping.terminal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 接收数据界面
 * Created by Mr.sorrow on 2016/10/12.
 */
public class SendFigureOrder extends Activity {

    private TextView textView;
    private String message;
    private boolean stopThread = false;
    private boolean state = false;
    private Button button1,button2,button3;
    private int dataStyle = 0,dataStyle1 = 0;
    final String[] nItems = {"字符显示","十六进制显示"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figure);

        message = "";

        textView = (TextView) findViewById(R.id.tv_receiveData);
        button1 = (Button) findViewById(R.id.bt_startOrStop);
        button2 = (Button) findViewById(R.id.bt_clear);
        button3 = (Button) findViewById(R.id.bt_dataStyle);

        //---------开启线程监听是否有数据发送过来----------
        new Thread(new Runnable() {
            @Override
            public void run() {

                while(!stopThread) {
                    byte[] buffer = new byte[2];
                    BluetoothManager bluetoothManager = BluetoothManager.getManager();
                    int bytes = bluetoothManager.receiveData(buffer);
                    if (bytes > 0) {
                        byte[] buf_data = new byte[bytes];
                        for (int i = 0; i < bytes; i++) {
                            buf_data[i] = buffer[i];
                        }
                        String s = "";
                        if(!state){
                            if(dataStyle == 1)
                                s = MessageUtil.byte2String(buf_data);
                            else{
                                s = new String(buf_data);
                            }
                            message = message + s;
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        stopThread = true;
        super.onDestroy();
    }

    public void startOrStop(View view) {
        if(button1.getText().equals("停止")){
            button1.setText("开启");
            state = true;
        }else{
            button1.setText("停止");
            state = false;
        }
    }

    public void clear(View view) {
        textView.setText("");
        message = "";
    }

    public void dataStyle(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("数据格式选择")
                .setSingleChoiceItems(nItems, dataStyle, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //十六进制
                        if(which == 1)
                            dataStyle1 = 1;
                        else
                            dataStyle1 = 0;
                    }
                });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataStyle = 0;
                if(dataStyle1 == 1)
                    dataStyle = 1;

                Toast.makeText(SendFigureOrder.this,"设置成功!",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case 1:
                    textView.setText(message);
                    break;
                default:
                    break;
            }
        }

    };
}

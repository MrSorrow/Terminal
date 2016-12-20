package com.ping.terminal;


import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

    boolean[] settings = {false, false, false, false, false, false};
    final String[] nItems = {"按钮设置", "文本发送",  "图片指令", "数据接收", "波形绘制", "图片显示"};
    SharedPreferences preferences ;
    SharedPreferences.Editor editor ;

    private BluetoothAdapter mBluetoothAdapter;
    private TextView tv_connectState;
    private Button bt_conn;
    private ImageButton[] imageButtons = new ImageButton[6];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButtons[0] = (ImageButton) findViewById(R.id.ibt_sendChar);
        imageButtons[1] = (ImageButton) findViewById(R.id.ibt_sendText);
        imageButtons[2] = (ImageButton) findViewById(R.id.ibt_sendCharImage);
        imageButtons[3] = (ImageButton) findViewById(R.id.ibt_receiveFigure);
        imageButtons[4] = (ImageButton) findViewById(R.id.ibt_receiveWave);
        imageButtons[5] = (ImageButton) findViewById(R.id.ibt_receivePicture);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(!mBluetoothAdapter.isEnabled()){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("打开蓝牙")
                    .setMessage("应用需要使用蓝牙串口，是否同意打开？")
                    .setCancelable(false)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mBluetoothAdapter.enable();
                            Toast.makeText(MainActivity.this, "蓝牙已打开，可以进行设备连接！",Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Toast.makeText(MainActivity.this, "蓝牙未打开，无法进行设备连接！",Toast.LENGTH_SHORT).show();
                        }
                    }).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(100 == resultCode){
            tv_connectState = (TextView) findViewById(R.id.tv_connectState);
            bt_conn = (Button) findViewById(R.id.bt_conn) ;
            String connectState = intent.getExtras().getString("connectState");
            if(!connectState.equals("error")) {
                tv_connectState.setText(connectState);
                bt_conn.setText("断开");
                BluetoothManager.setIsConnect(true);
            }
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }

    //菜单弹出：功能设置，软件介绍
    public void settings(View view){
        final PopupMenu popupMenu = new PopupMenu(this,view);
        getMenuInflater().inflate(R.menu.popup_settingsmenu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_setting:
                        popupMenu.dismiss();
                        set();
                        break;
                    case R.id.item_introduce:
                        popupMenu.dismiss();
                        Intent intent = new Intent(MainActivity.this,IntroduceActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }

    public void scanDevice(View view){
        if(!BluetoothManager.isConnect()) {
            if(!mBluetoothAdapter.isEnabled())
                mBluetoothAdapter.enable();
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, ScanDeviceActivity.class);
            startActivityForResult(intent, 100);
        }else {
            BluetoothManager.setIsConnect(false);
            bt_conn.setText("连接");
            tv_connectState.setText("未连接");
            mBluetoothAdapter.disable();
            Toast.makeText(MainActivity.this,"断开成功！",Toast.LENGTH_SHORT).show();
        }
    }

    public void sendChar(View view){
        if(!BluetoothManager.isConnect()){
            Toast.makeText(MainActivity.this,"请先连接蓝牙设备！",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(MainActivity.this, SendCharOrder.class);
            startActivity(intent);
        }
    }

    public void sendText(View view){
        if(!BluetoothManager.isConnect()){
            Toast.makeText(MainActivity.this,"请先连接蓝牙设备！",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(MainActivity.this, SendTextOrder.class);
            startActivity(intent);
        }
    }

    public void sendCharImage(View view){
        if(!BluetoothManager.isConnect()){
            Toast.makeText(MainActivity.this,"请先连接蓝牙设备！",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(MainActivity.this, SendImageOrder.class);
            startActivity(intent);
        }
    }

    public void receiveFigure(View view){
        if(!BluetoothManager.isConnect()){
            Toast.makeText(MainActivity.this,"请先连接蓝牙设备！",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(MainActivity.this, SendFigureOrder.class);
            startActivity(intent);
        }
    }

    public void receiveWave(View view){
        if(!BluetoothManager.isConnect()){
            Toast.makeText(MainActivity.this,"请先连接蓝牙设备！",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(MainActivity.this, SendWaveOrder.class);
            startActivity(intent);
        }
    }

    public void receivePicture(View view){
        if(!BluetoothManager.isConnect()){
            Toast.makeText(MainActivity.this,"请先连接蓝牙设备！",Toast.LENGTH_SHORT).show();
        }else {
            Intent intent = new Intent(MainActivity.this, SendPictureOrder.class);
            startActivity(intent);
        }
    }

    private void set(){
        preferences = getSharedPreferences("setting",MODE_PRIVATE);
        editor = preferences.edit();

        for(int i = 0; i < 6; i++) {
            settings[i] = false;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("功能设置");
        //  设置多选项
        builder.setMultiChoiceItems(nItems,
                new boolean[]{false,false, false,false, false,false},
                new DialogInterface.OnMultiChoiceClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1, boolean arg2) {
                        // TODO Auto-generated method stub
                        if (arg2) {
                            settings[arg1] = true;
                        }
                        else {
                            settings[arg1] = false;
                        }
                    }
                });
        //  设置确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub

                for(int i = 0; i < 6; i++) {
                    editor.putBoolean("settings"+i,settings[i]);
                    if(settings[i] == true){
                        imageButtons[i].setVisibility(View.VISIBLE);
                    }else{
                        imageButtons[i].setVisibility(View.GONE);
                    }
                }

                editor.commit();
            }
        });
        //  设置取消按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });

        builder.create().show();
    }
}

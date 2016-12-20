package com.ping.terminal;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;


/**
 * 扫描设备界面
 * Created by Mr.sorrow on 2016/9/25.
 */
public class ScanDeviceActivity extends Activity implements OnItemClickListener {

    private ListView mlv;
    public BluetoothManager bluetoothManager = BluetoothManager.getManager();
    private BluetoothAdapter mBluetoothAdapter;
    private ArrayList<BluetoothDevice> mDevices = new ArrayList<>();
    private DeviceAdapter mAdapter;

    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mDevices.add(device);
                mAdapter.notifyDataSetChanged();
                System.out.print(device.getName());
            }else if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)){
                Toast.makeText(getApplicationContext(),"开始扫描",Toast.LENGTH_SHORT).show();
            }else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                //Toast.makeText(getApplicationContext(),"扫描结束",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        mlv = (ListView) findViewById(R.id.lv_device);
        mAdapter = new DeviceAdapter(getApplicationContext(),mDevices);
        mlv.setAdapter(mAdapter);
        mlv.setOnItemClickListener(this);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mBluetoothReceiver,filter);
    }

    public void pairDone(View view){
        mBluetoothAdapter.cancelDiscovery();
        finish();

    }

    public void scanBluetooth(View view){
        mDevices.clear();
        mAdapter.notifyDataSetChanged();
        mBluetoothAdapter.startDiscovery();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        view.setBackgroundColor(getResources().getColor(R.color.blue));
        mBluetoothAdapter.cancelDiscovery();
        BluetoothDevice device = mDevices.get(position);
        conn(device);


    }

    private void conn(final BluetoothDevice device){
        new Thread(){
            @Override
            public void run() {
                try {
                    BluetoothSocket bluetoothSocket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                    bluetoothSocket.connect();
                    bluetoothManager.setOutputStream(bluetoothSocket.getOutputStream());
                    bluetoothManager.setInputStream(bluetoothSocket.getInputStream());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"连接成功",Toast.LENGTH_SHORT).show();

                        }
                    });

                    Intent intent = new Intent(ScanDeviceActivity.this,SendCharOrder.class);
                    intent.putExtra("connectState",device.getName());
                    setResult(100,intent);
                    finish();


                } catch (IOException e) {
                    e.printStackTrace();

                    Intent intent = new Intent();
                    intent.putExtra("connectState","error");
                    setResult(100,intent);
                    finish();
                }
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBluetoothReceiver);
    }
}

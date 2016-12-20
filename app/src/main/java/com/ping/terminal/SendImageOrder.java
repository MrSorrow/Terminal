package com.ping.terminal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Mr.sorrow on 2016/11/5.
 */
public class SendImageOrder extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
    }

    public void imageOrder1(View view){
        BluetoothManager bluetoothManager = BluetoothManager.getManager();
        byte[] bytes = {'o'};
        bluetoothManager.sendData(bytes);
    }

    public void imageOrder2(View view){
        BluetoothManager bluetoothManager = BluetoothManager.getManager();
        byte[] bytes = {'l'};
        bluetoothManager.sendData(bytes);
    }

    public void imageOrder3(View view){
        BluetoothManager bluetoothManager = BluetoothManager.getManager();
        byte[] bytes = {'m'};
        bluetoothManager.sendData(bytes);
    }

    public void imageOrder4(View view){
        BluetoothManager bluetoothManager = BluetoothManager.getManager();
        byte[] bytes = {'e'};
        bluetoothManager.sendData(bytes);
    }

    public void imageOrder5(View view){
        BluetoothManager bluetoothManager = BluetoothManager.getManager();
        byte[] bytes = {'k'};
        bluetoothManager.sendData(bytes);
    }

    public void imageOrder6(View view){
        BluetoothManager bluetoothManager = BluetoothManager.getManager();
        byte[] bytes = {'i'};
        bluetoothManager.sendData(bytes);
    }

    public void imageOrder7(View view){
        BluetoothManager bluetoothManager = BluetoothManager.getManager();
        byte[] bytes = {'g'};
        bluetoothManager.sendData(bytes);
    }

    public void imageOrder8(View view){
        BluetoothManager bluetoothManager = BluetoothManager.getManager();
        byte[] bytes = {'f'};
        bluetoothManager.sendData(bytes);
    }

    public void imageOrder9(View view){
        BluetoothManager bluetoothManager = BluetoothManager.getManager();
        byte[] bytes = {'n'};
        bluetoothManager.sendData(bytes);
    }

    public void imageOrder10(View view){
        BluetoothManager bluetoothManager = BluetoothManager.getManager();
        byte[] bytes = {'j'};
        bluetoothManager.sendData(bytes);
    }

    public void imageOrder11(View view){
        BluetoothManager bluetoothManager = BluetoothManager.getManager();
        byte[] bytes = {'h'};
        bluetoothManager.sendData(bytes);
    }

    public void imageOrder12(View view){
        BluetoothManager bluetoothManager = BluetoothManager.getManager();
        byte[] bytes = {'d'};
        bluetoothManager.sendData(bytes);
    }


}

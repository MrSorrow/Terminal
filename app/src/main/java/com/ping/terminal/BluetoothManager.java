package com.ping.terminal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 蓝牙管理类：用于封装蓝牙的数据io流，以及收发数据的方法。
 * Created by Mr.sorrow on 2016/10/14.
 */
public class BluetoothManager {
    private final static BluetoothManager manager = new BluetoothManager();
    private static boolean isConnect;

    public static BluetoothManager getManager(){
        return manager;
    }

    public static boolean isConnect() {
        return isConnect;
    }

    public static void setIsConnect(boolean isConnect) {
        BluetoothManager.isConnect = isConnect;
    }

    private OutputStream outputStream;
    private InputStream inputStream;

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    //发送字节
    public void sendByte(byte data){
        try {
            byte[] dataByte = {data} ;
            this.outputStream.write(dataByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //发送字符串
    public void sendData(byte[] data){
        try {
            this.outputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendTextData(byte[] byte_1){
        try {
            byte[] byte_2 = {'!'};
            byte[] byte_3 = new byte[byte_1.length+byte_2.length];
            System.arraycopy(byte_1, 0, byte_3, 0, byte_1.length);
            System.arraycopy(byte_2, 0, byte_3, byte_1.length, byte_2.length);
            this.outputStream.write(byte_3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //接收数据
    public int receiveData(byte[] buffer){
        try {
            int x = this.inputStream.read(buffer);
            return x;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void sendMessage(String message){
        byte[] data = message.getBytes();
        sendData(data);
    }

    public void sendTextMessage(String message){
        byte[] data = message.getBytes();
        sendTextData(data);
    }

}

package com.ping.terminal;

/**
 * 数据处理类：用于专门进行数据格式转换。
 * Created by Mr.sorrow on 2016/10/14.
 */
public class MessageUtil {

    private String message;
    private static String hexString="0123456789ABCDEF";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] string2byte(String message)
    {
        byte[] data;
        data = message.getBytes();
        return data;
    }

    public static String byte2String(byte[] bytes){
        StringBuilder sb=new StringBuilder(bytes.length*2);
        for(int i=0;i<bytes.length;i++)
        {
            sb.append(hexString.charAt((bytes[i]&0xf0)>>4));
            sb.append(hexString.charAt((bytes[i]&0x0f)>>0));
            sb.append(" ");
        }
        return sb.toString();
    }

    public static String byte2StringNoSpace(byte[] bytes){
        StringBuilder sb=new StringBuilder(bytes.length*2);
        for(int i=0;i<bytes.length;i++)
        {
            sb.append(hexString.charAt((bytes[i]&0xf0)>>4));
            sb.append(hexString.charAt((bytes[i]&0x0f)>>0));
        }
        return sb.toString();
    }

    public static int[] intAppend(int[] a1,int[] a2){
        int a[] = new int[a1.length+a2.length];
        System.arraycopy(a1, 0, a, 0, a1.length);
        System.arraycopy(a2, 0, a, a1.length, a2.length);
        return a;
    }

    public static int charToInt(byte ch)
    {
        int val = 0;
        if (ch >= 0x30 && ch <= 0x39)
        {
            val = ch - 0x30;
        }
        else if (ch >= 0x41 && ch <= 0x46)
        {
            val = ch - 0x41 + 10;
        }
        return val;
    }
}

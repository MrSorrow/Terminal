package com.ping.terminal;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 此类用于蓝牙设备搜索的Listview显示。
 * Created by Mr.sorrow on 2016/9/27.
 */
public class DeviceAdapter extends BaseAdapter {

    //全部存放BluetoothDevice类型的mDevices集合
    private ArrayList<BluetoothDevice> mDevices;
    private Context mContext;

    public DeviceAdapter(Context context,ArrayList<BluetoothDevice> devices){
        mContext = context;
        mDevices = devices;
    }

    @Override
    public int getCount() {
        return mDevices.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = View.inflate(mContext,R.layout.item,null);
            holder = new ViewHolder();
            holder.mTvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.mTvAddress = (TextView) convertView.findViewById(R.id.tv_address);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        BluetoothDevice device = mDevices.get(position);
        holder.mTvName.setText(device.getName());
        holder.mTvAddress.setText(device.getAddress());
        return convertView;
    }

    class ViewHolder{
        TextView mTvName;
        TextView mTvAddress;

    }
}

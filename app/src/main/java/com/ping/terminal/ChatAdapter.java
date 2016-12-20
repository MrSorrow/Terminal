package com.ping.terminal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 此类用于管理聊天界面的listview加载。
 * Created by Mr.sorrow on 2016/10/24.
 */
public class ChatAdapter extends BaseAdapter {

    ArrayList al;
    Context c;

    public ChatAdapter(Context c,ArrayList al){
        this.c = c;
        this.al = al;
    }

    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        LayoutInflater inflater = LayoutInflater.from(c);

        if(((MessageInfo)al.get(position)).getName().equals("蓝牙")){
            view = inflater.inflate(R.layout.chatlayout_left,null);

            ((TextView)view.findViewById(R.id.tv_time)).setText(((MessageInfo)al.get(position)).getTime());
            ((TextView)view.findViewById(R.id.tv_sender)).setText(((MessageInfo)al.get(position)).getName());
            ((TextView)view.findViewById(R.id.tv_content)).setText(((MessageInfo)al.get(position)).getContent());

            return view;
        }else{
            view = inflater.inflate(R.layout.chatlayout_right,null);

            ((TextView)view.findViewById(R.id.tv_time)).setText(((MessageInfo)al.get(position)).getTime());
            ((TextView)view.findViewById(R.id.tv_sender)).setText(((MessageInfo)al.get(position)).getName());
            ((TextView)view.findViewById(R.id.tv_content)).setText(((MessageInfo)al.get(position)).getContent());

            return view;
        }
    }
}

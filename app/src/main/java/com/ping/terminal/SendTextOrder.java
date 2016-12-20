package com.ping.terminal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 聊天界面。
 * Created by Mr.sorrow on 2016/10/12.
 */
public class SendTextOrder extends Activity {

    private EditText editText;
    private ListView listView;
    private BaseAdapter baseAdapter;
    private ArrayList arrayList;
    private DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        databaseHandler = new DatabaseHandler(this);
        arrayList = databaseHandler.getRecord();

        listView = (ListView) findViewById(R.id.lv_text);
        baseAdapter = new ChatAdapter(this,arrayList);
        listView.setAdapter(baseAdapter);
        listView.setSelection(listView.getBottom());
    }

    public void sendTextOrder(View view){
        editText = (EditText) findViewById(R.id.et_sendText);

        InputMethodManager imm = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);

        String message = editText.getText().toString();

        MessageInfo messageInfo = new MessageInfo(getCurrentTime(),"手机",message);
        arrayList.add(messageInfo);
        databaseHandler.addRecord(messageInfo);
        baseAdapter.notifyDataSetChanged();
        listView.setSelection(listView.getBottom());

        BluetoothManager bluetoothManager = BluetoothManager.getManager();
        bluetoothManager.sendTextMessage(message);

        editText.setText("");

    }

    private String getCurrentTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }
}

package com.ping.terminal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * 此类用于将聊天数据存储在SQLite。
 * Created by Mr.sorrow on 2016/10/25.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Terminal";
    private static final String TABLE_NAME = "chatRecord";
    private static final int VERSION = 1;
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TIME = "time";
    private static final String KEY_CONTENT = "content";

    private static final String CREATE_TABLE = "create table "+TABLE_NAME+"("+KEY_ID+
            " integer primary key autoincrement,"+KEY_NAME+" text not null,"+KEY_TIME+
            " text not null,"+KEY_CONTENT+" text not null);";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addRecord(MessageInfo messageInfo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,messageInfo.getName());
        values.put(KEY_TIME,messageInfo.getTime());
        values.put(KEY_CONTENT,messageInfo.getContent());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public ArrayList<MessageInfo> getRecord(){

        ArrayList<MessageInfo> infoArrayList = new ArrayList<MessageInfo>();
        String selectQuery="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                MessageInfo record = new MessageInfo();
                record.setName(cursor.getString(1));
                record.setTime(cursor.getString(2));
                record.setContent(cursor.getString(3));
                infoArrayList.add(record);
            }while(cursor.moveToNext());
        }

        return infoArrayList;
    }

    public int getRecordCounts(){
        String selectQuery="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        cursor.close();

        return cursor.getCount();
    }
}

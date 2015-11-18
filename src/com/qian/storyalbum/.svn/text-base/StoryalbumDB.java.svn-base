package com.qian.storyalbum;
import android.content.ContentValues;

import android.content.Context;

import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;



public class StoryalbumDB extends SQLiteOpenHelper {

private final static String DATABASE_NAME = "Storyalbum.db";

private final static int DATABASE_VERSION = 1;

private final static String TABLE_NAME = "Storyalbum_table";

public final static String STORYALBUM_ID = "Storyalbum_id";

public final static String STORYALBUM_TITLE = "Storyalbum_title";

public final static String STORYALBUM_FAME = "Storyalbum_fame";

public final static String STORYALBUM_URI = "Storyalbum_uri";



public StoryalbumDB(Context context) {

// TODO Auto-generated constructor stub

super(context, DATABASE_NAME, null, DATABASE_VERSION);

}



// 创建table

@Override

public void onCreate(SQLiteDatabase db) {

String sql = "CREATE TABLE " + TABLE_NAME + " (" + STORYALBUM_ID

+ " INTEGER primary key autoincrement, " + STORYALBUM_TITLE

+ " text, " + STORYALBUM_FAME + " text, "+ STORYALBUM_URI + " text);";

db.execSQL(sql);

}



@Override

public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;

db.execSQL(sql);

onCreate(db);

}



public Cursor select() {

SQLiteDatabase db = this.getReadableDatabase();

Cursor cursor = db

.query(TABLE_NAME, null, null, null, null, null, null);

return cursor;

}



// 增加操作

public long insert(String bookname, String author,String imageuri) {

SQLiteDatabase db = this.getWritableDatabase();

/* ContentValues */

ContentValues cv = new ContentValues();

cv.put(STORYALBUM_TITLE, bookname);

cv.put(STORYALBUM_FAME, author);

cv.put(STORYALBUM_URI, imageuri);

long row = db.insert(TABLE_NAME, null, cv);

return row;

}



// 删除操作

public void delete(int id) {

SQLiteDatabase db = this.getWritableDatabase();

String where = STORYALBUM_ID + " = ?";

String[] whereValue = { Integer.toString(id) };

db.delete(TABLE_NAME, where, whereValue);

}



// 修改操作

public void update(int id, String bookname, String author, String imageuri) {

SQLiteDatabase db = this.getWritableDatabase();

String where = STORYALBUM_ID + " = ?";

String[] whereValue = { Integer.toString(id) };



ContentValues cv = new ContentValues();

cv.put(STORYALBUM_TITLE, bookname);

cv.put(STORYALBUM_FAME, author);

cv.put(STORYALBUM_URI, imageuri);

db.update(TABLE_NAME, cv, where, whereValue);

}

/**
 * 判断某张表是否存在
 * @param tabName 表名
 * @return
 */
public boolean tabIsExist(String tabName){
        boolean result = false;
        if(tabName == null){
                return false;
        }
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
                db = this.getReadableDatabase();

                cursor = db
                .query(TABLE_NAME, null, null, null, null, null, null);
                if(cursor.moveToNext()){
                        int count = cursor.getInt(0);
                        if(count>0){
                                result = true;
                        }
                }
               
        } catch (Exception e) {
                // TODO: handle exception
        }               
        return result;
}

}


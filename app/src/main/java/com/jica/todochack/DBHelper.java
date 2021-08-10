package com.jica.todochack;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "todoong.db";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 데이터베이스가 생성이 될 때 호출
        //데이터베이스 -> 테이블 -> 컬럼 -> 값
        db.execSQL("CREATE TABLE IF NOT EXISTS TodoList (id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT NOT NULL, writeDate Text NOT NULL, checkBox TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    //SELECT 문 (할일 목록들을 조회)
    public ArrayList<TodoItem> getTodoList() {
        ArrayList<TodoItem> todoItems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TodoList ORDER BY writeDate DESC", null);
        if (cursor.getCount() != 0) {
            //조회온 데이터가 있을때 내부 수행
            while (cursor.moveToNext()) {   //다음으로 이동할 데이터가 있을때까지
                int id = cursor.getInt(cursor.getColumnIndex("id"));    // 체크박스, 메뉴버튼 추가?
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String writeDate = cursor.getString(cursor.getColumnIndex("writeDate"));
                String checkBox  = cursor.getString(cursor.getColumnIndex("checkBox"));

                TodoItem todoItem = new TodoItem();
                todoItem.setId(id);
                todoItem.setContent(content);
                todoItem.setWriteDate(writeDate);
                todoItem.setCheckBox(checkBox);


                todoItems.add(todoItem);
            }
        }
        cursor.close();

        return todoItems;
    }

    //INSERT문(할일 목록을 DB에 넣는다.)
    public void InsertTodo(String _content, String _writeDate, String _checkBox) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO TodoList (content, writeDate, checkBox) VALUES('" + _content + "', '" + _writeDate + "', '" + _checkBox + "');");   //JAVA언어가 아니라 SQL명령어
    }

    //UPDATE 문(할일 목록을 수정한다.)
    public void UpdateTodo(String _content, String _writeDate, String _beforeDate, String _checkBox) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE TodoList SET content='" + _content + "', writeDate='" + _writeDate + "', checkBox='" + _checkBox + "' WHERE writeDate='" + _beforeDate + "'");   //id를 이용해서 순서?를 알아봄

    }

    public void UpdateTodo(int id, String _checkBox) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE TodoList SET checkBox='" + _checkBox + "' WHERE  id = " + id );   //id를 이용해서 순서?를 알아봄
    }

    //DELETE 문 (할일 목록을 제거한다.)
    public void DeleteTodo(String _beforeDate) {    //String _beforeDate
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM TodoList WHERE writeDate='" + _beforeDate + "'");
    }

}

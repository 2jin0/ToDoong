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
        db.execSQL("CREATE TABLE IF NOT EXISTS TodoList (id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT NOT NULL, writeData Text NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    //SELECT 문 (할일 목록들을 조회)
    public ArrayList<TodoItem> getTodoList() {
        ArrayList<TodoItem> todoItems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TodoList ORDER BY writeData DESC", null);
        if (cursor.getCount() != 0) {
            //조회온 데이터가 있을때 내부 수행
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String content = cursor.getString(cursor.getColumnIndex("content"));

                TodoItem todoItem = new TodoItem();
                todoItem.setId(id);
                todoItem.setContent(content);
                todoItems.add(todoItem);
            }
        }
        cursor.close();

        return todoItems;
    }

    //INSERT문(할일 목록을 DB에 넣는다.)
    public void InsertTodo(String _content, String _writDate) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO TodoList (content, writDate) VALUES('" + _content + "', '" + _writDate + "');");   //JAVA언어가 아니라 SQL언어
    }

    //UPDATE 문(할일 목록을 수정한다.)
    public void UpdateTodo(String _content, String _writDate, String _beforeDate) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE TodoList SET content='" + _content + "', writDate='" + _writDate + "' WHERE writDate='" + _beforeDate + "'");   //id를 이용해서 순서?를 알아봄

    }

    //DELETE 문 (할일 목록을 제거한다.)
    public void DeleteTodo(String _beforeDate) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM TodoList WHERE writDate='" + _beforeDate + "'");
    }

    //이후의 메서드는 db관련 기능을 지원하기 위한 사용자가 만든 메서드
    public ArrayList<String> queryAllWordTable() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String sql = "SELECT * FROM word";

        Log.d("TAG", "실행sql문장 : " + sql);

        Cursor cursor = null;
        cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.getCount() <= 0) {
            return null;
        }

        ArrayList<String> wordArraryList = new ArrayList<String>();

        while (cursor.moveToNext()) {
            int _id = cursor.getInt(0);        //_id
            String ctodo = cursor.getString(1); //kor

            String message = String.format("%-3d|%-20s|%-20s", _id, ctodo);
            //String message = _id + " | " + ckor + " | " + ceng;

            Log.d("TAG", message);

            wordArraryList.add(message);
        }

        cursor.close();
        sqLiteDatabase.close();

        return wordArraryList;

    }

}
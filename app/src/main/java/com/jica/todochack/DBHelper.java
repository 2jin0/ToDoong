package com.jica.todochack;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

//SQLiteOpenHelper를 상속받은 클래스
public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "toodong.db";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        //첫번째 인자값 contxt : ApplictionContext
        //두번째 인자값 name   : 데이타베이스화일명
        //세번째 인자값 factory : null
        //네번째 인자값 version : 버전(구조가 바뀔때 사용)
        super(context, name, factory, version);
        //위의 코드가 최초로 작동할때는 내부에서 데이타베이스화일이 생성된다.
        //이후 호출시부터는 생성되지 않는다.

        Log.d("TAG", "DBHelper::DBHelper()-->데이타베이스 생성준비(데이타베이스화일이 없으면 생성");
    }

    //테이블을 생성하는 메서드 -- 반드시 재정의해야하는 메서드
    @Override
    public void onCreate(SQLiteDatabase db) {
        //데이타베이스화일이 만들어지고 최초 open시만 호출된다.
        //데이터베이스 -> 테이블 -> 값
        db.execSQL("CREATE TABLE IF NOT EXISTS TodoList (id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT NOT NULL)");

        //sql명령어 작성
        String sql = "CREATE TABLE word( ";
        sql += "_id INTEGER PRIMARY KEY AUTOINCREMENT,";
        sql += "todo TEXT,";
        sql += ");";

        //sql명령어 실행
        db.execSQL(sql);

        Log.d("TAG", "DBOpenHeler::onCreate()--> word 테이블 생성");
    }

    //-- 반드시 재정의해야하는 메서드
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    //SELECT 문 (할일 목록들을 조회)
    public ArrayList<TodoItem> getTodoList() {
        ArrayList<TodoItem> todoItems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TodoList ORDER BY writeData DESC", null);
        if(cursor.getCount() != 0){
            //조회온 데이터가 있을때 내부 수행
            while (cursor.moveToNext()){
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
        db.execSQL("INSERT INTO TodoList (content) VALUES('" + _content + "');");   //JAVA언어가 아니라 SQL언어
    }

    //UPDATE 문(할일 목록을 수정한다.)
    public void UpdateTodo(String _content, String _writDate, int _id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE TodoList SET content='" + _content + "', writDate='" + _writDate + "' WHERE id='" + _id + "'");   //id를 이용해서 순서?를 알아봄

    }

    //DELETE 문 (할일 목록을 제거한다.)
    public void DeleteTodo(int _id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM TodoList WHERE id = '" + _id + "'");
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

    //용도에 따라 다양한 메서드를 여기에 작성해 놓고 호출하여 사용하면
    //코드도 단순해지고 사용하기도 편리하다.
    ArrayList<String> queryKorWord(String todo) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT _id, todo FROM word WHERE todo = '" + todo + "'";
        Cursor cursor = db.rawQuery(sql, null);

        //결과가 여러건일 경우를 대비하여 ArrayList를 준비한다.
        ArrayList<String> result = new ArrayList<String>();
        if (cursor.getCount() == 0) {
            result.add("결과 데이타가 없습니다.");
        } else {
            while (cursor.moveToNext()) {
                int _id = cursor.getInt(0);        //_id
                String ctodo = cursor.getString(1); //kor

                String message = String.format("%-3d|%-20s|%-20s", _id, ctodo);
                result.add(message);
            }
        }

        cursor.close();
        db.close();

        return result;
    }
}
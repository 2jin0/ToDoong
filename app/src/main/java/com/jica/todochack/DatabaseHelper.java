package com.jica.todochack;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String NAME = "todoDB.db";
    public static int VERSION = 1;


    public DatabaseHelper(Context context) {
        //최초로 호출될때 data/data/com.jica.sqlite/databases/employee.db 가 없으면
        //데이타베이스 화일을 생성한다.(아래의 상위클래스 생성자 내부에서)
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //최초로 Database를 getReadableDatabase(),getWriteabelDatabase()로 open할때
        //테이블이 존재하지 않으면 호출된다. 여기서 테이블을 만든다.
        println("onCreate 호출됨");

        String sql = "create table if not exists emp("
                + " _id integer PRIMARY KEY autoincrement, "
                + " name text, "
                + " age integer, "
                + " mobile text)";

        db.execSQL(sql);
    }

    public void onOpen(SQLiteDatabase db) {
        println("onOpen 호출됨");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //버전번호가 달라졋을때 데이타베이스 내부의 테이블구조를 변경시키거나
        //신규생성할때 사용한다.
        println("onUpgrade 호출됨 : " + oldVersion + " -> " + newVersion);

        if (newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS emp");
        }
    }

    public void println(String data) {
        Log.d("DatabaseHelper", data);
    }
}

package com.jica.todochack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener {
    //Database
    SQLiteDatabase sqLiteDatabase;
    DBHelper dbHelper;

    //UI객체
    EditText etAddItem, etTodo_text;
    Button btnUpdate, btnInsert, btnDelete;
    //Button btnQuery;   //검색버튼 - 사용x
    RecyclerView rvTodayList;

    TextView tvTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //xml UI 전개
        setContentView(R.layout.fragment_bottom_sheet);

        //UI객체 찾기
        etAddItem = findViewById(R.id.etAddTodo);

        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        //btnQuery = findViewById(R.id.btnQuery);   //검색버튼 - 사용x

        rvTodayList = findViewById(R.id.rvTodayList);   //메인-할일목록
        tvTodo = findViewById(R.id.tvTodo);
        tvTodo.setText((CharSequence) etTodo_text);

        //4개버튼 클릭시의 이벤트핸들러 설정
        //btnQuery.setOnClickListener(this);    //검색버튼 - 사용x
        btnInsert.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        // row 추가(insert), 수정(update), 삭제(delete)
    }

    //4개의 버튼 클릭시의 이벤트 핸들러 메서드
    @Override
    public void onClick(View view) {

        int curId = view.getId();
        switch (curId) {
/*          
            // 검색기능  
            case R.id.btnQuery :{
                //UI객체에서 입력 한 값 가져오기
                String kor = etAddItem.getText().toString(); //""


                //모든 데이타 가져오기
                //dbHelper객체 생성
                dbHelper = new DBHelper(getApplicationContext(), "dictionary.db", null, 1);

                //dbHelper객체를 사용하여 데이타베이스 open
                sqLiteDatabase = dbHelper.getReadableDatabase();

                Cursor cursor = null;

                //SQL명령 ==> SELECT * FROM word
                Log.d("TAG", "모든 row를 가져오는 기능을 query()메서드 사용하여 실행");
                // sql 명령을 직접 실행할때
                //cursor = sqLiteDatabase.rawQuery("SELECT _id, kor, eng FROM word WHERE SUBSTR(eng, 1, 1) = 'j'", null);

                //query()메서드의 사용법
                //Cursor	query(String table,
                //                String[] columns,
                //                String selection,
                //                String[] selectionArgs,
                //                String groupBy,
                //                String having,
                //                String orderBy)

                
//                cursor = sqLiteDatabase.query("word",
//                                              new String[]{"_id","kor","eng"},
//                                              "SUBSTR(eng, 1, 1) = ?",
//                                                       new String[]{"j"},
//                                               null,
//                                                null,
//                                               null);
                 
                cursor = sqLiteDatabase.query("word",
                                            null,
                                            null,
                                            null,
                                            null,
                                            null,
                                            null);

                //Cursor 0x100 ----> bof
                //                   1 바보팅               BABOTING
                //                   2 자바                java
                //                   3 안드로이드           ANDROID
                //                   5 전주정보문화산업진흥원 JICA
                //                   6 코틀린              kotlien
                //                   7 전주                jeonju
                //                   8 웹프로그래밍          web

                String result = "";
                while(cursor.moveToNext()){
                    int _id = cursor.getInt(0);
                    String ckor = cursor.getString(1);
                    String ceng = cursor.getString(2);
                    result += _id +  "," + ckor +"," + ceng +"\n";
                }

                if(result.length() == 0){
                    result = " 1건의 row도 존재하지 않습니다.";
                }
                tvResult.setText(result);

                //close
                cursor.close();
                sqLiteDatabase.close();
                dbHelper.close();
                break;

            }
*/
            //데이터 추가

            //다이얼로그에 있는 삽입버튼을 클릭
            ButtonSubmit.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    //사용자 입력값 가져오기
                    String strID = editTextID.getText().toString();
                    String strTodo = editTextTodo.getText().toString();

                    //ArrayList에 추가
                    Dictionary dict = new Dictionary(strID, strEnglish, strKorean );
                    mArrayList.add(0, dict); //첫번째 줄에 삽입됨
                    //mArrayList.add(dict); //마지막 줄에 삽입됨

                    // 6. 어댑터에서 RecyclerView에 반영하도록 합니다.
                    mAdapter.notifyItemInserted(0);
                    //mAdapter.notifyDataSetChanged();

                    dialog.dismiss(); //다이얼로그 종료

                }
            })

            case R.id.btnInsert: {
                //UI객체에서 입력한 값 가져오기
                String todo = etAddItem.getText().toString();//"데이타베이스"
                if (todo == null || todo.length() <= 0) {   //필요없는 기능인가?
                    Toast.makeText(getApplicationContext(), "할 일을 입력하세요", Toast.LENGTH_SHORT).show();
                    break;
                }

                //dbHelper객체 생성
                dbHelper = new DBHelper(getApplicationContext(), "todoDB.db", null, 1);

                //dbHelper객체를 사용하여 데이타베이스 open
                sqLiteDatabase = dbHelper.getWritableDatabase();

                //SQL문장 ==> INSERT INTO word VALUES(null,'이이','leelee');
                //sqLiteDatabase.execSQL("INSERT INTO word VALUES(null,'데이타베이스','database')");

                //SQL의 insert문을 직접사용하는 대신에 insert()메서드를 사용한다.
                // long	insert(String table, String nullColumnHack, ContentValues values);
                Log.d("TAG", "insert()메서드 실행");

                ContentValues row = new ContentValues();
                row.put("todo", todo);

                long newId = sqLiteDatabase.insert("word", null, row);

                //close
                sqLiteDatabase.close();
                dbHelper.close();

                if (newId != -1) {
                    rvTodayList.add(new PhRecyclerItem(R.drawable.emoji_u1f605, "emoji_u1f605 " + mItemList.size()));
                    // List 반영
                    rvTodayList.notifyDataSetChanged();

                    //rvTodayList.setText(todo);
                } else {
                    rvTodayList.setText("내부적인 이유로 row를 추가하지 못했습니다.");
                }

                //다음 작업을 위해 UI입력객체의 값 reset
                etAddItem.setText("");

                break;
            }
            case R.id.btnUpdate: {
                //한글단어가 일치하는 row의 영어단어를 변경하자
                //UI객체에서 입력 한 값 가져오기
                String todo = etAddItem.getText().toString(); //"안드로이드"

                if (todo.length() == 0) {
                    Toast.makeText(getApplicationContext(), "수정하려는 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHelper = new DBHelper(getApplicationContext(), "dictionary.db", null, 1);
                sqLiteDatabase = dbHelper.getWritableDatabase();

                //SQL명령==> UPDATE word SET eng='ANDROID UPPER' WHERE kor = '안드로이드';

                //update()메서드 사용
                //int	update(String table, ContentValues values, String whereClause, String[] whereArgs)

                ContentValues row = new ContentValues();
                row.put("todo", todo);

                Log.d("TAG", "update()메서드를 사용하여 row 수정");

                String whereClause = "todo = ?";
                String whereArgs[] = new String[1];
                whereArgs[0] = todo;

                int count = sqLiteDatabase.update("word",
                        row,
                        "todo = ?",
                        new String[]{""});

                rvTodayList.setText(count + " 개의 row를 수정했습니다.");
                //close
                sqLiteDatabase.close();
                dbHelper.close();

                //UI초기화
                etAddItem.setText("");

                break;
            }
            case R.id.btnDelete: {
                //일치하는 한글단어를 찿아 해당 row를 제거 => 선택한 항목 수정기능으로 변경

                //UI객체에서 입력 한 값 가져오기
                String todo = etAddItem.getText().toString(); //"웹프로그램"

                if (todo.length() == 0) {
                    Toast.makeText(getApplicationContext(), "삭제하려는 한글단어를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHelper = new DBHelper(getApplicationContext(), "dictionary.db", null, 1);
                sqLiteDatabase = dbHelper.getWritableDatabase();

                //SQL명령 ==>DELETE FROM word WHERE kor = '웹프로그램';
                //delete()메서드 사용
                //int	delete(String table, String whereClause, String[] whereArgs)

                Log.d("TAG", "delete()메서드를 사용하여 row 삭제");

                //int count = sqLiteDatabase.delete("word", "kor = '"+ kor + "'", null);
                String whereArg[] = new String[1];
                whereArg[0] = "웹프로그램";
                int count = sqLiteDatabase.delete("word", "todo = ?", whereArg);
                //          sqLiteDatabase.delete("word", "kor = ?", new String[]{"프로젝트"});
                rvTodayList.setText(count + " 개의 row를 삭제했습니다.");

                //close
                sqLiteDatabase.close();
                dbHelper.close();

                //UI초기화
                etAddItem.setText("");

                break;
            }
        }
    }

    //SQLiteOpenHelper를 상속받은 클래스
    class DBHelper extends SQLiteOpenHelper {

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

        //테이블을 생성하는 메서드
        @Override
        public void onCreate(SQLiteDatabase db) {
            //데이타베이스파일이 만들어지고 최초 open시만 호출된다.

            //sql명령어 작성
            String sql = "CREATE TABLE word( ";
            sql += "_id INTEGER PRIMARY KEY AUTOINCREMENT,";
            sql += "todo TEXT,";
            sql += ");";

            //sql명령어 실행
            db.execSQL(sql);

            Log.d("TAG", "DBOpenHeler::onCreate()--> word 테이블 생성");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}

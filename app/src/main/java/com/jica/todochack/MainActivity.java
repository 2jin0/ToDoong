package com.jica.todochack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    //전역변수

    //Database
    private DBHelper mDBHelper;
    private CustomAdapter mAdapter;

    //UI객체
    private Button mBtnAddList, btnCalender;
    private RecyclerView mRvTodayList;
    //private TodoAdapter adapter;
    private ArrayList<TodoItem> mTodoItems;
    private CheckBox todo_done;

    private EditText etAddItem, etTodo_text;
    //TextView tvResult;

    //Button ivTodoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInit();
    }

    private void setInit() {
        mDBHelper = new DBHelper(this);
        mRvTodayList = findViewById(R.id.rvTodayList);
        mBtnAddList = findViewById(R.id.btnAddList);
        mTodoItems = new ArrayList<>();

        //load recent Database
        loadRecentDB();

        //+버튼 클릭시 할일목록 작성창
        mBtnAddList = findViewById(R.id.btnAddList);
        mBtnAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bottomSheetDialog 팝업창 띄우기
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        MainActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.layout_bottom_sheet,
                                (ConstraintLayout) findViewById(R.id.bottomSheetContainer));
                //int curPos = getAdapterPosition();  //현재 리스트 클릭한 아이템 위치
                //TodoItem todoItem = mTodoItems.get(curPos);

                //UI객체 찾기
                final Button btnOk_bs = bottomSheetView.findViewById(R.id.btnOk_bs);
                final Button btnCancel_bs = bottomSheetView.findViewById(R.id.btnCancel_bs);
                final EditText etAddTodo = bottomSheetView.findViewById(R.id.etAddTodo);

                //v버튼 클릭시 할일 등록
                bottomSheetView.findViewById(R.id.btnOk_bs).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Insert Database
                        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());    //현재 시간 연월일시분초 받아오기

                        Log.d("TextTextTextTextTextTextTextTextText", mDBHelper.toString());
                        mDBHelper.InsertTodo(etAddTodo.getText().toString().trim(), currentTime);

                        //Insert UI
                        TodoItem item = new TodoItem();
                        item.setContent(etAddTodo.getText().toString());

                        mAdapter.addItem(item);
                        mRvTodayList.smoothScrollToPosition(0);
                        //dialog.dicmiss();
                        Toast.makeText(MainActivity.this, "할일 목록에 추가 되었습니다.", Toast.LENGTH_SHORT).show();
                        bottomSheetDialog.dismiss();
                    }
                });

                //x버튼 클릭시 bottomSheetDialog 종료
                bottomSheetView.findViewById(R.id.btnCancel_bs).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();

            }
        });

    }

    private void loadRecentDB() {
        //저장되어있던 DB를 가져온다.
        mTodoItems = mDBHelper.getTodoList();
        if (mAdapter == null) {
            mAdapter = new CustomAdapter(mTodoItems, this);
            mRvTodayList.setHasFixedSize(true);
            mRvTodayList.setAdapter(mAdapter);
        }
    }


}
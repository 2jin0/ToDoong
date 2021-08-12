package com.jica.todochack;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.datepicker.OnSelectionChangedListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jica.todochack.Decorator.EventDecorator;
import com.jica.todochack.Decorator.SaturdayDecorator;
import com.jica.todochack.Decorator.SundayDecorator;

import com.jica.todochack.Decorator.TodayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import org.jetbrains.annotations.NotNull;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

@SuppressLint("RestrictedApi")
public class CalendarActivity extends AppCompatActivity implements  OnDateSelectedListener{

    //Database
    private DBHelper mDBHelper;
    private CustomAdapter mAdapter;

    //UI객체
    MaterialCalendarView mcv;
    String curDate;
    private TextView tvDate;

    private Button mBtnAddListCal;

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
        setContentView(R.layout.activity_calendar);

        mcv = findViewById(R.id.mcv);
        mcv.setOnDateChangedListener(this);
        tvDate = findViewById(R.id.textView);
        mDBHelper = new DBHelper(this);
        mRvTodayList = findViewById(R.id.rvTodayList);
        mBtnAddListCal = findViewById(R.id.btnAddList);

        mTodoItems = new ArrayList<>();

        FloatingActionButton fab_back = (FloatingActionButton) findViewById(R.id.fab_back);
        fab_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(); // 인텐트 객체 생성하고
                finish(); // 현재 액티비티 없애기
            }

        });

        mcv.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new TodayDecorator(this)
        );




        //String[] result = {"2021,08,01", "2021,09,21", "2021,07,25", "2021,08,05"};
        //new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());
        new ApiSimulator().executeOnExecutor(Executors.newSingleThreadExecutor());


        //load recent Database
        loadRecentDB();

        //+버튼 클릭시 할일목록 작성창
        mBtnAddListCal = findViewById(R.id.btnAddListCal);
        mBtnAddListCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bottomSheetDialog 팝업창 띄우기
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        CalendarActivity.this, R.style.BottomSheetDialogTheme
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
                        Log.d("TAG", "선택한 날짜 확인 : " + curDate);  //달력에서 날짜선택시 지정된다.


                        //할일이 추가될때는 수행여부는 false으로 저장
                        mDBHelper.InsertTodo(etAddTodo.getText().toString().trim(), curDate, "false");

                        //Insert UI
                        TodoItem item = new TodoItem();
                        item.setContent(etAddTodo.getText().toString());

                        mAdapter.addItem(item);
                        mRvTodayList.smoothScrollToPosition(0);

                        Toast.makeText(CalendarActivity.this, "할일 목록에 추가 되었습니다.", Toast.LENGTH_SHORT).show();

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
        //저장되어있던 DB의 전체데리타를 리사이클러뷰에 보여준다.
        mTodoItems = mDBHelper.getTodoList();
        if (mAdapter == null) {
            //최초 실행시만 어탭터를 새로만들어서 읽어온 데이타 설정
            mAdapter = new CustomAdapter(this, mTodoItems, this);
            mRvTodayList.setHasFixedSize(true);
            mRvTodayList.setAdapter(mAdapter);
        }else{
            //기존데이타를 제거하고 새로 읽어온데이타로 설정
            mAdapter.addItems(mTodoItems);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void loadRecentDBDate(String curDate) {
        //저장되어있던 DB의 선택날짜의 데이타를 리사이클러뷰에 보여준다.
        mTodoItems = mDBHelper.getTodoList(curDate);
        if (mAdapter == null) {
            mAdapter = new CustomAdapter(this, mTodoItems, this);
            mRvTodayList.setHasFixedSize(true);
            mRvTodayList.setAdapter(mAdapter);
        }else{
            mAdapter.addItems(mTodoItems);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDateSelected(@NonNull @NotNull MaterialCalendarView widget, @NonNull @NotNull CalendarDay date, boolean selected) {
        Log.d("TAG", "선택여부: " + selected);
       Log.d("TAG", "날짜값: " + date.toString());
        curDate = new SimpleDateFormat("yyyy-MM-dd").format(date.getCalendar().getTime());
        Log.d("TAG", "변경한 날짜값: " + curDate);
        loadRecentDBDate(curDate);
    }



      class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {

            //데이타베이스에서 SELECT문으로 --- 특정 writeDate값의 모든 checkBox가 true항목만 구해오는 SELECT을 작성항 실행
            ArrayList<String> compleateDate = mDBHelper.loadCompleateDate();

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            for( int i=0; i<compleateDate.size(); i++){
                String sDate = compleateDate.get(i);
                String[] time = sDate.split("-");

                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                calendar.set(year, month - 1, dayy);
                CalendarDay day = CalendarDay.from(calendar);
                dates.add(day);
            }

            return dates;

            // 1 데이터베이스에 저장이 되어 있는 경우
            // 2 일자별
            // 3 체크박스의 개수와 체크박스가 ture인 개수가 같은 경우?
            // SELECT count(checkBox) FROM Todolist 와
            // true

            //쿼리를 실행하여 얻어진 writeDate를 아래의 형식으로 작성하여 리턴해 준다.
            /*


             */

            }

            @Override
            protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
                super.onPostExecute(calendarDays);
                Log.d("TAG", "할일완료 날짜 : " + calendarDays.toString());

                if (isFinishing()) {
                    return;
                }

                mcv.addDecorator(new EventDecorator(getColor(R.color.i), calendarDays, CalendarActivity.this));

            }


        }

}
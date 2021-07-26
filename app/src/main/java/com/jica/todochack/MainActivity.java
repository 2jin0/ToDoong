package com.jica.todochack;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnAddList;
    Button btnCalender;
    Button ivTodoMenu;

    RecyclerView rvTodayList;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //+버튼 클릭시 할 일 추가 바텀씨트 호출
        btnAddList = findViewById(R.id.btnAddList);
        btnAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetFragment bottomSheet = new BottomSheetFragment();
                bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                Log.d("TAG", "+버튼 클릭합니다.");

                //키보드 자동 띄우기
                InputMethodManager imManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //error: 인자가 두개 필요하대????
                imManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

            }
        });


    }

    //다이얼로그 호출할 버튼 누르는 setOnClickListener 필요하지않나? 왜 오류나지?
    //===> 시험해보려면 데이터가 필요함 ==> SQLite부터 해야함
    /*
        //...(메뉴)버튼 클릭시 doalog 호출
        ivTodoMenu = findViewById(R.id.ivTodoMenu);
        ivTodoMenu.setOnClickListener(new View.OnClickListener() {
        }
     */
        public void custom_dialog(View view) {
                //Dialog - 투두리스트 수정/삭제
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_menu, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setView(dialogView);

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                //수정버튼 클릭시 fragment_bottom_sheet 출력
                Button btnModify_dialog = dialogView.findViewById(R.id.btnModify_dialog);
                btnModify_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View dialogView = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet, null);    //수정페이지 출력
                        alertDialog.dismiss();  //다이얼로그 닫기
                    }
                });

                //삭제버튼 클릭시 해당 체크리스트 삭제
                Button btnDel_dialog = dialogView.findViewById(R.id.btnCancel_dialog);
                btnDel_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();  //다이얼로그 닫기
                    }
                });

                //x버튼 클릭시 다이얼로그 창에서 나가기
                Button btnCancle_dialog = dialogView.findViewById(R.id.btnDelete_dialog);
                btnCancle_dialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();  //다이얼로그 닫기
                    }
                });
            }

            /*
            //모달상자 - 사용안함
            @Override
            public void onClick(View v) {
                AlertDialog.Builder oDialog = new AlertDialog.Builder(this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog);  //두번째 인자에 테마 지정

                oDialog.setMessage("할 일")
                        .setTitle("일반 Dialog")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Log.i("Dialog", "취소");
                                Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_LONG).show();
                            }
                        }).setNeutralButton("수정, new DialogInterface.OnClickListener()") {
                    public void onClick (DialogInterface dialog,int which){
                        m_oMainActivity.finish();
                    }
                }).show();
                //.setCancelable(false) // 백버튼으로 팝업창이 닫히지 않도록 한다.
            }

             */







/*
    //뷰모델 받아오기
    rvTodayList =

    ViewModelProvider(this,new HasDefaultViewModelProviderFactory(this.getApplication())

    {
        @NonNull
        @org.jetbrains.annotations.NotNull
        @Override
        public ViewModelProvider.Factory getDefaultViewModelProviderFactory () {
        return null;
    }
    }
                .

    get(TodoAdapter));

    //캘린더버튼 클릭시 캘린더 페이지로 이동
    btnCalender =

    findViewById(R.id.btnCalender);
        btnCalender.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){

    }
    });
 */

}
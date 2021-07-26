package com.jica.todochack;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

import static androidx.core.content.ContextCompat.getSystemService;

public class BottomSheetFragment extends BottomSheetDialogFragment {
    Button btnCancel_bs;
    private ListView myList;
    private ListAdapter todoListAdapter;
    //private TodoListSQLHelper todoListSQLHelper;
    Bundle bundle;
    //Todo todo;

    EditText etAddItem;
    ConstraintLayout cl;
    //키보드 자동띄움

/*
    private EditText etAddItem;
    //에딧텍스트 페이지에서 키보드 같이 올라오기
    etAddItem = (EditText)findViewById(R.id.etAddItem);
    etAddItem.requestFocus();
*/


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setStyle(STYLE_NORMAL, R.style.CustomBottomSheet);

    }


/*
    private void hideKeyboard(){

        InputMethodManager imManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //인자가 두개 필요하대
        imManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

    }

 */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        etAddItem = view.findViewById(R.id.etAddItem);

       //x버튼 클릭시 fragment_bottom_sheet 종료
        //Fragment에서 findViewById를 입력하면 오류가 난다.
        //Fragment의 view가 inflate하기전에 컴포넌트를 호출하기 때문이다
        //https://brightmango.tistory.com/39
        btnCancel_bs = view.findViewById(R.id.btnCancel_bs);
        btnCancel_bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        etAddItem.requestFocus();
        etAddItem.setCursorVisible(true);
        etAddItem.selectAll();
    }
}


/*
    //x버튼 클릭시 bottomSheetFragment종료
    // Fragment에서는 onClick을 사용할 수 없기때문에,  별도로 리스너를 달아서 클릭이벤트를 지정한다.
    Button btnCancel_bs = View.findViewById(R.id.btnCancel_bs);
    Button btnOk_bs = View.findViewById(R.id.btnOk_bs);
    btnCancel_bs.setOnClickListener(this);
    btnOk_bs.setOnClickListener(this);

    // 버튼 클릭 이벤트 리스너
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //출근버튼
            case R.id.btnCancel_bs:
                //할일추가 종료
                dismiss();
                break;
            //할일 추가 완료
            case R.id.btnOk_bs:
                //퇴근 관련 로직
                Toast.makeText(getContext(), "할 일 추가 완료", Toast.LENGTH_SHORT).show();
                break;
        }
    }

 */

/*
    //https://alaveiw.tistory.com/133 - 키보드 보이게
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        return view;

        etAddItem = findViewById(R.id.etAddItem);
        etAddItem.requestFocus();

        //키보드 올리기
        inputMethodManager imm = (inputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toddleSoftInput(inputMethodManager.SHOW_FORCED, inputMethodManager.HIDE_IMPLCIT_ONLY);

        //키보드 내리기
        inputMethodManager imm = (inputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(randomNumberEditText, InputMethodManager.SHOW_IMPLICIT);



        // 버튼 리스너 설정
        saveButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // '확인' 버튼 클릭시
                // ...코드..
                Toast.makeText(mContext,et_text.getText().toString(), Toast.LENGTH_SHORT).show();
                // Custom Dialog 종료
                dismiss();
            }
        });
        cancelButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // '취소' 버튼 클릭시
                // ...코드..
                // Custom Dialog 종료
                dismiss();
            }
        });
    }
 */



package com.jica.todochack;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MenuDialogFragment extends DialogFragment {
    Button btnCancel_dialog;
    Button btnModify_dialog;
    Button btnDelete_dialog;
    TextView tvTodoList_dialog;

    EditText etAddItem;
    ConstraintLayout cl_dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_dialog, container, false);

        etAddItem = view.findViewById(R.id.etAddItem);

        //x버튼 클릭시 fragment_menu_dialog 종료
        //Fragment에서 findViewById를 입력하면 오류가 난다.
        //Fragment의 view가 inflate하기전에 컴포넌트를 호출하기 때문이다
        //https://brightmango.tistory.com/39
        btnCancel_dialog = view.findViewById(R.id.btnCancel_bs);
        btnCancel_dialog.setOnClickListener(new View.OnClickListener() {
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
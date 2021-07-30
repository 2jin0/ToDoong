package com.jica.todochack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

public class ItemTodoActivity extends AppCompatActivity {
    Button ivTodoMenu;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //+버튼 클릭시 할 일 추가 바텀씨트 호출
        ivTodoMenu = findViewById(R.id.ivTodoMenu);
        ivTodoMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogFragment = new DialogFragment();
                dialogFragment.show(getSupportFragmentManager(), dialogFragment.getTag());
                Log.d("TAG", "...버튼 클릭합니다.");
            }
        });
    }

}
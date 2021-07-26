package com.jica.todochack;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static android.graphics.Paint.STRIKE_THRU_TEXT_FLAG;

public class MyAdapter {
    Context context;
    ArrayList itemList;
/*

    public void onCreateViewHolder(RecyclerView.Adapter<MyAdapter.MyViewHolder>()) {
        Context inflater = LayoutInflater.from(parent.context);
        View view = inflater.inflate(R.layout.item_todo,parent,false);

        return MyViewHolder(view);

    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        //position번째에 해당하는 Todo객체 얻기
        int todo = itemList(position);

        holder.todoText.text = todo.text;

        //todo객체의 isDone을 CheckBox의 isChecked에 set해준다.
        holder.todoIsDone.isChecked = todo.isDone;

        //todo가 완료(done)된 상태라면 todo_text의 글자섹 변경
        if(todo.isDone){
            holder.todoText.apply{
                setTextColor(Color.GRAY);
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG;
                setTypeface(null, Typeface.ITALIC);
            }
        } else {
            // 완료상태가 아니라면 글자색 복구, 취소선 없앰
            holder.todoText.apply {
                setTextColor(Color.BLACK);
                paintFlags = 0;
                setTypeface(null, Typeface.NORMAL);
            }
        }

        // CheckBox인 todoIsDone이 클릭되었을 때
        holder.todoIsDone.apply {
            setOnClickListener {
                todo.isDone = this.isChecked
                viewModel.update(todo)
                //변경이 완료되었으므로 다시 todoList를 받아온 후
                // liveData.value에 넣어주는 setList메서드 실행
                setList()
            }
        }



        }
    }

 */

}

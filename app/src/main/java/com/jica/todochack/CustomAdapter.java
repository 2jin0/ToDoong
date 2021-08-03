package com.jica.todochack;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//adapter는 main의 list와 연동
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<TodoItem> mTodoItems;
    private Context mContext;
    private DBHelper mDBHelper;

    public CustomAdapter(ArrayList<TodoItem> mTodoItems, Context mContext) {
        this.mTodoItems = mTodoItems;
        this.mContext = mContext;
        mDBHelper = new DBHelper(mContext);
    }

    @NonNull
    @Override//item_todo의 ViewHolder연동
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false);

        return new ViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTodoText.setText(mTodoItems.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return mTodoItems.size();
    }

    //item_todo객체 전개?
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTodoText;
        private ImageView ivTodoMenu;
        private CheckBox todo_done;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTodoText = itemView.findViewById(R.id.tvTodoText);
            ivTodoMenu = itemView.findViewById(R.id.ivTodoMenu);
            todo_done = itemView.findViewById(R.id.todo_done);

            //RecyclerView의 ...버큰 클릭시
            ivTodoMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int curPos = getAdapterPosition();  //현재 리스트아이템 위치
                    TodoItem todoItem = mTodoItems.get(curPos); //현재 리스트아이템 클릭한 위치에 해당하는 ArrayList값 가져옴

                    //팝업창 띄우기 -- menuDialog띄우기
                    Dialog dialogMenu = new Dialog(mContext);
                    dialogMenu.setContentView(R.layout.dialog_menu);
                    Log.d("TAG", "...버튼 클릭합니다.");

                    //x버튼 클릭시 - dialog종료
                    Button btnCancel;
                    btnCancel = dialogMenu.findViewById(R.id.btnCancel_dialog);
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogMenu.dismiss();
                        }
                    });


                }
            });
        }
    }
}

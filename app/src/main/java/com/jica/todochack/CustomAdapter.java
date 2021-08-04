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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        private EditText etAddTodo;

        //dialog_menu
        private TextView tvTodo;
        private Button btnUpdate, btnDelete;

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
                    
                    //할일 추가란-바텀시트에 있는 에딧텍스트 -> 리사이클러뷰의 할일내용으로
                    EditText et_AddTodo = dialogMenu.findViewById(R.id.tvTodoText);

                    //x버튼 클릭시 - dialog종료
                    Button btnCancel;
                    btnCancel = dialogMenu.findViewById(R.id.btnCancel_dialog);
                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogMenu.dismiss();
                        }
                    });

                    //수정버튼 클릭시 - 할일 수정
                    Button btnUpdate;
                    btnUpdate = dialogMenu.findViewById(R.id.btnUpdate);
                    btnUpdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //dialog_menu 종료
                            dialogMenu.dismiss();

                            //팝업창-bottomSheet
                            BottomSheetFragment bottomSheet = new BottomSheetFragment();
                            bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
                            Log.d("TAG", "+버튼 클릭합니다.");

                            //할일 목록 텍스트 가져오기
                            et_AddTodo.setText((todoItem.getContent()));

                            //커서 맨 뒤로 이동
                            et_AddTodo.setSelection(et_AddTodo.getText().length());

                            // update database
                            String content = et_AddTodo.getText().toString();
                            String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());    //현재시간: 연월일시분초 받아오기
                            String beforeTime = todoItem.getWriteDate();

                            mDBHelper.UpdateTodo(content, currentTime, beforeTime);

                            //update UI
                            todoItem.setContent(content);
                            todoItem.setWriteDate(currentTime);
                            notifyItemChanged(curPos, todoItem);
                            Toast.makeText(mContext, "목록 수정이 완료 되었습니다.", Toast.LENGTH_SHORT).show();

                        }
                    });

                    //삭제 버튼 클릭시 - 할일 삭제
                    Button btnDelete;
                    btnDelete = dialogMenu.findViewById(R.id.btnDelete);
                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // delete table
                            String beforeTime = todoItem.getWriteDate();
                            mDBHelper.deleteTodo(beforeTime);
                            // delete UI
                            mTodoItems.remove(curPos);
                            notifyItemRemoved(curPos);
                            Toast.makeText(mContext, "목록이 제거 되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //builder.show();

                }
            });
        }
    }

    //액티비티에서 호출되는 함수이며, 현재 어댑터에 새로운 게시글 아이템을 전달받아 추가하는 목적
    public void addItem(TodoItem _item) {
        mTodoItems.add(0, _item);   //항상 최신 데이터가 0번째에 더해짐
        notifyItemInserted(0);    //notify 들어간거는 새로고침이라 생각하면 된다.

    }
}

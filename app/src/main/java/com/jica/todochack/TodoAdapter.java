package com.jica.todochack;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder>
        implements OnTodoItemClickListener {

    //달라진 부분 - implements OnPersonItemClickListener
//            항목뷰 클릭시 동작할 이벤트 핸들러의 xxxListener를 사용자가 직접 인터페이스로 만들고
//            이를 구현하였다.//3개의 추상메서드를 가진다.
    //원본데이터
    ArrayList<Todo> items = new ArrayList<Todo>();

    //항목뷰가 클릭되었을때 인식하는 Listener 멤버변수
    OnTodoItemClickListener listener;

    public TodoAdapter() {
        Log.d("TAG", "PersonAdapter::PersonAdapter()...");
    }

    @NonNull
    //아래의 두 메서드를 반드시 재정의 해줘야 한다.
    @Override                               //null이 아님을 보장한다.
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Log.d("TAG", "PersonAdapter::onCreateViewHolder(,)...");
        //xml로 디자인 한 UI파일(person_item.xml)의 내용을 메모리에 개체로 만들려면
        //LauoutInflter 객체가 필요하다.
        //LayoutInflater객체를 얻은 방법은 여러가지이다.
        //우리가 학습한 방법
        //LayoutInflater inflater = ViewGroup.getContext().getSustemService(Context.LAYOUT_INFLATER_SERVICE);
        //다른방법
        //LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        //항목뷰를 생성한다.(xml 디자인파일 ---> 객체 )
        View itemView = inflater.inflate(R.layout.item_todo, viewGroup, false);   //아래의 코드와 같은 코드(하위형 객체를 상위형 객체로 가르킬 수 있다는 점을 이용)
        //LinearLayout linearLayout = (LinearLayout)inflater.inflate(R.layout.person_item, viewGroup, false);

        //전개 된 항목뷰를 직접 리턴하지 않고 반드시 ViewHolder객체로 만들어서 리턴해야한다.
        //주의) 항목뷰 내부의 내용이 아직 지정되지 않았다.
        return new ViewHolder(itemView, listener);
    }

    @Override   //ViewHolder를 만들어만! 달라는 메서드
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //항목뷰의 내용을 지정하는 기능을 수행한다.
        //position번째의 데이터를 가져와서 인자로 전달 된 viewHoder객체의 UI객체에
        //값을 저장해서 리턴하면 된다.
        Log.d("TAG", "TodoAdapter::onBindViewHolder(,)..." + position);

        //1. position번째의 데이터 가져오기
        Todo item = items.get(position);
        //Person item = getItem(position); 위의 코딩과 같은 내용

        //2. 가져온 데이터를 항목뷰 내부의 UI객체에 반영하기
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        //원본데이터의 전체갯수를 리턴한다.
        return items.size();
    }

    //달라진부분 -- listener 멤버변수에 값을 설정하는 메서드
    public void setOnItemClickListener(OnTodoItemClickListener listener) {
        this.listener = listener;
    }

    //달라진 부분 -- 사용자가 만든 OnPersonClickListener 인터베이스를 구현했으므로
    //              재정의하는 메서드
    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        //항목뷰가 선택되면 멤버변수 listener의 메서드를 동작시킨다.
        if (listener != null) {
            Log.d("TAG", "PersonAdapter의 onItemClick()메서드 동작");
            listener.onItemClick(holder, view, position);
        }
    }

    //내부에 만들어진 ViewHolder객체로 감싸서 관리하라.
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView, final OnTodoItemClickListener listener) {
            super(itemView);
            //생성자의 인자로 전달 된 View itemView가 R.person_Item.xml내용을
            //전개(inflater)하여 객체로 생성 된 것이다.
            Log.d("TAG", "     PersonAdapter.ViewHoder::ViewHolder(View)...");
            textView = itemView.findViewById(R.id.textView);


            //달라진 부분
            //항목뷰를 클릭했을때의 최초로 인지하여 실행되는 이벤트 핸들러 설정 ==> 동일
            //다만, 직접 처리하지 않고 해당 정보를 사용자가 만들 핸들러 객채로 전달한다.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //현재 항목뷰가 RecyclerView에서의 위치값을 얻어온다.
                    int position = getAdapterPosition();
                    if (listener != null) {
                        Log.d("TAG", "ViewHolder:: 항목뷰 click 이벤트1");
                        listener.onItemClick(ViewHolder.this, view, position);

                    }
                }
            });
        }

        public void setItem(Todo item) {
            Log.d("TAG", "PersonAdapter.ViewHolder::setItem()...");
            textView.setText(item.getMemo());
        }

    }


    //원본데이터를 관리하기 위하여 프로그해머가 임의로 추가한 메서드들
    //필요할때 사용자가 호출하여 사용한다.
    public void addItem(Todo item) {  //원본데이터를 추가

        items.add(item);
    }

    public void setItems(ArrayList<Todo> items) { //

        this.items = items;
    }

    public Todo getItem(int position) {

        return items.get(position);
    }

    public void setItem(int position, Todo item) {

        items.set(position, item);
    }


}



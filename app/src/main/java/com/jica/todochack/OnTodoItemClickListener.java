package com.jica.todochack;

import android.view.View;

//사용자가 RecyclerView의 이벤트처리를 위해 직접 Listener interface를 작성
public interface OnTodoItemClickListener {
    public void onItemClick(TodoAdapter.ViewHolder holder, View view, int position);
}

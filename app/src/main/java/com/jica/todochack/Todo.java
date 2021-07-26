package com.jica.todochack;

//RecyclerViewActivity.xml에서 사용하는
public class Todo {
    String memo;

    public Todo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
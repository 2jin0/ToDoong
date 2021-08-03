package com.jica.todochack;

import android.widget.CheckBox;
import android.widget.ImageView;

public class TodoData {
    private String tvTodoText;
    private ImageView ivTodoMenu;
    private CheckBox todo_done;



    //구조
    public TodoData(String tvTodoText, ImageView ivTodoMenu, CheckBox todo_done) {
        this.tvTodoText = tvTodoText;
        this.ivTodoMenu = ivTodoMenu;
        this.todo_done = todo_done;
    }

    public String getTvTodoText() {
        return tvTodoText;
    }

    public void setTvTodoText(String tvTodoText) {
        this.tvTodoText = tvTodoText;
    }

    public ImageView getIvTodoMenu() {
        return ivTodoMenu;
    }

    public void setIvTodoMenu(ImageView ivTodoMenu) {
        this.ivTodoMenu = ivTodoMenu;
    }

    public CheckBox getTodo_done() {
        return todo_done;
    }

    public void setTodo_done(CheckBox todo_done) {
        this.todo_done = todo_done;
    }
}

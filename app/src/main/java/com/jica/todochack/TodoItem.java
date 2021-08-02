package com.jica.todochack;

public class TodoItem {
    private int id;         //게시글의 고유 ID
    private String content; //할일 내용

    public TodoItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

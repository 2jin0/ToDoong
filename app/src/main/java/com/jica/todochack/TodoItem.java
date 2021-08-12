package com.jica.todochack;

import java.io.Serializable;

public class TodoItem implements Serializable {
    private int id;             //게시글의 고유 ID
    private String content;     //할일 내용
    private String writeDate;   //작성날짜
    private String checkBox;   //체크박스

    public TodoItem() {
    }

    public String getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(String checkBox) {
        this.checkBox = checkBox;
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

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", writeDate='" + writeDate + '\'' +
                ", checkBox='" + checkBox + '\'' +
                '}';
    }
}

package com.example.carson_ho.toptabbar.bean;

import org.litepal.crud.DataSupport;


/**
 * Created by Test on 2018/3/9.
 */

public class Article extends DataSupport{

    String title;
    String author;
    String content;
    String desciption;
    String time;




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Article(String title, String author, String content, String desciption, String time) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.desciption = desciption;
        this.time=time;
    }
}

package com.example.carson_ho.toptabbar.bean;



public class New {
    private String title;
    private int imageId;
    private String author;
    private String time;

    public New(String title, int imageId, String author, String time) {
        this.title = title;
        this.imageId = imageId;
        this.author = author;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

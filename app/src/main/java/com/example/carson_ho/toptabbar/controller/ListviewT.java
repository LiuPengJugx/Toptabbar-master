package com.example.carson_ho.toptabbar.controller;



public class ListviewT {
    private String text;
    private int imageId;
    public ListviewT(String text, int imageId){
        this.text = text;
        this.imageId = imageId;
    }
    public String getText(){
        return text;
    }
    public int getImageId(){
        return imageId;
    }
}

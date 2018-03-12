package com.example.carson_ho.toptabbar.bean;

import org.litepal.crud.DataSupport;


public class Room extends DataSupport{
    private String roomNum;
    private int type;
    private float longitude;
    private float latitude;
    private float price;
    private byte status;
    private int photo1;
    private int photo2;
    private int photo3;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Room(String roomNum, int type, float longitude, float latitude, float price, byte status, int photo1, int photo2, int photo3) {
        this.roomNum = roomNum;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.price = price;
        this.status = status;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.photo3 = photo3;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public int getPhoto1() {
        return photo1;
    }

    public void setPhoto1(int photo1) {
        this.photo1 = photo1;
    }

    public int getPhoto2() {
        return photo2;
    }

    public void setPhoto2(int photo2) {
        this.photo2 = photo2;
    }

    public int getPhoto3() {
        return photo3;
    }

    public void setPhoto3(int photo3) {
        this.photo3 = photo3;
    }
}

package com.example.carson_ho.toptabbar.bean;

import org.litepal.crud.DataSupport;


public class Room extends DataSupport{
    private String roomNum;
    private String address;
    private String district;
    private String city;
    private int type;
    private float price;
    private byte status;
    private int photo;

    public Room(String roomNum, String address, String district, String city, int type, float price, byte status, int photo) {
        this.roomNum = roomNum;
        this.address = address;
        this.district = district;
        this.city = city;
        this.type = type;
        this.price = price;
        this.status = status;
        this.photo = photo;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }


    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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





}

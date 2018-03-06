package com.example.carson_ho.toptabbar.bean;
import org.litepal.crud.DataSupport;

public class Order extends DataSupport{
    private String roomNum;
    private String userNo;
    private int roomImage;
    private int from;
    private int to;
    private float cost;
    private byte statusPay;
    private byte statusUse;

    public Order(){
        super();
    }
    public Order( String roomNum, String userNo, int roomImage, int from, int to, float cost, byte statusPay, byte statusUse) {

        this.roomNum = roomNum;
        this.userNo = userNo;
        this.roomImage = roomImage;
        this.from = from;
        this.to = to;
        this.cost = cost;
        this.statusPay = statusPay;
        this.statusUse = statusUse;
    }

    public int getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(int roomImage) {
        this.roomImage = roomImage;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public byte getStatusPay() {
        return statusPay;
    }

    public void setStatusPay(byte statusPay) {
        this.statusPay = statusPay;
    }

    public byte getStatusUse() {
        return statusUse;
    }

    public void setStatusUse(byte statusUse) {
        this.statusUse = statusUse;
    }




}

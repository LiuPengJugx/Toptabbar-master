package com.example.carson_ho.toptabbar.bean;

import org.litepal.crud.DataSupport;


public class User extends DataSupport{
    private int head;
    private String no;
    private String signature;
    private long sIntegral;
    private long sCredit;
    private float balance;
    private String nickName;
    private String passWord;
    private String phone;
    private long cash;



    @Override
    public String toString() {
        return "User{" +
                "head=" + head +
                ", no='" + no + '\'' +
                ", signature='" + signature + '\'' +
                ", sIntegral=" + sIntegral +
                ", sCredit=" + sCredit +
                ", balance=" + balance +
                ", nickName='" + nickName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", phone='" + phone + '\'' +
                ", cash=" + cash +
                '}';
    }
    public User() {
    }

    public User(int head, String no, String signature, long sIntegral, long sCredit, long cash, float balance, String nickName, String passWord, String phone) {
        this.head = head;
        this.no = no;
        this.signature = signature;
        this.sIntegral = sIntegral;
        this.sCredit = sCredit;
        this.cash = cash;
        this.balance = balance;
        this.nickName = nickName;
        this.passWord = passWord;
        this.phone = phone;
    }



    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public long getsIntegral() {
        return sIntegral;
    }

    public void setsIntegral(long sIntegral) {
        this.sIntegral = sIntegral;
    }

    public long getsCredit() {
        return sCredit;
    }

    public void setsCredit(long sCredit) {
        this.sCredit = sCredit;
    }


    public long getCash() {
        return cash;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

}

package com.ehappy.connect;

/**
 * Created by 俊翔 on 2017/1/11.
 */

public class User {
    public String userName;
    public String key;
    public String email;
    public double x;
    public double y;

    public User(){}

    public User(String name, String key, String email, double x, double y){
        this.userName = name;
        this.key = key;
        this.email = email;
        this.x = x;
        this.y = y;
    }

    public String getUserName(){
       return userName;
    }

    public String getKey(){
        return key;
    }

    public String getEmail(){
        return email;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}

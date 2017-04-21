package com.example.android.bank.Data;

import android.os.Parcelable;

import java.io.Serializable;



public class UserData implements Serializable{

    private String name,dob;
    private int amount;

    public UserData(String name,  int amount,String dob) {
        this.name = name;
        this.dob = dob;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getAmount() {
        return Integer.toString(amount);
    }
}

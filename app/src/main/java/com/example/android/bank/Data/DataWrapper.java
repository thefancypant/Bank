package com.example.android.bank.Data;

import java.io.Serializable;
import java.util.ArrayList;


// Wrapper class to encapsulate while using intent
public class DataWrapper implements Serializable {

    private ArrayList<UserData> arrlist;

    public DataWrapper(ArrayList<UserData> data) {
        this.arrlist = data;
    }

    public ArrayList<UserData> getParliaments() {
        return this.arrlist;
    }

}
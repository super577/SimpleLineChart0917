package com.asuper.simplelinechart.data;

/**
 * Created by ok on 2016/8/29.
 */
public class MyData {
    private String date;
    private int amount;

    public MyData(String date, int amount) {
        this.date = date;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

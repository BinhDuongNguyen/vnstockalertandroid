package com.dongbat.stockalert.models;

import java.util.Date;

/**
 * Created by duongnb on 18/12/2015.
 */
public class EOD {
    private float open, high, close, low;
    private long volume;
    private String ticker;
    private Date day;

    public EOD() {
    }

    public EOD(float open, float high, float close, float low, long volume, String ticker, Date day) {
        this.open = open;
        this.high = high;
        this.close = close;
        this.low = low;
        this.volume = volume;
        this.ticker = ticker;
        this.day = day;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}

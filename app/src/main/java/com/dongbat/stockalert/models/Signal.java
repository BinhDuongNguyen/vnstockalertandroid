package com.dongbat.stockalert.models;

import java.util.Date;

/**
 * Created by duongnb on 04/12/2015.
 */

public class Signal {
    private String ticker;
    private Date day;
    private float sma10Signal, sma20Signal, sma10Over20Signal, macdSignal, state;

    public Signal() {
    }

    public Signal(String ticker, Date day, float sma10Signal, float sma20Signal, float sma10Over20Signal, float macdSignal, float state) {
        this.ticker = ticker;
        this.day = day;
        this.sma10Signal = sma10Signal;
        this.sma20Signal = sma20Signal;
        this.sma10Over20Signal = sma10Over20Signal;
        this.macdSignal = macdSignal;
        this.state = state;
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

    public float getSma10Signal() {
        return sma10Signal;
    }

    public void setSma10Signal(float sma10Signal) {
        this.sma10Signal = sma10Signal;
    }

    public float getSma20Signal() {
        return sma20Signal;
    }

    public void setSma20Signal(float sma20Signal) {
        this.sma20Signal = sma20Signal;
    }

    public float getSma10Over20Signal() {
        return sma10Over20Signal;
    }

    public void setSma10Over20Signal(float sma10Over20Signal) {
        this.sma10Over20Signal = sma10Over20Signal;
    }

    public float getMacdSignal() {
        return macdSignal;
    }

    public void setMacdSignal(float macdSignal) {
        this.macdSignal = macdSignal;
    }

    public float getState() {
        return state;
    }

    public void setState(float state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return ticker + "-" + state + "-" + day;
    }
}

package com.dongbat.stockalert.models;

/**
 * Created by duongnb on 06/12/2015.
 */

public class Ticker {
    private String ticker;

    public Ticker() {
    }

    public Ticker(String ticker) {
        this.ticker = ticker;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public String toString() {
        return "ticker is:" + ticker;
    }
}

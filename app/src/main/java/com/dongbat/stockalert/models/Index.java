package com.dongbat.stockalert.models;

/**
 * Created by duongnb on 06/12/2015.
 */
public class Index {
    private String name, tickers;

    @Override
    public String toString () {
        return "index" + name + "include: " + tickers;
    }
}

package com.dongbat.stockalert.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duongnb on 31/12/2015.
 */
public class TickerSignal {
    private String ticker, companyName;
    float currentPrice, lastPrice;
    int currentState, lastState;
    ArrayList<Float> historyPrice;

    public TickerSignal() {
    }

    public TickerSignal(String ticker, String companyName, float currentPrice, float lastPrice, int currentState, int lastState, ArrayList<Float> historyPrice) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.currentPrice = currentPrice;
        this.lastPrice = lastPrice;
        this.currentState = currentState;
        this.lastState = lastState;
        this.historyPrice = historyPrice;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public float getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public int getCurrentState() {
        return currentState;
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public int getLastState() {
        return lastState;
    }

    public void setLastState(int lastState) {
        this.lastState = lastState;
    }

    public ArrayList<Float> getHistoryPrice() {
        return historyPrice;
    }

    public void setHistoryPrice(ArrayList<Float> historyPrice) {
        this.historyPrice = historyPrice;
    }

    public enum Indicators {
        BUY("{zmdi-check} Buy", "#0C8A3A"), SELL("! Sell", "#C93529"), PREBUY("{zmdi-timer} Buy", "#cccccc"), PRESELL("{zmdi-timer} Sell", "#cccccc"), HOLD("{zmdi-block-alt} Hold", "#FAB021");

        private final String label;
        private final String color;

        Indicators(String label, String color) {
            this.label = label;
            this.color = color;
        }

        public  String getColor () {
            return color;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public Indicators getState() {
        if (currentState == 4) {
            if (currentState != lastState) {
                return Indicators.BUY;
            }
        } else if (currentState == 2 || currentState ==3) {
            if (currentState < lastState) {
                return Indicators.PRESELL;
            } else {
                return Indicators.PREBUY;
            }
        } else if (currentState == 0 || currentState == 1 || currentState == -1) {
            if (currentState <= lastState) {
                return Indicators.SELL;
            } else {
                return Indicators.PREBUY;
            }
        } else if (currentState < -1) {
            return Indicators.SELL;
        }
        return Indicators.HOLD;
    }
}
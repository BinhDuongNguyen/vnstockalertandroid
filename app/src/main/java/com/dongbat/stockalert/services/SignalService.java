package com.dongbat.stockalert.services;

import android.content.Context;

import com.dongbat.stockalert.http.HttpHelper;
import com.dongbat.stockalert.http.RequestCallback;
import com.dongbat.stockalert.models.Signal;
import com.dongbat.stockalert.models.Ticker;

/**
 * Created by duongnb on 14/12/2015.
 */

public class SignalService {
    private HttpHelper httpHelper;
    private String urlPrefix;

    public SignalService(Context context, String prefix) {
        httpHelper = new HttpHelper(context);
        this.urlPrefix = prefix;
    }

    public void getByTicker(String ticker, RequestCallback<Signal[]> requestCallback) {
        httpHelper.get(urlPrefix + "/signal/ticker/" + ticker, Signal[].class, requestCallback);
    }
}

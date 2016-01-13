package com.dongbat.stockalert.services;

import android.content.Context;

import com.dongbat.stockalert.http.HttpHelper;
import com.dongbat.stockalert.http.RequestCallback;
import com.dongbat.stockalert.models.Signal;
import com.dongbat.stockalert.models.TickerSignal;

/**
 * Created by duongnb on 05/01/2016.
 */
public class TickerSignalService {
    private HttpHelper httpHelper;
    private String urlPrefix;

    public TickerSignalService(Context context, String prefix) {
        httpHelper = new HttpHelper(context);
        this.urlPrefix = prefix;
    }

    public void getInfo(RequestCallback<TickerSignal[]> requestCallback) {
        httpHelper.get(urlPrefix + "/signal/displayInfo", TickerSignal[].class, requestCallback);
    }

    public void getInfo(int skip, RequestCallback<TickerSignal[]> requestCallback) {
        httpHelper.get(urlPrefix + "/signal/displayInfo/" + skip, TickerSignal[].class, requestCallback);
    }
}

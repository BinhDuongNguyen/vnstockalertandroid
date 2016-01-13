package com.dongbat.stockalert.services;

import android.content.Context;

import com.dongbat.stockalert.http.HttpHelper;
import com.dongbat.stockalert.http.RequestCallback;
import com.dongbat.stockalert.models.EOD;

/**
 * Created by duongnb on 18/12/2015.
 */
public class EodService {
    private HttpHelper httpHelper;
    private String urlPrefix;

    public EodService(Context context, String prefix) {
        httpHelper = new HttpHelper(context);
        this.urlPrefix = prefix;
    }

    public void getByTicker(String ticker, RequestCallback<EOD[]> requestCallback) {
        httpHelper.get(urlPrefix + "/eod/ticker/" + ticker, EOD[].class, requestCallback);
    }
}

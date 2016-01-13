package com.dongbat.stockalert.http;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by duongnb on 14/12/2015.
 */
public class GenericRequest extends StringRequest {

    private String body;
    private static final String JSON_CONTENT_TYPE = "application/json";

    public GenericRequest(int method, String url, String body, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.body = body;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (body == null) {
            return null;
        }
        return body.getBytes();
    }

    @Override
    public String getBodyContentType() {
        return JSON_CONTENT_TYPE;
    }
}

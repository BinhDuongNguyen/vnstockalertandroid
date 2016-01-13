package com.dongbat.stockalert.http;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by duongnb on 14/12/2015.
 */
public class HttpHelper {

    private final RequestQueue requestQueue;
    private final Gson gson;

    public HttpHelper(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
    }

    public <K> void get(String url, final Class<K> responseType, final RequestCallback<K> requestCallback) {
        request(url, Request.Method.GET, null, responseType, requestCallback);
    }

    public <K> void delete(String url, final Class<K> responseType, final RequestCallback<K> requestCallback) {
        request(url, Request.Method.DELETE, null, responseType, requestCallback);
    }

    public <K> void post(String url, Object body, final Class<K> responseType, final RequestCallback<K> requestCallback) {
        request(url, Request.Method.POST, body, responseType, requestCallback);
    }

    public <K> void put(String url, Object body, final Class<K> responseType, final RequestCallback<K> requestCallback) {
        request(url, Request.Method.PUT, body, responseType, requestCallback);
    }

    private <K> void request(String url, int method, Object requestBody, final Class<K> responseType, final RequestCallback<K> requestCallback) {
        Request request = new GenericRequest(method, url, gson.toJson(requestBody), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                requestCallback.success(gson.fromJson(response, responseType));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                requestCallback.error(error);
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(request);
    }
}

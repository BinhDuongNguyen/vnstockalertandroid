package com.dongbat.stockalert.http;

/**
 * Created by duongnb on 14/12/2015.
 */
public interface RequestCallback<T>{
    public void success(T data);
    public void error(Exception exception);
}

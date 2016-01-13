package com.dongbat.stockalert.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duongnb on 05/01/2016.
 */
public class ArrayUtil {

    public static <T> T[] join(T[] array1, T[] array2) {
        List<T> list = new ArrayList<T>();

        for(T t: array1) {
            list.add(t);
        }

        for(T t: array2) {
            list.add(t);
        }

        return (T[]) list.toArray();
    }
}

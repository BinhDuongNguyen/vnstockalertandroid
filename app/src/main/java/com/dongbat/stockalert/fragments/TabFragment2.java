package com.dongbat.stockalert.fragments;

/**
 * Created by duongnb on 22/12/2015.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dongbat.stockalert.R;
import com.dongbat.stockalert.adapters.TickerAdapter;
import com.dongbat.stockalert.models.Ticker;
import com.google.gson.Gson;

public class TabFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_1, container, false);
    }
}

package com.dongbat.stockalert.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dongbat.stockalert.R;
import com.dongbat.stockalert.fragments.TabFragment2;
import com.dongbat.stockalert.models.Ticker;

/**
 * Created by duongnb on 29/12/2015.
 */
public class TickerAdapter extends ArrayAdapter<Ticker> {

    public TickerAdapter(Context context, Ticker[] tickers) {
        super(context, 0, tickers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ticker ticker = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_ticker, parent, false);
        }
        TextView tickerView = (TextView) convertView.findViewById(R.id.ticker_textview);
        tickerView.setText(ticker.getTicker());

        return convertView;
    }
}

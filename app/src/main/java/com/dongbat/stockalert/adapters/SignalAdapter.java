package com.dongbat.stockalert.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dongbat.stockalert.R;
import com.dongbat.stockalert.models.Signal;
import com.dongbat.stockalert.models.Ticker;
import com.github.mikephil.charting.charts.LineChart;

/**
 * Created by duongnb on 30/12/2015.
 */

public class SignalAdapter extends ArrayAdapter<Signal> {
    public SignalAdapter(Context context, Signal[] signals) {
        super(context, 0, signals);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Signal signal = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_signal, parent, false);
        }
        TextView tickerView = (TextView) convertView.findViewById(R.id.ticker_textview);
        tickerView.setText(signal.getTicker());

        TextView stateView = (TextView) convertView.findViewById(R.id.state_textview);
        stateView.setText(String.valueOf(signal.getState()));

        LineChart chart = (LineChart) convertView.findViewById(R.id.price_chart);

        return convertView;
    }
}

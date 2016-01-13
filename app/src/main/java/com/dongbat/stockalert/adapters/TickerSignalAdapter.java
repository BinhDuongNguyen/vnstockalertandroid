package com.dongbat.stockalert.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dongbat.stockalert.R;
import com.dongbat.stockalert.models.Signal;
import com.dongbat.stockalert.models.TickerSignal;
import com.dongbat.stockalert.view.MiniChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duongnb on 31/12/2015.
 */

public class TickerSignalAdapter extends ArrayAdapter<TickerSignal> {
    private static final int UP_COLOR = Color.parseColor("#0C8A3A");
    private static final int DOWN_COLOR = Color.parseColor("#C93529");

    public TickerSignalAdapter(Context context, List<TickerSignal> tickerSignals) {
        super(context, 0, tickerSignals);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TickerSignal tickerSignal = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_ticker_signal, parent, false);
        }
        TextView tickerView = (TextView) convertView.findViewById(R.id.ticker_textview);
        tickerView.setText(tickerSignal.getTicker());
        tickerView.setTextColor(Color.parseColor("#3E3E3E"));

        TextView stateView = (TextView) convertView.findViewById(R.id.state_textview);
        stateView.setText(tickerSignal.getState().toString());
        stateView.setTextColor(Color.parseColor(tickerSignal.getState().getColor()));

        MiniChart chart = (MiniChart) convertView.findViewById(R.id.price_chart);
        chart.setValues(tickerSignal.getHistoryPrice());


        TextView companyNameView = (TextView) convertView.findViewById(R.id.company_name_textview);
        if (tickerSignal.getCompanyName() == null) {
            companyNameView.setText("DongBat Software");
        } else {
            companyNameView.setText(String.valueOf(tickerSignal.getCompanyName()));
        }

        TextView currentPrice = (TextView) convertView.findViewById(R.id.price_textview);
        currentPrice.setText(String.valueOf(tickerSignal.getCurrentPrice()));

        View cardColor = convertView.findViewById(R.id.card_color);
        TextView priceChangeView = (TextView) convertView.findViewById(R.id.price_change_textview);
        float change = tickerSignal.getCurrentPrice() - tickerSignal.getLastPrice();
        float percentage = (change) / tickerSignal.getLastPrice() * 100.00f;
        String upDown = "zmdi-trending-flat";
        if (change < 0) {
            upDown = "zmdi-trending-down";
            priceChangeView.setTextColor(DOWN_COLOR);
            cardColor.setBackgroundColor(DOWN_COLOR);

        } else if (change > 0) {
            upDown = "zmdi-trending-up";
            priceChangeView.setTextColor(UP_COLOR);
            cardColor.setBackgroundColor(UP_COLOR);
        }
        priceChangeView.setText(String.format("{%s} %.2f (%.1f%%)", upDown, Math.abs(change), Math.abs(percentage)));

        return convertView;
    }
}

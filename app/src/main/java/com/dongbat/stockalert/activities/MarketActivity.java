package com.dongbat.stockalert.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.dongbat.stockalert.http.RequestCallback;
import com.dongbat.stockalert.models.EOD;
import com.dongbat.stockalert.models.Signal;
import com.dongbat.stockalert.services.EodService;
import com.dongbat.stockalert.services.SignalService;
import com.dongbat.stockalert.utils.Constants;
import com.dongbat.stockalert.utils.chart.PricePointAdapter;
import com.dongbat.stockalert.utils.chart.VolumePointAdapter;
import com.leadingbyte.stockchart.Area;
import com.leadingbyte.stockchart.Series;
import com.leadingbyte.stockchart.Side;
import com.leadingbyte.stockchart.StockChartView;
import com.leadingbyte.stockchart.points.AbstractPoint;
import com.leadingbyte.stockchart.points.Point2;
import com.leadingbyte.stockchart.renderers.CandlestickStockRenderer;
import com.leadingbyte.stockchart.renderers.HistogramRenderer;
import com.leadingbyte.stockchart.utils.StockDataGenerator;

import com.dongbat.stockalert.R;

public class MarketActivity extends AppCompatActivity {
    private static final String TAG = "StockAlert";
    int openPrice, highPrice, lowPrice, closePrice, volume;
    String ticker;
    String url = Constants.url;

    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        final TextView textViewTicker = (TextView) findViewById(R.id.tv_ticker);
        final TextView textViewOpenPrice = (TextView) findViewById(R.id.tv_open_value);
        final TextView textViewHighPrice = (TextView) findViewById(R.id.tv_high_value);
        final TextView textViewLowPrice = (TextView) findViewById(R.id.tv_low_value);
        final TextView textViewClosePrice = (TextView) findViewById(R.id.tv_close_value);
        final TextView textViewVolume = (TextView) findViewById(R.id.tv_volume_value);

        final StockChartView chart = (StockChartView) this.findViewById(R.id.stockChartView);

        Area a1 = chart.addArea();
        Area a2 = chart.addArea();

        final Series price = chart.addSeries(a1);
        price.setName("price");
        price.setYAxisSide(Side.RIGHT);

        price.setRenderer(new CandlestickStockRenderer());
        // The same we do for the volume series
        final Series volume = chart.addSeries(a2);
        volume.setRenderer(new HistogramRenderer());
        volume.setName("volume");
        volume.setYAxisSide(Side.RIGHT);
        EodService eodService = new EodService(this, url);
        eodService.getByTicker("bvh", new RequestCallback<EOD[]>() {
            @Override
            public void success(EOD[] data) {
                price.setPointAdapter(new PricePointAdapter(data));
                volume.setPointAdapter(new VolumePointAdapter(data));
                chart.invalidate();
            }

            @Override
            public void error(Exception exception) {
                Log.e(TAG, exception.getMessage());
            }
        });


        // Don't forget to call invalidate() each time you do any chart modifications

        search = (SearchView) findViewById(R.id.sv_ticker_view);
        search.setQueryHint("Import Ticker");

        //*** setOnQueryTextFocusChangeListener ***
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub

                Toast.makeText(getBaseContext(), String.valueOf(hasFocus),
                        Toast.LENGTH_SHORT).show();
            }
        });

        //*** setOnQueryTextListener ***
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub

                getSearchSubmit(query, price, volume, chart,
                        textViewTicker, textViewOpenPrice, textViewHighPrice,
                        textViewLowPrice, textViewClosePrice, textViewVolume);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub

                Toast.makeText(getBaseContext(), newText,
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void getSearchSubmit (final String text, final Series price, final Series volume, final StockChartView chart,
                                  final TextView textViewTicker, final TextView textViewOpenPrice, final TextView textViewHighPrice,
                                  final TextView textViewLowPrice, final TextView textViewClosePrice, final TextView textViewVolume) {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        EodService eodService = new EodService(this, url);
        eodService.getByTicker(text, new RequestCallback<EOD[]>() {
            @Override
            public void success(EOD[] data) {
                textViewTicker.setText(data[data.length - 1].getTicker());
                textViewOpenPrice.setText(String.valueOf(data[data.length - 1].getOpen()));
                textViewHighPrice.setText(String.valueOf(data[data.length - 1].getHigh()));
                textViewLowPrice.setText(String.valueOf(data[data.length - 1].getLow()));
                textViewClosePrice.setText(String.valueOf(data[data.length - 1].getClose()));
                textViewVolume.setText(String.valueOf(data[data.length - 1].getVolume()));

                price.setPointAdapter(new PricePointAdapter(data));
                volume.setPointAdapter(new VolumePointAdapter(data));
                chart.invalidate();
                pDialog.hide();
            }

            @Override
            public void error(Exception exception) {
                Log.e(TAG, exception.getMessage());
            }
        });
    }
}

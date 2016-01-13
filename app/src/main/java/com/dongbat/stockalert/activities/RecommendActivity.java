package com.dongbat.stockalert.activities;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.LoginFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dongbat.stockalert.R;
import com.dongbat.stockalert.data.DBHelper;
import com.dongbat.stockalert.models.Signal;
import com.dongbat.stockalert.utils.Constants;
import com.dongbat.stockalert.utils.GsonRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

public class RecommendActivity extends AppCompatActivity {

    private RequestQueue queue;
    final String TAG = Constants.TAG;
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

    private DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        queue = Volley.newRequestQueue(this);

        TextView overBuy = (TextView) findViewById(R.id.tv_re_index_number_over_buy);
        TextView buy = (TextView) findViewById(R.id.tv_re_index_number_buy);
        TextView medium = (TextView) findViewById(R.id.tv_re_index_number_medium);
        TextView sell = (TextView) findViewById(R.id.tv_re_index_number_sell);
        TextView overSell = (TextView) findViewById(R.id.tv_re_index_number_over_sell);



//        mydb = new DBHelper(this);

//        int[] indexStates = processDataFromDb(getDataFromServer());
//        int overBuyNumber = indexStates[0],
//                buyNumber = indexStates[1],
//                mediumNumber = indexStates[2],
//                sellNumber = indexStates[3],
//                overSellNumber = indexStates[4];
//
////        Button b = (Button) findViewById(R.id.button1);
////        b.setVisibility(View.INVISIBLE);
//
//        overBuy.setText(String.valueOf(overBuyNumber));
//        overBuy.setFocusable(false);
//        overBuy.setClickable(false);
//
//        buy.setText(String.valueOf(buyNumber));
//        buy.setFocusable(false);
//        buy.setClickable(false);
//
//        medium.setText(String.valueOf(mediumNumber));
//        medium.setFocusable(false);
//        medium.setClickable(false);
//
//        sell.setText(String.valueOf(sellNumber));
//        sell.setFocusable(false);
//        sell.setClickable(false);
//
//        overSell.setText(String.valueOf(overSellNumber));
//        overSell.setFocusable(false);
//        overSell.setClickable(false);

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

    private int[] processDataFromDb(ArrayList<Signal> arrayList) {
        int overBuy = 0, buy = 0, medium = 0, sell = 0, overSell = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < Constants.vn30.length; j++) {
//                if (String.valueOf(Constants.vn30[j]).equals(String.valueOf(arrayList.get(i).ticker))) {
//                    Log.i(TAG, "processDataFromDb: " + Constants.vn30[j]);
//                    Log.i(TAG, "AAAAAAAAAAAAAAA");
//                    String value = Constants.vn30[j];
////            Cursor rs = mydb.getData(value);
////            String sma10 = rs.getString(rs.getColumnIndex(DBHelper.SIGNALS_COLUMN_SMA10));
////            String sma20 = rs.getString(rs.getColumnIndex(DBHelper.SIGNALS_COLUMN_SMA20));
////            String sma10over20 = rs.getString(rs.getColumnIndex(DBHelper.SIGNALS_COLUMN_SMA10OVER20));
////            String macd = rs.getString(rs.getColumnIndex(DBHelper.SIGNALS_COLUMN_MACD));
////            int state = Integer.parseInt(rs.getString(rs.getColumnIndex(DBHelper.SIGNALS_COLUMN_STATE)));
//                    float state = Float.parseFloat(String.valueOf(arrayList.get(i).state));
//                    if (state < -2) {
//                        overSell += 1;
//                    } else if (state < 0) {
//                        sell += 1;
//                    } else if (state < 2) {
//                        medium += 1;
//                    } else if (state < 4) {
//                        buy += 1;
//                    } else {
//                        overBuy += 1;
//                    }
//                }
            }
        }
        return new int[]{overBuy, buy, medium, sell, overSell};
    }

    public void getDataFromServer() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(Constants.url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        final ArrayList<Signal> ret = new ArrayList<Signal>(response.length());
                        int state = 0;
                        int[] indexStates = new int[5];
                        for (int i = 0; i < response.length(); i++) {
                            String json = null;
                            try {
                                json = String.valueOf(response.get(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Signal signal = gson.fromJson(json, Signal.class);
                            ret.add(signal);
//                            Log.i(TAG, "onResponse: " + signal.ticker);
                            state += 1;
                            if (state == response.length() - 1) {
                                indexStates = processDataFromDb(ret);
//                                int overBuyNumber = indexStates[0],
//                                        buyNumber = indexStates[1],
//                                        mediumNumber = indexStates[2],
//                                        sellNumber = indexStates[3],
//                                        overSellNumber = indexStates[4];
//
//                                overBuy.setText(String.valueOf(overBuyNumber));
//                                overBuy.setFocusable(false);
//                                overBuy.setClickable(false);
//
//                                buy.setText(String.valueOf(buyNumber));
//                                buy.setFocusable(false);
//                                buy.setClickable(false);
//
//                                medium.setText(String.valueOf(mediumNumber));
//                                medium.setFocusable(false);
//                                medium.setClickable(false);
//
//                                sell.setText(String.valueOf(sellNumber));
//                                sell.setFocusable(false);
//                                sell.setClickable(false);
//
//                                overSell.setText(String.valueOf(overSellNumber));
//                                overSell.setFocusable(false);
//                                overSell.setClickable(false);
//
                            }
                        }
//                        int[] indexStates = processDataFromDb(ret);
//                        int overBuyNumber = indexStates[0],
//                                buyNumber = indexStates[1],
//                                mediumNumber = indexStates[2],
//                                sellNumber = indexStates[3],
//                                overSellNumber = indexStates[4];

//                        overBuy.setText(String.valueOf(overBuyNumber));
//                        overBuy.setFocusable(false);
//                        overBuy.setClickable(false);
//
//                        buy.setText(String.valueOf(buyNumber));
//                        buy.setFocusable(false);
//                        buy.setClickable(false);
//
//                        medium.setText(String.valueOf(mediumNumber));
//                        medium.setFocusable(false);
//                        medium.setClickable(false);
//
//                        sell.setText(String.valueOf(sellNumber));
//                        sell.setFocusable(false);
//                        sell.setClickable(false);
//
//                        overSell.setText(String.valueOf(overSellNumber));
//                        overSell.setFocusable(false);
//                        overSell.setClickable(false);
                        Log.i(TAG, "Overbuy = " + indexStates[0]);

                        pDialog.hide();
                        Log.i(TAG, "onResponse: Done getting Data");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                pDialog.hide();
                Log.i(TAG, "Getting Data error");
            }
        });
        queue.add(req);
    }
}

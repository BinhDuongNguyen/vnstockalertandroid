package com.dongbat.stockalert.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dongbat.stockalert.R;
import com.dongbat.stockalert.adapters.PagerAdapter;
import com.mikepenz.iconics.context.IconicsLayoutInflater;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), new IconicsLayoutInflater(getDelegate()));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Market Overall"));
        tabLayout.addTab(tabLayout.newTab().setText("Watch List"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_home:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;
            case R.id.action_market:
                Intent marketIntent = new Intent(MainActivity.this, com.dongbat.stockalert.activities.MarketActivity.class);
                MainActivity.this.startActivity(marketIntent);
                return true;
            case R.id.action_alert:
                Intent alertIntent = new Intent(MainActivity.this, com.dongbat.stockalert.activities.AlertActivity.class);
                MainActivity.this.startActivity(alertIntent);
                return true;
            case R.id.action_portfolio:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;
            case R.id.action_recommend:
                Intent recommendIntent = new Intent(MainActivity.this, com.dongbat.stockalert.activities.RecommendActivity.class);
                MainActivity.this.startActivity(recommendIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.nfc.Tag;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.widget.TextView;
//
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
//import com.dongbat.stockalert.R;
//import com.dongbat.stockalert.data.DBHelper;
//import com.dongbat.stockalert.http.RequestCallback;
//import com.dongbat.stockalert.models.Signal;
//import com.dongbat.stockalert.services.SignalService;
//import com.dongbat.stockalert.utils.GsonRequest;
//import com.dongbat.stockalert.utils.Constants;
//import com.dongbat.stockalert.utils.JsonDateDeserializer;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//public class MainActivity extends AppCompatActivity {
//
//    private RequestQueue queue;
//    SQLiteDatabase db;
//    private DBHelper mydb;
//    final String TAG = Constants.TAG;
//    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        queue = Volley.newRequestQueue(this);
//
//        SignalService signalService = new SignalService(this, "http://192.168.1.16:3000");
//        signalService.getByTicker("bvh", new RequestCallback<Signal[]>() {
//            @Override
//            public void success(Signal[] data) {
//                Log.d(TAG, "dcm" + data.length + "");
//                Log.d(TAG, "dcm" + data.length + "");
//            }
//
//            @Override
//            public void error(Exception exception) {
//                Log.e(TAG, exception.getMessage());
//            }
//        });
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.i(TAG, "onClick: Why ??");
////                updateData();
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//            }
//        });
////        db = openOrCreateDatabase("Stock", Context.MODE_PRIVATE, null);
////        onCreateDB();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                // User chose the "Settings" item, show the app settings UI...
//                return true;
//
//            case R.id.action_home:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//                return true;
//            case R.id.action_market:
//                Intent marketIntent = new Intent(MainActivity.this, com.dongbat.stockalert.activities.MarketActivity.class);
//                MainActivity.this.startActivity(marketIntent);
//                return true;
//            case R.id.action_alert:
//                Intent alertIntent = new Intent(MainActivity.this, com.dongbat.stockalert.activities.AlertActivity.class);
//                MainActivity.this.startActivity(alertIntent);
//                return true;
//            case R.id.action_portfolio:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//                return true;
//            case R.id.action_recommend:
//                Intent recommendIntent = new Intent(MainActivity.this, com.dongbat.stockalert.activities.RecommendActivity.class);
//                MainActivity.this.startActivity(recommendIntent);
//                return true;
//
//            default:
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                return super.onOptionsItemSelected(item);
//
//        }
//    }
//
////    private void createDataBase () {
////        db = openOrCreateDatabase("Stock", Context.MODE_PRIVATE, null);
////        db.execSQL("CREATE TABLE IF NOT EXISTS signal(ticker VARCHAR,sma10 NUMBER,sma20 NUMBER, sma10over20 NUMBER, macd NUMBER, state NUMBER);");
////        db.execSQL("CREATE TABLE IF NOT EXISTS index(name VARCHAR, tickers VARCHAR)");
////        db.execSQL("CREATE TABLE IF NOT EXISTS eod(ticker VARCHAR, open NUMBER, high NUMBER, low NUMBER, close NUMBER, volume NUMBER)");
////        db.execSQL("CREATE TABLE IF NOT EXISTS ticker(ticker VARCHAR)");
////    }
//
//    private void onCreateDB () {
//        mydb = new DBHelper(this);
//        mydb.onCreate(db);
//    }
//
//    public void updateData () {
////        mydb = new DBHelper(this);
////        final ProgressDialog pDialog = new ProgressDialog(this);
////        pDialog.setMessage("Loading...");
////        pDialog.show();
////        final TextView test = (TextView) findViewById(R.id.tv_test);
////
////        pDialog.setMessage("Loading...");
////        pDialog.show();
////        JsonArrayRequest req = new JsonArrayRequest(Constants.urlSignalLastest,
////                new Response.Listener<JSONArray>() {
////                    @Override
////                    public void onResponse(JSONArray response) {
////                        for (int i = 0; i < response.length(); i++) {
////                            String json = null;
////                            try {
////                                json = String.valueOf(response.get(i));
////                            } catch (JSONException e) {
////                                e.printStackTrace();
////                            }
////                            Signal signal = gson.fromJson(json, Signal.class);
////                            mydb.updateSignals(db, signal.ticker, signal.sma10Signal, signal.sma20Signal, signal.sma10Over20Signal, signal.macdSignal, signal.state);
////                            Log.i(TAG, "onResponse: " + signal.ticker);
////                        }
////                        pDialog.hide();
////                        ArrayList<String> ret = mydb.getAllSignals(db);
////                        Log.i(TAG, "onResponse: " + ret);
////                    }
////                }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
////                error.printStackTrace();
////                pDialog.hide();
////                Log.i(TAG, "aaaaaaaaaaaaaaaaaaa");
////            }
////        });
////        queue.add(req);
////
//////        GsonRequest<Signal[]> getSignals = new GsonRequest<Signal[]>(Constants.urlSignalLastest, Signal[].class,
//////
//////                new Response.Listener<Signal[]>() {
//////                    @Override
//////                    public void onResponse(Signal[] response) {
//////                        Log.i(TAG, "aaaaaaaaaaaaaaaaaaa");
//////                        Log.d(TAG, response.toString());
//////                        List<Signal> signals = Arrays.asList(response);
//////
//////                        test.setText(String.valueOf(response));
//////
//////                        for (int i = 0; i < signals.size(); i++) {
//////                            mydb.updateSignals(signals.get(i).ticker,signals.get(i).sma10Signal, signals.get(i).sma20Signal, signals.get(i).sma10Over20Signal, signals.get(i).macdSignal, signals.get(i).state);
//////                            Log.d(TAG,signals.toString());
//////                        }
//////                        pDialog.hide();
//////                    }
//////                }, new Response.ErrorListener() {
//////            @Override
//////            public void onErrorResponse(VolleyError error) {
//////                error.printStackTrace();
//////                Log.i(TAG, "aaaaaaaaaaaaaaaaaaa");
//////                pDialog.hide();
//////            }
//////        });
//////        queue.add(getSignals);
//    }
//
//    public void getArrayRequest(String url) {
//        GsonRequest<Signal[]> getSignals = new GsonRequest<Signal[]>(url, Signal[].class,
//
//                new Response.Listener<Signal[]>() {
//                    @Override
//                    public void onResponse(Signal[] response) {
//                        List<Signal> signals = Arrays.asList(response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        });
//        queue.add(getSignals);
//    }
//
//    public void getRequest(String url) {
//        final TextView responseTv = (TextView) findViewById(R.id.tv_response);
//        final ProgressDialog pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//        JsonArrayRequest req = new JsonArrayRequest(url,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        responseTv.setText(response.toString());
//                        responseTv.setVisibility(View.VISIBLE);
//                        pDialog.hide();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                pDialog.hide();
//            }
//        });
//        queue.add(req);
//    }
//}

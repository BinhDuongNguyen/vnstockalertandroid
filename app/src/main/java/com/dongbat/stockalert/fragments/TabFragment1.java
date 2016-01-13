package com.dongbat.stockalert.fragments;

/**
 * Created by duongnb on 22/12/2015.
 */

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dongbat.stockalert.R;
import com.dongbat.stockalert.adapters.TickerSignalAdapter;
import com.dongbat.stockalert.http.RequestCallback;
import com.dongbat.stockalert.models.EOD;
import com.dongbat.stockalert.models.Signal;
import com.dongbat.stockalert.models.Ticker;
import com.dongbat.stockalert.models.TickerSignal;
import com.dongbat.stockalert.services.EodService;
import com.dongbat.stockalert.services.TickerSignalService;
import com.dongbat.stockalert.utils.ArrayUtil;
import com.dongbat.stockalert.utils.Constants;
import com.dongbat.stockalert.utils.chart.PricePointAdapter;
import com.dongbat.stockalert.utils.chart.VolumePointAdapter;
import com.dongbat.stockalert.view.EndlessScrollListener;
import com.dongbat.stockalert.view.OverscrollableListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class TabFragment1 extends Fragment implements OverscrollableListView.Loader {

    private OverscrollableListView listView;
    int start = 0;
    int limit = 10;
    boolean loadingMore = false;
    View loadMoreView;
    String url = Constants.url;
    String TAG = Constants.TAG;

    private String dummyData = "[{\"_id\":\"5677791b2788d2050bdbc429\",\"ticker\":\"AAA\",\"day\":\"2015-12-17T17:00:00.000Z\",\"state\":2,\"macdSignal\":-1,\"sma10Over20Signal\":1,\"sma20Signal\":1,\"sma10Signal\":1},{\"_id\":\"5677791c2788d2050bdbc9f2\",\"ticker\":\"AAM\",\"day\":\"2015-12-17T17:00:00.000Z\",\"state\":-2,\"macdSignal\":-1,\"sma10Over20Signal\":1,\"sma20Signal\":-1,\"sma10Signal\":-1},{\"_id\":\"5677791d2788d2050bdbcc7f\",\"ticker\":\"ABI\",\"day\":\"2015-12-17T17:00:00.000Z\",\"state\":-4,\"macdSignal\":-1,\"sma10Over20Signal\":-1,\"sma20Signal\":-1,\"sma10Signal\":-1},{\"_id\":\"5677791e2788d2050bdbd4ca\",\"ticker\":\"ABT\",\"day\":\"2015-12-17T17:00:00.000Z\",\"state\":-4,\"macdSignal\":-1,\"sma10Over20Signal\":-1,\"sma20Signal\":-1,\"sma10Signal\":-1},{\"_id\":\"5677791e2788d2050bdbdd9b\",\"ticker\":\"ACB\",\"day\":\"2015-12-17T17:00:00.000Z\",\"state\":-2,\"macdSignal\":1,\"sma10Over20Signal\":-1,\"sma20Signal\":-1,\"sma10Signal\":-1},{\"_id\":\"5677791f2788d2050bdbe171\",\"ticker\":\"ACC\",\"day\":\"2015-12-17T17:00:00.000Z\",\"state\":-4,\"macdSignal\":-1,\"sma10Over20Signal\":-1,\"sma20Signal\":-1,\"sma10Signal\":-1},{\"_id\":\"567779202788d2050bdbe29d\",\"ticker\":\"ACE\",\"day\":\"2015-12-17T17:00:00.000Z\",\"state\":0,\"macdSignal\":-1,\"sma10Over20Signal\":-1,\"sma20Signal\":1,\"sma10Signal\":1},{\"_id\":\"567779212788d2050bdbea74\",\"ticker\":\"ACL\",\"day\":\"2015-12-17T17:00:00.000Z\",\"state\":-4,\"macdSignal\":-1,\"sma10Over20Signal\":-1,\"sma20Signal\":-1,\"sma10Signal\":-1},{\"_id\":\"567779222788d2050bdbeade\",\"ticker\":\"ACM\",\"day\":\"2015-12-17T17:00:00.000Z\",\"state\":-4,\"macdSignal\":-1,\"sma10Over20Signal\":-1,\"sma20Signal\":-1,\"sma10Signal\":-1},{\"_id\":\"567779242788d2050bdbfbb7\",\"ticker\":\"AGF\",\"day\":\"2015-12-17T17:00:00.000Z\",\"state\":4,\"macdSignal\":1,\"sma10Over20Signal\":1,\"sma20Signal\":1,\"sma10Signal\":1}]";
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
    private TickerSignalService tickerSignalService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_fragment_1, container, false);
        perform(v);
        return v;
    }

    private ArrayList<TickerSignal> loaded = new ArrayList<>();

    public void perform(View v) {
        listView = (OverscrollableListView) v.findViewById(R.id.tab1_listview);
        listView.setMaximum(800);
        listView.setLoader(this);
        tickerSignalService = new TickerSignalService(this.getActivity(), url);
        tickerSignalService.getInfo(new RequestCallback<TickerSignal[]>() {
            @Override
            public void success(TickerSignal[] data) {
                for (TickerSignal t: data) {
                    loaded.add(t);
                }
                listView.setAdapter(new TickerSignalAdapter(TabFragment1.this.getContext(), loaded));
            }

            @Override
            public void error(Exception exception) {
                Log.e(TAG, "err", exception);
            }
        });
    }

    @Override
    public void load(int itemLoaded, final OverscrollableListView endlessScrollListener) {
        final ProgressDialog pDialog = new ProgressDialog(TabFragment1.this.getContext());
        pDialog.setMessage("Loading...");
        pDialog.show();
        tickerSignalService.getInfo(itemLoaded, new RequestCallback<TickerSignal[]>() {
            @Override
            public void success(TickerSignal[] data) {
                ArrayAdapter<TickerSignal> adapter = (ArrayAdapter<TickerSignal>) listView.getAdapter();
                adapter.addAll(data);
                endlessScrollListener.doneLoading();
                pDialog.hide();
            }

            @Override
            public void error(Exception exception) {
                endlessScrollListener.doneLoading();
                pDialog.hide();
            }
        });
    }
}
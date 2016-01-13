package com.dongbat.stockalert.view;

import android.widget.AbsListView;

/**
 * Created by duongnb on 05/01/2016.
 */
public class EndlessScrollListener implements AbsListView.OnScrollListener {

    public interface Loader {
        void load(int itemLoaded, EndlessScrollListener endlessScrollListener);
    }

    public void doneLoading() {
        finishLoading = true;
    }

    private boolean finishLoading = true;
    private int maximum;
    private Loader loader;

    public EndlessScrollListener(int maximum, Loader loader) {
        this.maximum = maximum;
        this.loader = loader;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(finishLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + 0) && totalItemCount < maximum) {
            finishLoading = false;
            loader.load(totalItemCount, this);
        }
    }
}

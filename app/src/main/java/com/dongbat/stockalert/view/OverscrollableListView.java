package com.dongbat.stockalert.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

/**
 * Created by duongnb on 06/01/2016.
 */
public class OverscrollableListView extends ListView {

    public interface Loader {
        void load(int itemLoaded, OverscrollableListView endlessScrollListener);
    }

    public void doneLoading() {
        finishLoading = true;
    }

    public OverscrollableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    private boolean finishLoading = true;
    private int maximum;
    private Loader loader;

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        if(finishLoading && getAdapter().getCount() < maximum) {
            if(getFirstVisiblePosition()==0){
            }else{
                finishLoading = false;
                loader.load(getAdapter().getCount(), this);
            }
        }
    }
}

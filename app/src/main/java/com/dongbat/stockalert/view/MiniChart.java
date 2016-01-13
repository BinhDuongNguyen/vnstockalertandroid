package com.dongbat.stockalert.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by duongnb on 05/01/2016.
 */
public class MiniChart extends View {

    private List<Float> values;
    private final Paint paint;

    public MiniChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
    }

    private boolean reversed = false;

    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }


    public void setValues(List<Float> values) {
        this.values = values;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (values == null) {
            return;
        }
        float ww = getWidth();
        float hh = getHeight();
        float w = getWidth() * .94f;
        float xGap = getWidth() * .03f;
        float h = getHeight() * .94f;
        float yGap = getHeight() * .03f;
        float yMax = 0;
        float yMin = Float.MAX_VALUE;

        for (int i = 0; i < values.size(); i++) {
            float y = values.get(i);
            if (yMin > y) {
                yMin = y;
            }
            if (yMax < y) {
                yMax = y;
            }
        }

        float yRange = yMax - yMin;
        float xone = w / values.size();

        for (int i = 1; i < values.size(); i++) {
            float y = values.get(i);
            float prevY = values.get(i - 1);

            float x1 = (i - 1) * xone + xGap, y1 = hh - (prevY - yMin) / yRange * h + yGap, x2 = x1 + xone, y2 = hh - (y - yMin) / yRange * h + yGap;
            if(reversed) {
                x1 = ww - x1;
                x2 = ww - x2;
            }

            canvas.drawLine(x1, y1, x2, y2, paint);
        }
    }
}

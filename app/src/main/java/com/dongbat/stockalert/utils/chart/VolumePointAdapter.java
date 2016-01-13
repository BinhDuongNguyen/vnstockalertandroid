package com.dongbat.stockalert.utils.chart;

import com.dongbat.stockalert.models.EOD;
import com.leadingbyte.stockchart.Series;
import com.leadingbyte.stockchart.points.AbstractPoint;
import com.leadingbyte.stockchart.points.Point2;
import com.leadingbyte.stockchart.points.StockPoint;
import com.leadingbyte.stockchart.utils.StockDataGenerator;

/**
 * Created by duongnb on 18/12/2015.
 */
public class VolumePointAdapter implements Series.IPointAdapter {

    private final Point2 point = new Point2();

    private final EOD[] eods;

    public VolumePointAdapter(EOD[] eods) {
        this.eods = eods;
    }

    @Override
    public int getPointCount() {
        return eods.length;
    }

    @Override
    public AbstractPoint getPointAt(int i) {
        EOD eod = eods[i];
        point.setValues(eod.getVolume(), 0.0);
        return point;
    }
}

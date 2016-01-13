package com.dongbat.stockalert.utils.chart;

import com.dongbat.stockalert.models.EOD;
import com.leadingbyte.stockchart.Series;
import com.leadingbyte.stockchart.points.AbstractPoint;
import com.leadingbyte.stockchart.points.StockPoint;
import com.leadingbyte.stockchart.utils.StockDataGenerator;

/**
 * Created by duongnb on 18/12/2015.
 */
public class PricePointAdapter implements Series.IPointAdapter {

    private final StockPoint point = new StockPoint();

    private final EOD[] eods;

    public PricePointAdapter(EOD[] eods) {
        this.eods = eods;
    }

    @Override
    public int getPointCount() {
        return eods.length;
    }

    @Override
    public AbstractPoint getPointAt(int i) {
        EOD eod = eods[i];
        point.setOhlc(eod.getOpen(), eod.getHigh(), eod.getLow(), eod.getClose());
        return point;
    }
}

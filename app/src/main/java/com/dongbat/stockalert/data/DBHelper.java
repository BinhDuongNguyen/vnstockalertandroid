package com.dongbat.stockalert.data;

/**
 * Created by duongnb on 06/12/2015.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "stock";
    public static final String SIGNALS_TABLE_NAME = "signals";
    public static final String SIGNALS_COLUMN_TICKER = "ticker";
    public static final String SIGNALS_COLUMN_SMA10 = "sma10";
    public static final String SIGNALS_COLUMN_SMA20 = "sma20";
    public static final String SIGNALS_COLUMN_SMA10OVER20 = "sma10over20";
    public static final String SIGNALS_COLUMN_MACD = "macd";
    public static final String SIGNALS_COLUMN_STATE = "state";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table signals " +
                        "(ticker text,sma10 float,sma20 float, sma10over20 float, macd float, state float)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS signals");
        onCreate(db);
    }

    public boolean insertSignals  (SQLiteDatabase db, String ticker, float sma10, float sma20, float sma10over20, float macd,float state) {
//        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ticker", ticker);
        contentValues.put("sma10", sma10);
        contentValues.put("sma20", sma20);
        contentValues.put("sma10over20", sma10over20);
        contentValues.put("macd", macd);
        contentValues.put("state", state);
        db.insert("signals", null, contentValues);
        return true;
    }

    public Cursor getData(String ticker){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from signals where ticker="+ticker+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, SIGNALS_TABLE_NAME);
        return numRows;
    }

    public boolean updateSignals(SQLiteDatabase db, String ticker, float sma10, float sma20, float sma10over20, float macd, float state) {
//        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ticker", ticker);
        contentValues.put("sma10", sma10);
        contentValues.put("sma20", sma20);
        contentValues.put("sma10over20", sma10over20);
        contentValues.put("macd", macd);
        contentValues.put("state", state);
        db.update("signals", contentValues, "ticker = ? ", new String[] { ticker } );
        return true;
    }

    public Integer deleteSignals (String ticker) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("signals",
                "ticker = ? ",
                new String[] { ticker });
    }

    public ArrayList<String> getAllSignals(SQLiteDatabase db) {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
//        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from signals", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(SIGNALS_COLUMN_TICKER)));
            res.moveToNext();
        }
        return array_list;
    }
}
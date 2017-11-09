package net.a6te.lazycoder.andropos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.a6te.lazycoder.andropos.modelClass.StockDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.StockModel;

import java.util.ArrayList;

/**
 * Created by Programmer on 5/21/2017.
 */

public class Stock {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public static final String TAG = "AndroPos";

    public Stock(Context context) {
        dbHelper = new DBHelper(context);

    }
    private void Open(){
        database = dbHelper.getWritableDatabase();
    }
    private void Close(){
        database.close();
    }


    public boolean storeStock(StockDatabaseModel stock){

        this.Open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COL_ST_PRODUCT_ID,stock.getProductId());
        contentValues.put(dbHelper.COL_ST_PRODUCT_TYPE,stock.getProductType());
        contentValues.put(dbHelper.COL_ST_QUANTITY,stock.getProductQuantity());
        contentValues.put(dbHelper.COL_ST_PRODUCT_FOR,stock.getProductFor());

        long id = database.insert(dbHelper.TABLE_ST_NAME,null,contentValues);

        this.Close();
        if(id > 0){
            Log.d(TAG, "stock: ------------ new Stock inserted");
            return true;
        }else {
            Log.d(TAG, "store: ------------ new stock insertion failed");

            return false;
        }
    }
//
//
//    //stock database required variable name
//    public static final String TABLE_ST_NAME = "stock";
//    public static final String COL_ST_ID = "Id";
//    public static final String COL_ST_PRODUCT_ID = "product_id";
//    public static final String COL_ST_PRODUCT_TYPE = "product_type";
//    public static final String COL_ST_QUANTITY = "quantity";
//    public static final String COL_ST_PRODUCT_FOR = "product_for";
//
//    private int sId;
//    private String pName;
//    private String brandName;
//    private String pCode;
//    private String pSize;
//    private String stockLimit;
//

    public ArrayList<StockModel> getStocks() {

        this.Open();

        ArrayList<StockModel> stocks= new ArrayList<>();

        try {
            Cursor stockTableCursor = database.query(dbHelper.TABLE_ST_NAME, null, null, null, null, null, null);

            stockTableCursor.moveToFirst();
            int productSerial=0;

            if (stockTableCursor.getCount() > 0) {
                for (int i = 0; stockTableCursor.getCount() > i; i++) {
                    Cursor productTableCursor = database.query(dbHelper.TABLE_P_NAME, null, dbHelper.COL_P_CODE+"=?", new String[] {stockTableCursor.getString(stockTableCursor.getColumnIndex(dbHelper.COL_ST_PRODUCT_ID))}, null, null, null);

                    productTableCursor.moveToFirst();
                    if (productTableCursor.getCount() > 0) {
                            stocks.add(new StockModel(
                                    productTableCursor.getString(productTableCursor.getColumnIndex(dbHelper.COL_P_NAME)),
                                    productTableCursor.getString(productTableCursor.getColumnIndex(dbHelper.COL_P_BRAND)),
                                    ++productSerial+"",
                                    productTableCursor.getString(productTableCursor.getColumnIndex(dbHelper.COL_P_SIZE)),
                                    stockTableCursor.getString(stockTableCursor.getColumnIndex(dbHelper.COL_ST_QUANTITY))+" "+
                                            productTableCursor.getString(productTableCursor.getColumnIndex(dbHelper.COL_P_UNIT)))
                            );

                    }
                    stockTableCursor.moveToNext();

                }
            }

            stockTableCursor.close();
            this.Close();
            int get = stockTableCursor.getCount();
            if (get > 0) {
                return stocks;
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.d(TAG, "getStocks: "+e);
            return null;

        }

    }
    public ArrayList<StockModel> getStocksForSendData() {// TODO: 7/13/2017  don,t need to use stockModel class we just need stock limit and stock code need to update it later


        this.Open();

        ArrayList<StockModel> stocks= new ArrayList<>();

        try {
            Cursor stockTableCursor = database.query(dbHelper.TABLE_ST_NAME, null, null, null, null, null, null);

            stockTableCursor.moveToFirst();
            String productCode;
            if (stockTableCursor.getCount() > 0) {
                for (int i = 0; stockTableCursor.getCount() > i; i++) {
                    Cursor productTableCursor = database.query(dbHelper.TABLE_P_NAME, null, dbHelper.COL_P_CODE+"=?", new String[] {stockTableCursor.getString(stockTableCursor.getColumnIndex(dbHelper.COL_ST_PRODUCT_ID))}, null, null, null);
                    productCode = stockTableCursor.getString(stockTableCursor.getColumnIndex(dbHelper.COL_ST_PRODUCT_ID));
                    Log.d(TAG, "sendDataToWeb: "+productCode);
                    productTableCursor.moveToFirst();
                    if (productTableCursor.getCount() > 0) {
                            stocks.add(new StockModel(
                                    productTableCursor.getString(productTableCursor.getColumnIndex(dbHelper.COL_P_NAME)),
                                    productTableCursor.getString(productTableCursor.getColumnIndex(dbHelper.COL_P_BRAND)),
                                    productCode,
                                    productTableCursor.getString(productTableCursor.getColumnIndex(dbHelper.COL_P_SIZE)),
                                    stockTableCursor.getString(stockTableCursor.getColumnIndex(dbHelper.COL_ST_QUANTITY)))
                            );

                    }
                    stockTableCursor.moveToNext();

                }
            }

            stockTableCursor.close();
            this.Close();
            int get = stockTableCursor.getCount();
            if (get > 0) {
                return stocks;
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.d(TAG, "getStocks: "+e);
            return null;

        }

    }



    public boolean haveAnyStock(){
        this.Open();
        try {
            Cursor stockTableCursor = database.query(dbHelper.TABLE_ST_NAME, null, null, null, null, null, null);
            stockTableCursor.moveToFirst();

            int temp = stockTableCursor.getCount();

            stockTableCursor.close();
            this.Close();

            if (temp>0){
                return true;
            }else return false;
        }catch (Exception e){

            Log.d(TAG, "haveAnyStock: "+e);
            return false;
        }

    }

    public boolean updateStock(String productId,String quantity){
        this.Open();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_ST_QUANTITY,quantity);
            int value = database.update(dbHelper.TABLE_ST_NAME,contentValues,String.format("%s = ?", dbHelper.COL_ST_PRODUCT_ID),new String[]{productId});
            this.Close();
            if (value > 0) return true;
            else return false;
        }catch (Exception e){
            return false;
        }

    }
    public boolean deleteStock(String productId){
        this.Open();
        try {
            int value = database.delete(dbHelper.TABLE_ST_NAME,String.format("%s = ?", dbHelper.COL_ST_PRODUCT_ID),new String[]{productId});
            this.Close();
            if (value > 0) return true;
            else return false;
        }catch (Exception e){
            return false;
        }
    }

}

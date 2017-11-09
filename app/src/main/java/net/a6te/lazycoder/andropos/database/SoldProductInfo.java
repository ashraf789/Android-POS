package net.a6te.lazycoder.andropos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.a6te.lazycoder.andropos.modelClass.SoldProductModel;

import java.util.ArrayList;

/**
 * Created by Programmer on 6/18/2017.
 */

public class SoldProductInfo {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public static final String TAG = "AndroPos";

    public SoldProductInfo(Context context) {
        dbHelper = new DBHelper(context);

    }
    private void Open(){
        database = dbHelper.getWritableDatabase();
    }
    private void Close(){
        database.close();
    }


//    public static final String COL_SOLD_PRODUCT_CODE = "sells_code";
//    public static final String COL_SOLD_PRODUCT_SELL_ID = "sells_id";
//    public static final String COL_SOLD_PRODUCT_PRODUCT_ID = "sells_product_id";
//    public static final String COL_SOLD_PRODUCT_PRICE = "product_price";
//    public static final String COL_SOLD_PRODUCT_QUANTITY = "quantity";
//    public static final String COL_SOLD_PRODUCT_TOTAL_PRICE = "total_price";
//    public static final String COL_SOLD_PRODUCT_PENDING_STATUS = "pending_status";
//
    public boolean storeSoldProductInfo(SoldProductModel soldProduct){

        this.Open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COL_SOLD_PRODUCT_CODE,soldProduct.getProductSellsCode());
        contentValues.put(dbHelper.COL_SOLD_PRODUCT_SELL_ID,soldProduct.getProductSellId());
        contentValues.put(dbHelper.COL_SOLD_PRODUCT_PRODUCT_ID,soldProduct.getProductId());
        contentValues.put(dbHelper.COL_SOLD_PRODUCT_PRICE,soldProduct.getProductPrice());
        contentValues.put(dbHelper.COL_SOLD_PRODUCT_QUANTITY,soldProduct.getProductQuantity());
        contentValues.put(dbHelper.COL_SOLD_PRODUCT_TOTAL_PRICE,soldProduct.getProductTotalPrice());
        contentValues.put(dbHelper.COL_SOLD_PRODUCT_PENDING_STATUS,soldProduct.getProductPendingStatus());

        long id = database.insert(dbHelper.TABLE_SOLD_PRODUCT_NAME,null,contentValues);

        this.Close();
        if(id > 0){
            Log.d(TAG, "SoldProductInfo: ------------ new SoldINfo inserted");
            return true;
        }else {
            Log.d(TAG, "SoldProductInfo: ------------ new SoldINfo insertion failed");

            return false;
        }
    }


//            this.productSellsCode = productSellsCode;
//        this.productSellId = productSellId;
//        this.productId = productId;
//        this.productPrice = productPrice;
//        this.productQuantity = productQuantity;
//        this.productTotalPrice = productTotalPrice;
//        this.productPendingStatus = productPendingStatus;
//
    public ArrayList<SoldProductModel> getAllSoldProductInfo(){
        this.Open();
        ArrayList<SoldProductModel> allSoldProduct = new ArrayList<>();
        Cursor cursor = database.query(dbHelper.TABLE_SOLD_PRODUCT_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();

        int temp = cursor.getCount();
        for (int i = 0; i<temp; i++){
            SoldProductModel soldProduct = new SoldProductModel(cursor.getString(cursor.getColumnIndex(dbHelper.COL_SOLD_PRODUCT_CODE)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COL_SOLD_PRODUCT_SELL_ID)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COL_SOLD_PRODUCT_PRODUCT_ID)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COL_SOLD_PRODUCT_PRICE)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COL_SOLD_PRODUCT_QUANTITY)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COL_SOLD_PRODUCT_TOTAL_PRICE)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COL_SOLD_PRODUCT_PENDING_STATUS))
                    );
            allSoldProduct.add(soldProduct);
            cursor.moveToNext();
        }
        this.Close();
        if (temp > 0 )return allSoldProduct;
        else return null;
    }


    public boolean haveAnySoldInfo(){
        this.Open();
        try {
            Cursor cursor = database.query(dbHelper.TABLE_SOLD_PRODUCT_NAME, null, null, null, null, null, null);
            cursor.moveToFirst();

            int temp = cursor.getCount();

            cursor.close();
            this.Close();

            if (temp>0){
                return true;
            }else return false;
        }catch (Exception e){

            Log.d(TAG, "haveAnyStock: "+e);
            return false;
        }

    }
}

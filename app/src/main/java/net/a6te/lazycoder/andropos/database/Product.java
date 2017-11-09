package net.a6te.lazycoder.andropos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.a6te.lazycoder.andropos.modelClass.ProductDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.ProductListModel;
import net.a6te.lazycoder.andropos.modelClass.StockDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.StockModel;

import java.util.ArrayList;

/**
 * Created by Programmer on 5/21/2017.
 */

public class Product {
    public static final String TAG = "AndroPos";
    private DBHelper dbHelper;
    private SQLiteDatabase database;


    public Product(Context context) {
        dbHelper = new DBHelper(context);

    }
    private void Open(){
        database = dbHelper.getWritableDatabase();
    }
    private void Close(){
        database.close();
    }
    //product database required variable name
//    public static final String COL_P_NAME = "product_name";
//    public static final String COL_P_CODE = "product_code";
//    public static final String COL_P_PRICE = "product_price";
//    public static final String COL_P_SELL_PRICE = "sell_price";
//    public static final String COL_P_UNIT = "unit";
//    public static final String COL_P_BRAND = "brand";
//    public static final String COL_P_SIZE = "size";

    public boolean storeProductInfo(ProductDatabaseModel product){

        this.Open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COL_P_NAME,product.getProductName());
        contentValues.put(dbHelper.COL_P_CODE,product.getProductCode());
        contentValues.put(dbHelper.COL_P_PRICE,product.getProductPrice());
        contentValues.put(dbHelper.COL_P_SELL_PRICE,product.getProductSellPrice());
        contentValues.put(dbHelper.COL_P_UNIT,product.getProductUnit());
        contentValues.put(dbHelper.COL_P_BRAND,product.getProductBrand());
        contentValues.put(dbHelper.COL_P_SIZE,product.getProductSize());

        long id = database.insert(dbHelper.TABLE_P_NAME,null,contentValues);

        this.Close();
        if(id > 0){
            Log.d(TAG, "Product: ------------ new Product inserted");
            return true;
        }else {
            Log.d(TAG, "Product: ------------ new Product inserted failed");
            return false;
        }
    }

//
//    public ArrayList<ProductDatabaseModel> getProducts() {
//        this.Open();
//        ArrayList<ProductDatabaseModel> products= new ArrayList<>();
//        try {
//
//            Cursor cursor = database.query(dbHelper.TABLE_P_NAME, null, null, null, null, null, null);
//            cursor.moveToFirst();
//            int productSerial=0;
//
//            int totalProduct = cursor.getCount();
//            if (cursor.getCount() > 0) {
//                for (int i = 0;  totalProduct> i; i++) {
//                    products.add(new ProductDatabaseModel(cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_NAME)),
//                            cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_CODE)),
//                            cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_PRICE)),
//                            cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_SELL_PRICE)),
//                            cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_UNIT)),
//                            cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_BRAND)),
//                            cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_SIZE))
//                    ));
//                    cursor.moveToNext();
//                }
//            }
//
//            cursor.close();
//            this.Close();
//            int get = cursor.getCount();
//            if (get > 0) {
//                return products;
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            Log.d(TAG, "getProducts: "+e);
//            return null;
//
//        }
//    }
//
public ArrayList<ProductDatabaseModel> getProducts() {
        this.Open();
        ArrayList<ProductDatabaseModel> products= new ArrayList<>();
        try {

            Cursor stockTableCursor = database.query(dbHelper.TABLE_ST_NAME, null, null, null, null, null, null);
            stockTableCursor.moveToFirst();
            if (stockTableCursor.getCount() > 0) {
                for (int i = 0; stockTableCursor.getCount() > i; i++) {

                    Cursor cursor = database.query(dbHelper.TABLE_P_NAME, null, dbHelper.COL_P_CODE+"=?", new String[] {stockTableCursor.getString(stockTableCursor.getColumnIndex(dbHelper.COL_ST_PRODUCT_ID))}, null, null, null);
                    cursor.moveToFirst();

                    if (cursor.getCount() > 0) {
                        products.add(new ProductDatabaseModel(cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_NAME)),
                                cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_CODE)),
                                cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_PRICE)),
                                cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_SELL_PRICE)),
                                cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_UNIT)),
                                cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_BRAND)),
                                cursor.getString(cursor.getColumnIndex(dbHelper.COL_P_SIZE)),
                                stockTableCursor.getString(stockTableCursor.getColumnIndex(dbHelper.COL_ST_QUANTITY))
                        ));
                        cursor.moveToNext();

                    }
                    stockTableCursor.moveToNext();

                }
            }

            int get = stockTableCursor.getCount();

            stockTableCursor.close();
            this.Close();

            if (get > 0) {
                return products;
            } else {
                return null;
            }
        } catch (Exception e) {
            Log.d(TAG, "getProducts: "+e);
            return null;

        }
    }

    public ProductListModel getProductDetails(String productCode) {

        this.Open();

        ProductListModel product;
        try {
            Cursor productCursor = database.query(dbHelper.TABLE_P_NAME, null, dbHelper.COL_P_CODE + "=?", new String[]{productCode}, null, null, null);
            Cursor stockCursor = database.query(dbHelper.TABLE_ST_NAME, null, dbHelper.COL_ST_PRODUCT_ID + "=?", new String[]{productCode}, null, null, null);

            productCursor.moveToFirst();
            stockCursor.moveToFirst();

            if (productCursor.getCount() > 0 && stockCursor.getCount() > 0) {

                product = new ProductListModel(
                        productCursor.getString(productCursor.getColumnIndex(dbHelper.COL_P_PRICE)),
                        productCursor.getString(productCursor.getColumnIndex(dbHelper.COL_P_NAME)),
                        productCursor.getString(productCursor.getColumnIndex(dbHelper.COL_P_BRAND)),
                        productCursor.getString(productCursor.getColumnIndex(dbHelper.COL_P_SIZE)),
                        productCursor.getString(productCursor.getColumnIndex(dbHelper.COL_P_UNIT)),
                        stockCursor.getString(stockCursor.getColumnIndex(dbHelper.COL_ST_QUANTITY)),
                        stockCursor.getString(stockCursor.getColumnIndex(dbHelper.COL_ST_PRODUCT_ID))
                );

                productCursor.close();
                stockCursor.close();

                this.Close();
                int get = productCursor.getCount();
                if (get > 0) {
                    return product;
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, "getStocks: " + e);
            return null;

        }
        return null;
    }

    public boolean haveAnyProduct(){
        this.Open();
        try {
            Cursor cursor = database.query(dbHelper.TABLE_ST_NAME, null, null, null, null, null, null);
            cursor.moveToFirst();

            int temp = cursor.getCount();

            cursor.close();
            this.Close();

            if (temp>0){
                return true;
            }else return false;
        }catch (Exception e){

            Log.d(TAG, "haveAnyProduct: "+e);
            return false;
        }

    }
}

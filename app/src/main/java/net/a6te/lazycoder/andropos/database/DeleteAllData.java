package net.a6te.lazycoder.andropos.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Programmer on 6/19/2017.
 */

public class DeleteAllData {
    private DBHelper dbHelper;
    private SQLiteDatabase database;

    public static final String TAG = "AndroPos";

    public DeleteAllData(Context context) {
        dbHelper = new DBHelper(context);

    }
    private void Open(){
        database = dbHelper.getWritableDatabase();
    }
    private void Close(){
        database.close();
    }


    public void deleteStockData(){
        this.Open();
        try {
            database.delete(dbHelper.TABLE_ST_NAME,null,null);
        }catch (Exception e){
            Log.d(TAG, "deleteAllData: "+e);
        }

        this.Close();

    }

    public void deleteCustomer(){
        this.Open();
        try {
            database.delete(dbHelper.TABLE_CT_NAME,null,null);

        }catch (Exception e){

            Log.d(TAG, "deleteAllData: "+e);
        }

        this.Close();
    }
}

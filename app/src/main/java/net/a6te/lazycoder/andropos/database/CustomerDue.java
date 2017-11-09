package net.a6te.lazycoder.andropos.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.a6te.lazycoder.andropos.modelClass.CustomerDuelDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.DueDetailsModel;
import net.a6te.lazycoder.andropos.modelClass.DueModel;

import java.util.ArrayList;

import static net.a6te.lazycoder.andropos.database.Stock.TAG;

/**
 * Created by Programmer on 5/23/2017.
 */

public class CustomerDue {
    private DBHelper dbHelper;
    private SQLiteDatabase database;


    public CustomerDue(Context context) {
        dbHelper = new DBHelper(context);

    }
    private void Open(){
        database = dbHelper.getWritableDatabase();
    }
    private void Close(){
        database.close();
    }

    public boolean storeSellsDetails(CustomerDuelDatabaseModel sell){

        this.Open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COL_DUE_CUSTOMER_ID,sell.getCustomerId());
        contentValues.put(dbHelper.COL_DUE_TOTAL_AMOUNT,sell.getTotalAmount());
        contentValues.put(dbHelper.COL_DUE_PAY_AMOUNT,sell.getTotalPayAmount());
        contentValues.put(dbHelper.COL_DUE_DUE,sell.getDueAmount());
        contentValues.put(dbHelper.COL_DUE_SELLS_CODE,sell.getSellsCode());
        contentValues.put(dbHelper.COL_DUE_PAY_DUE_DATE,sell.getPayDueDate());
        contentValues.put(dbHelper.COL_DUE_DEPOSIT,sell.getPayDeposit());

        long id = database.insert(dbHelper.TABLE_DUE_INFO_NAME,null,contentValues);

        this.Close();
        if(id > 0){
            Log.d(TAG, "CustomerDue: ------------ new CustomerDue details inserted");
            return true;
        }else {
            Log.d(TAG, "CustomerDue: ------------ new CustomerDue details inserted failed");
            return false;
        }
    }


    public ArrayList<DueModel> getDueList(){
        ArrayList<DueModel> dues = new ArrayList<>();

        this.Open();

        Cursor cursorDue = database.query(dbHelper.TABLE_DUE_INFO_NAME,null,null,null,null,null,null);
        cursorDue.moveToFirst();
        int count = cursorDue.getCount();

        if (count > 0){
            for (int i = 0; i < count; i++){
                Cursor cursorCustomer = database.query(dbHelper.TABLE_CT_NAME, null, dbHelper.COL_CT_CODE + "=?", new String[]{cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_CUSTOMER_ID))}, null, null, null);
                cursorCustomer.moveToFirst();

                dues.add(new DueModel(
                        String.valueOf((i+1)),
                        cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_DUE)),
                        cursorCustomer.getString(cursorCustomer.getColumnIndex(dbHelper.COL_CT_NAME)),
                        cursorCustomer.getString(cursorCustomer.getColumnIndex(dbHelper.COL_CT_PHONE)),
                        cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_ID))
                ));

                cursorDue.moveToNext();
            }
        }
        cursorDue.close();
        this.Close();

        if (count > 0){
            return dues;
        }else return null;
    }

    public boolean haveDue(){

        this.Open();
        try {Cursor cursor = database.query(dbHelper.TABLE_DUE_INFO_NAME, null, null, null, null, null, null);
            cursor.moveToFirst();
            int temp = cursor.getCount();

            cursor.close();
            this.Close();

            if (temp > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;

        }
    }

    //    private String name;
//    private String phone;
//    private String email;
//    private String type;
//    private String amount;
//    private String pay;
//    private String date;
//    private String due;
//
    public DueDetailsModel getDueDetails(String dueCode){

        DueDetailsModel dueDetails = new DueDetailsModel();
        this.Open();

        Cursor cursorDue = database.query(dbHelper.TABLE_DUE_INFO_NAME,null,dbHelper.COL_DUE_ID + "=?",new String []{dueCode},null,null,null);
        cursorDue.moveToFirst();
        int count = cursorDue.getCount();

        if (count > 0){
            Cursor cursorCustomer = database.query(dbHelper.TABLE_CT_NAME, null, dbHelper.COL_CT_CODE + "=?", new String[]{cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_CUSTOMER_ID))}, null, null, null);
            cursorCustomer.moveToFirst();

            dueDetails = new DueDetailsModel(
                    cursorCustomer.getString(cursorCustomer.getColumnIndex(dbHelper.COL_CT_NAME)),
                    cursorCustomer.getString(cursorCustomer.getColumnIndex(dbHelper.COL_CT_PHONE)),
                    cursorCustomer.getString(cursorCustomer.getColumnIndex(dbHelper.COL_CT_EMAIL)),
                    cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_SELLS_CODE)),
                    cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_PAY_AMOUNT)),
                    cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_DEPOSIT)),
                    cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_PAY_DUE_DATE)),
                    cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_DUE)),
                    cursorCustomer.getString(cursorCustomer.getColumnIndex(dbHelper.COL_CT_CODE))
            );
        }
        cursorDue.close();
        this.Close();

        if (count > 0){
            return dueDetails;
        }else return null;
    }

    public ArrayList<DueDetailsModel> getAllDueDetails(){

        ArrayList<DueDetailsModel> allDue = new ArrayList<>();
        this.Open();

        Cursor cursorDue = database.query(dbHelper.TABLE_DUE_INFO_NAME,null,null,null,null,null,null);
        cursorDue.moveToFirst();

        int count = cursorDue.getCount();

        for (int i = 0; i<count; i++){

            DueDetailsModel dueDetails;
            Cursor cursorCustomer = database.query(dbHelper.TABLE_CT_NAME, null, dbHelper.COL_CT_CODE + "=?", new String[]{cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_CUSTOMER_ID))}, null, null, null);
            cursorCustomer.moveToFirst();

            dueDetails = new DueDetailsModel(
                    cursorCustomer.getString(cursorCustomer.getColumnIndex(dbHelper.COL_CT_NAME)),
                    cursorCustomer.getString(cursorCustomer.getColumnIndex(dbHelper.COL_CT_PHONE)),
                    cursorCustomer.getString(cursorCustomer.getColumnIndex(dbHelper.COL_CT_EMAIL)),
                    cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_SELLS_CODE)),
                    cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_PAY_AMOUNT)),
                    cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_DEPOSIT)),
                    cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_PAY_DUE_DATE)),
                    cursorDue.getString(cursorDue.getColumnIndex(dbHelper.COL_DUE_DUE)),
                    cursorCustomer.getString(cursorCustomer.getColumnIndex(dbHelper.COL_CT_CODE))
            );

            allDue.add(dueDetails);
            cursorDue.moveToNext();
        }

        cursorDue.close();
        this.Close();

        if (count > 0){
            return allDue;
        }else return null;
    }

    public boolean updateDueDetails(String newDue,String newPaid,String dueCode){
        this.Open();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.COL_DUE_DUE,newDue);
            contentValues.put(dbHelper.COL_DUE_DEPOSIT,newPaid);

            int value = database.update(dbHelper.TABLE_DUE_INFO_NAME,contentValues,String.format(dbHelper.COL_DUE_ID+" = ?"),new String[]{dueCode});
            this.Close();
            if (value > 0) return true;
            else return false;
        }catch (Exception e){
            return false;
        }

    }
    public boolean deleteDue(String dueCode){
        this.Open();
        try {
            int value = database.delete(dbHelper.TABLE_DUE_INFO_NAME,String.format("%s = ?", dbHelper.COL_DUE_ID),new String[]{dueCode});
            this.Close();
            if (value > 0) return true;
            else return false;
        }catch (Exception e){
            return false;
        }

    }

    public boolean haveAnyData(){

        this.Open();
        try {
            Cursor cursor = database.query(dbHelper.TABLE_DUE_INFO_NAME, null, null, null, null, null, null);
            cursor.moveToFirst();

            int temp = cursor.getCount();

            cursor.close();
            this.Close();

            if (temp>0){
                return true;
            }else return false;
        }catch (Exception e){

            Log.d(TAG, "haveAnyData: "+e);
            return false;
        }

    }
}

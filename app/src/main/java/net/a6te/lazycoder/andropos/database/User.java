package net.a6te.lazycoder.andropos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.a6te.lazycoder.andropos.modelClass.UserDatabaseModel;

import static net.a6te.lazycoder.andropos.database.Stock.TAG;

/**
 * Created by Programmer on 5/23/2017.
 */

public class User {

    private DBHelper dbHelper;
    private SQLiteDatabase database;


    public User(Context context) {
        dbHelper = new DBHelper(context);

    }
    private void Open(){
        database = dbHelper.getWritableDatabase();
    }
    private void Close(){
        database.close();
    }

    public boolean createNewUser(UserDatabaseModel user){

        this.Open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.COL_U_NAME,user.getUserName());
        contentValues.put(dbHelper.COL_U_EMAIL,user.getUserEmail());
        contentValues.put(dbHelper.COL_U_PASSWORD,user.getUserPassword());
        contentValues.put(dbHelper.COL_U_PHONE,user.getUserPhone());
        contentValues.put(dbHelper.COL_U_EMPLOYEE_ID,user.getUserEmployeeId());

        long id = database.insert(dbHelper.TABLE_USER_NAME,null,contentValues);

        this.Close();
        if(id > 0){
            Log.d(TAG, "User: ------------ new User details inserted");
            return true;
        }else {
            Log.d(TAG, "User: ------------ new User details inserted failed");
            return false;
        }
    }


//    private String userName;
//    private String userEmail;
//    private String userPassword;
//    private String userPhone;
//    private String userEmployeeId;

    public UserDatabaseModel getUserDetails(){
        UserDatabaseModel user;
        this.Open();

        try {
            Cursor cursor = database.query(dbHelper.TABLE_USER_NAME,null,null,null,null,null,null);
            cursor.moveToFirst();

            user = new UserDatabaseModel(cursor.getString(cursor.getColumnIndex(dbHelper.COL_U_NAME)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COL_U_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COL_U_PASSWORD)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COL_U_PHONE)),
                    cursor.getString(cursor.getColumnIndex(dbHelper.COL_U_EMPLOYEE_ID))
            );
            int temp = cursor.getCount();
            cursor.close();
            this.Close();


            if (temp > 0) return user;
            else return null;
        }catch (Exception e){
            return null;
        }
    }

    public boolean haveAnySeller(){
        this.Open();
        try {
            Cursor cursor = database.query(dbHelper.TABLE_SELL_NAME, null, null, null, null, null, null);
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

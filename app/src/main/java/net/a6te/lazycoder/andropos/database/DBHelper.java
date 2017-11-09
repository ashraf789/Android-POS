package net.a6te.lazycoder.andropos.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Programmer on 5/21/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "androPos";
    public static final int DB_VERSION = 1;



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


//   --------------all database column name --------------------
//    //stock database required variable name
//    public static final String COL_ST_PRODUCT_ID = "product_id";
//    public static final String COL_ST_PRODUCT_TYPE = "product_type";
//    public static final String COL_ST_QUANTITY = "quantity";
//    public static final String COL_ST_PRODUCT_FOR = "product_for";//employee id
//
//
//    //customer database required variable name
//    public static final String COL_CT_NAME = "customer_name";
//    public static final String COL_CT_CODE = "customer_code";
//    public static final String COL_CT_TYPE_ = "customer_type";
//    public static final String COL_CT_GENDER = "gender";
//    public static final String COL_CT_PHONE = "phone";
//    public static final String COL_CT_EMAIL = "email";
//    public static final String COL_CT_ADDRESS = "address";
//    public static final String COL_CT_DUE_AMOUNT = "due_amount";
//
//
//    //product database required variable name
//    public static final String COL_P_NAME = "product_name";
//    public static final String COL_P_CODE = "product_code";
//    public static final String COL_P_PRICE = "product_price";
//    public static final String COL_P_SELL_PRICE = "sell_price";
//    public static final String COL_P_UNIT = "unit";
//    public static final String COL_P_BRAND = "brand";
//    public static final String COL_P_SIZE = "size";
//
//
//
//
//    //due database required variable name
//    public static final String COL_DUE_CUSTOMER_ID = "customer_id";
//    public static final String COL_DUE_TOTAL_AMOUNT = "total_amount";
//    public static final String COL_DUE_PAY_AMOUNT = "pay_amount";
//    public static final String COL_DUE_DUE = "due";
//    public static final String COL_DUE_SELLS_CODE = "sells_code";
//    public static final String COL_DUE_PAY_DUE_DATE = "pay_due_date";
//    public static final String COL_DUE_DEPOSIT = "due_deposit";
//
//
//
//    //sell database database required variable name
//    public static final String COL_SELL_SELLS_CODE = "sells_code";
//    public static final String COL_SELL_CUSTOMER_ID = "customer_id";
//    public static final String COL_SELL_AMOUNT = "total_amount";
//    public static final String COL_SELL_DISCOUNT = "discount";
//    public static final String COL_SELL_PAY_AMOUNT = "pay_amount";
//    public static final String COL_SELL_PAYMENT_TYPE = "payment_type";
//    public static final String COL_SELL_SELL_DATE = "sell_date";
//    public static final String COL_SELL_PAYMENT_STATUS = "payment_status";
//    public static final String COL_SELL_SELL_BY = "sell_by";//employee id
//
//
//    //sell product info database database required variable name
//    public static final String COL_SOLD_PRODUCT_CODE = "sells_code";
//    public static final String COL_SOLD_PRODUCT_SELL_ID = "sells_id";
//    public static final String COL_SOLD_PRODUCT_PRODUCT_ID = "sells_product_id";
//    public static final String COL_SOLD_PRODUCT_PRICE = "product_price";
//    public static final String COL_SOLD_PRODUCT_QUANTITY = "quantity";
//    public static final String COL_SOLD_PRODUCT_TOTAL_PRICE = "total_price";
//    public static final String COL_SOLD_PRODUCT_PENDING_STATUS = "pending_status";
//






    //stock database required variable name
    public static final String TABLE_ST_NAME = "stock";
    public static final String COL_ST_ID = "Id";
    public static final String COL_ST_PRODUCT_ID = "product_id";
    public static final String COL_ST_PRODUCT_TYPE = "product_type";
    public static final String COL_ST_QUANTITY = "quantity";
    public static final String COL_ST_PRODUCT_FOR = "product_for";

    public static final String STOCK_TABLE_QUERY = "Create Table "+TABLE_ST_NAME+" ( "
            +COL_ST_ID+" Integer primary key, "+COL_ST_PRODUCT_ID+" Text, "
            +COL_ST_PRODUCT_TYPE+" Text, "+COL_ST_QUANTITY+" Text, "+ COL_ST_PRODUCT_FOR +" Text);";



    //customer database required variable name
    public static final String TABLE_CT_NAME = "customer";
    public static final String COL_CT_ID = "ctId";
    public static final String COL_CT_NAME = "customer_name";
    public static final String COL_CT_CODE = "customer_code";
    public static final String COL_CT_TYPE_ = "customer_type";
    public static final String COL_CT_GENDER = "gender";
    public static final String COL_CT_PHONE = "phone";
    public static final String COL_CT_EMAIL = "email";
    public static final String COL_CT_ADDRESS = "address";
    public static final String COL_CT_DUE_AMOUNT = "due_amount";

    public static final String CUSTOMER_TABLE_QUERY = "Create Table "+ TABLE_CT_NAME +" ( "
            +COL_CT_ID+" Integer primary key, "+COL_CT_NAME+" Text, "+COL_CT_CODE+" Text, "
            +COL_CT_TYPE_+" Text, "+COL_CT_GENDER+" Text, "+COL_CT_PHONE+" Text, "
            +COL_CT_EMAIL+" Text, "+COL_CT_ADDRESS+" Text, "+COL_CT_DUE_AMOUNT +" Text);";


    //product database required variable name
    public static final String TABLE_P_NAME = "product";
    public static final String COL_P_ID = "id";
    public static final String COL_P_NAME = "product_name";
    public static final String COL_P_CODE = "product_code";
    public static final String COL_P_PRICE = "product_price";
    public static final String COL_P_SELL_PRICE = "sell_price";
    public static final String COL_P_UNIT = "unit";
    public static final String COL_P_BRAND = "brand";
    public static final String COL_P_SIZE = "size";

    public static final String PRODUCT_TABLE_QUERY = "Create Table "+TABLE_P_NAME+" ( "
            +COL_P_ID+" Integer primary key, "+COL_P_NAME+" Text, "+COL_P_CODE+" Text, "
            +COL_P_PRICE+" Text, "+COL_P_SELL_PRICE+" Text, "+COL_P_UNIT+" Text, "
            +COL_P_BRAND+" Text, "+COL_P_SIZE +" Text);";



    //due database required variable name
    public static final String TABLE_DUE_INFO_NAME = "due_info";
    public static final String COL_DUE_ID = "id";
    public static final String COL_DUE_CUSTOMER_ID = "customer_id";
    public static final String COL_DUE_TOTAL_AMOUNT = "total_amount";
    public static final String COL_DUE_PAY_AMOUNT = "pay_amount";
    public static final String COL_DUE_DUE = "due";
    public static final String COL_DUE_SELLS_CODE = "sells_code";
    public static final String COL_DUE_PAY_DUE_DATE = "pay_due_date";
    public static final String COL_DUE_DEPOSIT = "due_deposit";

    public static final String SELL_TABLE_QUERY = "Create Table "+ TABLE_DUE_INFO_NAME +" ( "
            + COL_DUE_ID +" Integer primary key, "+ COL_DUE_CUSTOMER_ID +" Text, "+ COL_DUE_TOTAL_AMOUNT +" Text, "
            + COL_DUE_PAY_AMOUNT +" Text, "+ COL_DUE_DUE +" Text, "+ COL_DUE_SELLS_CODE +" Text, "
            + COL_DUE_PAY_DUE_DATE +" Text, "+ COL_DUE_DEPOSIT +" Text);";


    //user database required variable name
    public static final String TABLE_USER_NAME = "user";
    public static final String COL_U_ID = "id";
    public static final String COL_U_NAME = "user_name";
    public static final String COL_U_EMAIL = "email";
    public static final String COL_U_PASSWORD = "password";
    public static final String COL_U_PHONE = "phone";
    public static final String COL_U_EMPLOYEE_ID = "employee_id";

    public static final String USER_TABLE_QUERY = "Create Table "+TABLE_USER_NAME+" ( "
            +COL_U_ID+" Integer primary key, "+COL_U_NAME+" Text, "+COL_U_EMAIL+" Text, "
            +COL_U_PASSWORD+" Text, "+COL_U_PHONE+" Text, "+COL_U_EMPLOYEE_ID +" Text);";



    //sell database database required variable name
    public static final String TABLE_SELL_NAME = "sells";
    public static final String COL_SELL_ID = "id";
    public static final String COL_SELL_SELLS_CODE = "sells_code";
    public static final String COL_SELL_CUSTOMER_ID = "customer_id";
    public static final String COL_SELL_AMOUNT = "total_amount";
    public static final String COL_SELL_DISCOUNT = "discount";
    public static final String COL_SELL_PAY_AMOUNT = "pay_amount";
    public static final String COL_SELL_PAYMENT_TYPE = "payment_type";
    public static final String COL_SELL_SELL_DATE = "sell_date";
    public static final String COL_SELL_PAYMENT_STATUS = "payment_status";
    public static final String COL_SELL_SELL_BY = "sell_by";

    public static final String SELLS_TABLE_QUERY = "Create Table "+TABLE_SELL_NAME+" ( "
            +COL_SELL_ID+" Integer primary key, "+COL_SELL_SELLS_CODE+" Text, "+COL_SELL_CUSTOMER_ID+" Text, "
            +COL_SELL_AMOUNT+" Text, "+COL_SELL_DISCOUNT+" Text, "+COL_SELL_PAY_AMOUNT+" Text, "
            +COL_SELL_PAYMENT_TYPE+" Text, "+COL_SELL_SELL_DATE+" Text, "+COL_SELL_PAYMENT_STATUS+" Text, "
            +COL_SELL_SELL_BY +" Text);";




    //sell product info database database required variable name
    public static final String TABLE_SOLD_PRODUCT_NAME = "sell_product_info";
    public static final String COL_SOLD_PRODUCT_ID = "id";
    public static final String COL_SOLD_PRODUCT_CODE = "sells_code";
    public static final String COL_SOLD_PRODUCT_SELL_ID = "sells_id";
    public static final String COL_SOLD_PRODUCT_PRODUCT_ID = "sells_product_id";
    public static final String COL_SOLD_PRODUCT_PRICE = "product_price";
    public static final String COL_SOLD_PRODUCT_QUANTITY = "quantity";
    public static final String COL_SOLD_PRODUCT_TOTAL_PRICE = "total_price";
    public static final String COL_SOLD_PRODUCT_PENDING_STATUS = "pending_status";

    public static final String SOLD_PRODUCT_TABLE_QUERY = "Create Table "+TABLE_SOLD_PRODUCT_NAME+" ( "
            +COL_SOLD_PRODUCT_ID+" Integer primary key, "+COL_SOLD_PRODUCT_CODE+" Text, "+COL_SOLD_PRODUCT_SELL_ID+" Text, "
            +COL_SOLD_PRODUCT_PRODUCT_ID+" Text, "+COL_SOLD_PRODUCT_PRICE+" Text, "+COL_SOLD_PRODUCT_QUANTITY+" Text, "
            +COL_SOLD_PRODUCT_TOTAL_PRICE+" Text, "+COL_SOLD_PRODUCT_PENDING_STATUS +" Text);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STOCK_TABLE_QUERY);
        db.execSQL(CUSTOMER_TABLE_QUERY);
        db.execSQL(PRODUCT_TABLE_QUERY);
        db.execSQL(SELL_TABLE_QUERY);
        db.execSQL(USER_TABLE_QUERY);
        db.execSQL(SELLS_TABLE_QUERY);
        db.execSQL(SOLD_PRODUCT_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exist "+STOCK_TABLE_QUERY);
        db.execSQL("Drop table if exist "+CUSTOMER_TABLE_QUERY);
        db.execSQL("Drop table if exist "+PRODUCT_TABLE_QUERY);
        db.execSQL("Drop table if exist "+ SELL_TABLE_QUERY);
        db.execSQL("Drop table if exist "+USER_TABLE_QUERY);
        db.execSQL("Drop table if exist "+SELLS_TABLE_QUERY);
        db.execSQL("Drop table if exist "+SOLD_PRODUCT_TABLE_QUERY);

        onCreate(db);
    }


}

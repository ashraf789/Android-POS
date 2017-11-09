package net.a6te.lazycoder.andropos;

import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.a6te.lazycoder.andropos.database.Customer;
import net.a6te.lazycoder.andropos.database.CustomerDue;
import net.a6te.lazycoder.andropos.database.SellsInfo;
import net.a6te.lazycoder.andropos.database.SoldProductInfo;
import net.a6te.lazycoder.andropos.database.Stock;
import net.a6te.lazycoder.andropos.modelClass.CustomerModel;
import net.a6te.lazycoder.andropos.modelClass.DueDetailsModel;
import net.a6te.lazycoder.andropos.modelClass.DueModel;
import net.a6te.lazycoder.andropos.modelClass.SellsDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.SoldProductModel;
import net.a6te.lazycoder.andropos.modelClass.StockModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import static net.a6te.lazycoder.andropos.UserAuthentication.TAG;

/**
 * Created by Programmer on 7/8/2017.
 */

public class JsonData {
    private Stock stock;
    private Customer customer;
    private CustomerDue customerDue;
    private SellsInfo sellsInfo;
    private SoldProductInfo soldProductInfo;

    private Context context;
    public JsonData(Context context){
        this.context = context;

        stock = new Stock(context);
        customer = new Customer(context);
        customerDue = new CustomerDue(context);
        sellsInfo = new SellsInfo(context);
        soldProductInfo = new SoldProductInfo(context);
    }

    public JsonObject getDataAsJson(){
        JsonObject jsonData = new JsonObject();

        try {
            jsonData.add("stock",getStockArray(stock.getStocksForSendData()));
            jsonData.add("customer",getCustomer(customer.getAllCustomer()));
            jsonData.add("due",getDueInfo(customerDue.getAllDueDetails()));
            jsonData.add("sell_info",getSellInfo(sellsInfo.getAllSellInfo()));
            jsonData.add("sold_product_into",getSoldProductInfo(soldProductInfo.getAllSoldProductInfo()));
        }catch (Exception e){
            e.printStackTrace();
        }

        return jsonData;
    }

    public JsonArray getStockArray(ArrayList<StockModel> stockDb){

        JsonArray stocks = new JsonArray();
        try {
            for (StockModel stockData:stockDb
                    ) {

                JsonObject object = new JsonObject();
                object.addProperty("product_id",stockData.getpCode());
                object.addProperty("quantity",stockData.getStockLimit());
                stocks.add(object);
            }
        }catch (Exception e){
            JsonObject object = new JsonObject();
            stocks.add(object);
        }

        return stocks;
    }

    //convert customer data to json array
    public JsonArray getCustomer(ArrayList<CustomerModel> customerDb){
        JsonArray jsonArray = new JsonArray();

        try {
            for (CustomerModel customer :customerDb
                    ) {

                JsonObject object = new JsonObject();
                object.addProperty("customer_name",customer.getCustomerName());
                object.addProperty("customer_code",customer.getCustomerCode());
                object.addProperty("customer_type",customer.getCustomerType());
                object.addProperty("gender",customer.getCustomerGender());
                object.addProperty("phone",customer.getCustomerPhone());
                object.addProperty("email",customer.getCustomerEmail());
                object.addProperty("address",customer.getCustomerAddress());
                object.addProperty("due_amount",customer.getCustomerDueAmount());

                jsonArray.add(object);
            }
        }catch (Exception e){
            JsonObject object = new JsonObject();
            jsonArray.add(object);
        }

        return jsonArray;
    }

    //convert sell data to json array
    public JsonArray getDueInfo(ArrayList<DueDetailsModel> dueDb){
        JsonArray jsonArray = new JsonArray();

        try {
            for (DueDetailsModel due :dueDb
                    ) {

                JsonObject object = new JsonObject();
                object.addProperty("customer_id",due.getCustomerCode());
                object.addProperty("total_amount",due.getTotalAmount());
                object.addProperty("pay_amount",due.getPaid());
                object.addProperty("due",due.getDue());
                object.addProperty("sells_code",due.getSellCode());
                object.addProperty("pay_due_date",due.getDate());
                object.addProperty("due_deposit",due.getPaid());

                jsonArray.add(object);
            }
        }catch (Exception e){
            JsonObject object = new JsonObject();
            jsonArray.add(object);
        }

        return jsonArray;
    }
    //convert sell data to json array
    public JsonArray getSellInfo(ArrayList<SellsDatabaseModel> sellDb){
        JsonArray jsonArray = new JsonArray();

        try {

            for (SellsDatabaseModel sell :sellDb
                    ) {

                JsonObject object = new JsonObject();
                object.addProperty("sells_code",sell.getSellsCode());
                object.addProperty("customer_id",sell.getCustomerId());
                object.addProperty("total_amount",sell.getTotalAmount());
                object.addProperty("discount",sell.getDiscount());
                object.addProperty("pay_amount",sell.getPayAmount());
                object.addProperty("payment_type",sell.getPaymentType());
                object.addProperty("sell_date",sell.getSellDate());
                object.addProperty("payment_status",sell.getPaymentStatus());
                object.addProperty("sell_by",sell.getSellBy());

                jsonArray.add(object);
            }
        }catch (Exception e){
            JsonObject object = new JsonObject();
            jsonArray.add(object);
        }

        return jsonArray;
    }
    //convert sell data to json array
    public JsonArray getSoldProductInfo(ArrayList<SoldProductModel> soldDb){
        JsonArray jsonArray = new JsonArray();

        try {

            for (SoldProductModel soldProduct :soldDb
                    ) {

                JsonObject object = new JsonObject();
                object.addProperty("sells_code",soldProduct.getProductSellsCode());
                object.addProperty("sells_id",soldProduct.getProductSellId());
                object.addProperty("sells_product_id",soldProduct.getProductId());
                object.addProperty("product_price",soldProduct.getProductPrice());
                object.addProperty("quantity",soldProduct.getProductQuantity());
                object.addProperty("total_price",soldProduct.getProductTotalPrice());
                object.addProperty("pending_status",soldProduct.getProductPendingStatus());

                jsonArray.add(object);
            }
        }catch (Exception e){
            JsonObject object = new JsonObject();
            jsonArray.add(object);
        }

        return jsonArray;
    }


}

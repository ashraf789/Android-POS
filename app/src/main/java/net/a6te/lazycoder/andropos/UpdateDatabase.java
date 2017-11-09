package net.a6te.lazycoder.andropos;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import net.a6te.lazycoder.andropos.database.Customer;
import net.a6te.lazycoder.andropos.database.DeleteAllData;
import net.a6te.lazycoder.andropos.database.Product;
import net.a6te.lazycoder.andropos.database.SellsInfo;
import net.a6te.lazycoder.andropos.database.Stock;
import net.a6te.lazycoder.andropos.database.User;
import net.a6te.lazycoder.andropos.interfaces.RetrofitApiCaller;
import net.a6te.lazycoder.andropos.modelClass.CustomerModel;
import net.a6te.lazycoder.andropos.modelClass.CustomerOnlineDataModel;
import net.a6te.lazycoder.andropos.modelClass.ProductDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.RetrofitResponse;
import net.a6te.lazycoder.andropos.modelClass.StockDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.StockOnlineModel;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static net.a6te.lazycoder.andropos.UserAuthentication.BASE_URL;
import static net.a6te.lazycoder.andropos.database.Product.TAG;

/**
 * Created by Programmer on 6/19/2017.
 */

public class UpdateDatabase {
    private Stock stockDatabase;
    private Product productDatabase;
    private Customer customerDatabase;
    private SellsInfo sellsDatabase;
    private User user;

    private boolean customerDataReceived = false;
    private boolean sendData = false;
    private boolean loginDataReceived = false;

    private Context context;
    private String userId;
    private String employeeId;
    private DeleteAllData deleteAllData;

    private String loginUrl;
    private String customerUrl;
    private String sendDataUrl;
    private RetrofitApiCaller apiClient;

    public UpdateDatabase(Context context){
        stockDatabase = new Stock(context);
        productDatabase = new Product(context);
        customerDatabase = new Customer(context);
        sellsDatabase = new SellsInfo(context);
        this.context = context;
        user = new User(context);
        employeeId = user.getUserDetails().getUserEmployeeId();
        loginUrl = "posapi/public/stock/user/"+employeeId;
        customerUrl = "posapi/public/sale/pageinfo/user/"+employeeId;
        sendDataUrl = "posapi/public/sync/update/user/"+employeeId;
        deleteAllData = new DeleteAllData(context);

    }

    public void updateData(){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClient = retrofit.create(RetrofitApiCaller.class);

        sendDataToWebServer();//send local database data to web server

    }

    // download stock data from online server
    public void downloadStockData(){
        Call<ArrayList<StockOnlineModel>> stocks = apiClient.getStocks(loginUrl);

        if (!new CheckInternetConnection().netCheck(context)){
            Toast.makeText(context,"No Internet Connection",Toast.LENGTH_SHORT).show();
            return;
        }

        stocks.enqueue(new Callback<ArrayList<StockOnlineModel>>() {
            @Override
            public void onResponse(Call<ArrayList<StockOnlineModel>> call, Response<ArrayList<StockOnlineModel>> response) {
                sendMessage("stock");

                Log.d(TAG, "onResponse: user id = "+employeeId);
                Log.d(TAG, "onResponse: "+response.body());

                stockUpdate(response.body());
                sendCompleteMessage(30);

                loginDataReceived = true;

                Log.d(TAG, "onResponse: ------------ stock 30");
            }

            @Override
            public void onFailure(Call<ArrayList<StockOnlineModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: updateStock "+t.getMessage());
                sendCompleteMessage(30);
                Log.d(TAG, "onResponse: ------------ stock 30");
                customerDataReceived = true;
            }

            int i = 0;
        });

    }

    //make request for download customer data

    public void downloadCustomerData(){
        Call<CustomerOnlineDataModel> customers = apiClient.getCustomersInfo(customerUrl);
        customers.enqueue(new Callback<CustomerOnlineDataModel>() {
            @Override
            public void onResponse(Call<CustomerOnlineDataModel> call, Response<CustomerOnlineDataModel> response) {
                Log.d(TAG, "onResponse: "+response.body());
                customerUpdate(response.body());

                customerDataReceived = true;
                Log.d(TAG, "onResponse: ------------ customer 40");
                sendCompleteMessage(40);
            }

            @Override
            public void onFailure(Call<CustomerOnlineDataModel> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
                sendCompleteMessage(40);
                Log.d(TAG, "onResponse: ------------ customer 40");
                customerDataReceived = true;

            }
        });


    }


    // send data to webServer

    JsonData jsonData;
    private void sendDataToWebServer() {

        jsonData = new JsonData(context);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiClient = retrofit.create(RetrofitApiCaller.class);

        Call<RetrofitResponse> apiResponse = apiClient.sendData(jsonData.getDataAsJson(),sendDataUrl);
        apiResponse.enqueue(new Callback<RetrofitResponse>() {
            @Override
            public void onResponse(Call<RetrofitResponse> call, Response<RetrofitResponse> response) {
                Log.d(TAG, "sendDataToWeb --------"+response.message()+"\n"+jsonData.getDataAsJson());
                sendData = true;
                sendCompleteMessage(30);
                Log.d(TAG, "onResponse: ------------ sendData 30");

                downloadStockData();
                downloadCustomerData();
            }

            @Override
            public void onFailure(Call<RetrofitResponse> call, Throwable t) {
                Log.d(TAG, "sendDataToWeb --------"+t.getMessage());
                sendData = true;
                sendCompleteMessage(30);
                downloadStockData();
                downloadCustomerData();

            }
        });

        Log.d(TAG, "sendDataToWebServer: "+jsonData.getDataAsJson());

    }

    //after download stock data then update local database
    public void stockUpdate(ArrayList<StockOnlineModel> stocks){

        if (stocks == null) return;
        else if (stocks.get(0) == null){
            return;
        }else {
            deleteAllData.deleteStockData();//delete previous database data
            for (StockOnlineModel stock : stocks) {


                //// TODO: 7/24/2017  here data change on base Stock id need to change it product code
                stockDatabase.storeStock(new StockDatabaseModel(stock.getId(), stock.getCategory() + "", stock.getQuantity(), employeeId));
                productDatabase.storeProductInfo(new ProductDatabaseModel(stock.getName(),
                        stock.getId(),
                        stock.getPrice(),
                        stock.getSellPrice(),
                        stock.getUnit(),
                        stock.getBrand(),
                        stock.getSize()
                ));

            }
        }
    }

    //after download customer data then this method will update local database
    public void customerUpdate(CustomerOnlineDataModel customerModel){

        if (customerModel == null){
            return;
        }
        if (customerModel.getCustomerInfo() != null){
            List<CustomerOnlineDataModel.CustomerInfo> customers = customerModel.getCustomerInfo();
            if (customers.get(0).getEmpId() != null){

                deleteAllData.deleteCustomer();//delete previous database data
                for (CustomerOnlineDataModel.CustomerInfo customer: customers)
                {
                    customerDatabase.createNewCustomer(new CustomerModel(customer.getFullName(),
                            customer.getCustomerCode(),
                            customer.getCustomerType()+"",
                            customer.getGender(),
                            customer.getPhoneNo(),
                            customer.getEmail(),
                            customer.getAddress(),
                            customer.getDueAmount()+""
                    ));
                }
            }
        }

    }


    // broadcast send

    public void sendMessage(String status){
        Intent intent = new Intent("updateDatabaseStatus");
        // You can also include some extra data.
        intent.putExtra("status", status);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    public void sendCompleteMessage(){
        Intent intent = new Intent("completationMessage");
        // You can also include some extra data.
        intent.putExtra("status", true);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }
    public void sendCompleteMessage(int value){
        Intent intent = new Intent("completationMessage");

        intent.putExtra("percentage", value);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

}
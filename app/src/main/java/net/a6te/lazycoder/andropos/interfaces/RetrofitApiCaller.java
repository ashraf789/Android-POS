package net.a6te.lazycoder.andropos.interfaces;

import com.google.gson.JsonObject;

import net.a6te.lazycoder.andropos.modelClass.CustomerOnlineDataModel;
import net.a6te.lazycoder.andropos.modelClass.EmployeeModel;
import net.a6te.lazycoder.andropos.modelClass.RetrofitResponse;
import net.a6te.lazycoder.andropos.modelClass.StockOnlineModel;
import net.a6te.lazycoder.andropos.modelClass.UserLogin;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * Created by Programmer on 6/18/2017.
 */

public interface RetrofitApiCaller {

    @FormUrlEncoded
    @POST("posapi/public/access")
    Call<UserLogin> userLogin(@Field("username") String userName, @Field("password") String password);

    @GET()
    Call<ArrayList<StockOnlineModel>> getStocks(@Url String url);

    @GET()
    Call<CustomerOnlineDataModel> getCustomersInfo(@Url String url);

    @POST()
    Call<RetrofitResponse> sendData(@Body JsonObject data, @Url String url);

}

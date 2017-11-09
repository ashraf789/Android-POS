package net.a6te.lazycoder.andropos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.a6te.lazycoder.andropos.database.User;
import net.a6te.lazycoder.andropos.interfaces.RetrofitApiCaller;
import net.a6te.lazycoder.andropos.modelClass.UserDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.UserLogin;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAuthentication extends AppCompatActivity {

    public static final String TAG = "AndroPos";
    private EditText nameEt;
    private EditText passwordEt;
    private Button loginBtn;
    private RetrofitApiCaller loginApi;
    private String userName;
    private String userPassword;
    private User user;


    public static final String BASE_URL = "http://testpos.sahidul.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_authentication);
        user = new User(this);
        nameEt = (EditText) findViewById(R.id.userNameEt);
        passwordEt = (EditText) findViewById(R.id.userPasswordEt);
        loginBtn = (Button) findViewById(R.id.btnLogin);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(nameEt.getText()) || TextUtils.isEmpty(passwordEt.getText())){
                    if (TextUtils.isEmpty(nameEt.getText())) nameEt.setError("user name can,t be empty");
                    if (TextUtils.isEmpty(passwordEt.getText())) nameEt.setError("user password can,t be empty");
                }else {
                    authentication();
                }
            }
        });
    }

    private void authentication() {


        userName = nameEt.getText().toString();
        userPassword = passwordEt.getText().toString();


        if (user.haveAnySeller()){
            if (user.getUserDetails().getUserName().equals(userName)
                    && user.getUserDetails().getUserName().equals(userPassword)){
                startActivity(new Intent(UserAuthentication.this,MainActivity.class));
                finish();

                return;
            }
        }

        if (!new CheckInternetConnection().netCheck(this)){
            Toast.makeText(UserAuthentication.this,"No Internet Connection",Toast.LENGTH_SHORT).show();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        loginApi = retrofit.create(RetrofitApiCaller.class);
        Call<UserLogin> apiResponse = loginApi.userLogin(userName,userPassword);



        apiResponse.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                UserLogin userLogin = response.body();

                Log.d(TAG, "onResponse: "+response.body());
                Log.d(TAG, "onResponse: userLogin"+response.message());
                try{

                    if (userLogin.getEmployinfo().getUsername() != null){
                        //store user details to userDatabase
                        user.createNewUser(new UserDatabaseModel(userLogin.getEmployinfo().getUsername()
                                ,userLogin.getEmployinfo().getEmail()
                                ,userLogin.getEmployinfo().getPassword()
                                ,userLogin.getEmployinfo().getPhoneNo()
                                ,userLogin.getEmployinfo().getUserId()));

                        finish();
                        startActivity(new Intent(UserAuthentication.this,MainActivity.class));

                    }else {
                        Log.d(TAG, "onResponse: wrong user name or password");
                        Toast.makeText(UserAuthentication.this,"Wrong userName or Password",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    showToast("Wrong user name or password or invalid device");
                    Toast.makeText(UserAuthentication.this,"Wrong userName or Password",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                showToast("Please check your internet connection");
                Log.d(TAG, "onFailure: userLogin"+t.getMessage());
            }
        });



    }

    public void showToast(String str){
        Toast.makeText(UserAuthentication.this,str,Toast.LENGTH_SHORT).show();
    }

}

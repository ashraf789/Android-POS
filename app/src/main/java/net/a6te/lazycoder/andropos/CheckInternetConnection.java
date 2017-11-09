package net.a6te.lazycoder.andropos;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Ashraf on 1/16/2017.
 */

public class CheckInternetConnection {

    //checking internet connection if internet is connected then it will return true
    // otherwise it will return false

    public static boolean netCheck(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();

        boolean isConnected = (nInfo != null && nInfo.isConnectedOrConnecting());
        return isConnected;
    }

}

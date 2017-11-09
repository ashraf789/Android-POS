package net.a6te.lazycoder.andropos.sellTabes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import net.a6te.lazycoder.andropos.R;
import net.a6te.lazycoder.andropos.adapters.ProductListAdapter;
import net.a6te.lazycoder.andropos.databinding.FragmentListProductFrgBinding;
import net.a6te.lazycoder.andropos.modelClass.ProductListModel;

import java.util.ArrayList;

import static net.a6te.lazycoder.andropos.database.Stock.TAG;

public class ListProductFrg extends Fragment {

    private FragmentListProductFrgBinding binder;
    private ProductListAdapter adapter;
    private ArrayList<ProductListModel> products;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binder = DataBindingUtil.inflate(inflater,R.layout.fragment_list_product_frg, container, false);
        products = new ArrayList<>();

        binder = DataBindingUtil.inflate(inflater,R.layout.fragment_list_product_frg, container, false);
        return binder.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver
                ,new IntentFilter("selectedProduct"));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(messageReceiver
                ,new IntentFilter("clearAll"));

        updateListView();
        binder.productListLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setMessage("Are sure you want to remove this item?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        products.remove(position);
                        sendBroadCastToReceiptPage(position);
                        updateListView();
                    }
                });
                dialog.setNegativeButton("No",null);

                dialog.show();
                return true;
            }
        });
    }

    public void updateListView(){
        adapter = new ProductListAdapter(getActivity(),products);
        binder.productListLv.setAdapter(adapter);
    }

    private void sendBroadCastToReceiptPage(int position) {
        Intent intent = new Intent("removeProduct");
        intent.putExtra("position",position);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }

    BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ProductListModel temp = (ProductListModel) intent.getSerializableExtra("product");
            products.add(temp);
            updateListView();
        }
    };

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean flag = intent.getBooleanExtra("flag",false);
            if (flag){
                products = new ArrayList<>();
                updateListView();
            }
        }
    };

}

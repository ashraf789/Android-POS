package net.a6te.lazycoder.andropos.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.a6te.lazycoder.andropos.R;
import net.a6te.lazycoder.andropos.UpdateDatabase;
import net.a6te.lazycoder.andropos.adapters.StockAdapter;
import net.a6te.lazycoder.andropos.database.Stock;
import net.a6te.lazycoder.andropos.databinding.FragmentStockBinding;
import net.a6te.lazycoder.andropos.modelClass.StockDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.StockModel;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

import static net.a6te.lazycoder.andropos.database.Stock.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class StockFragment extends Fragment {


    public StockFragment() {
        // Required empty public constructor
    }


    private FragmentStockBinding stockBinding;
    private View view;
    private StockAdapter adapter;
    private StockModel stockModel;
    private Stock stock;
    private ArrayList<StockModel> allStocks;
    private UpdateDatabase updateDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        stockBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_stock, container, false);
        view = stockBinding.getRoot();
        stockModel = new StockModel();
        updateDatabase = new UpdateDatabase(getActivity());

        stock = new Stock(getActivity());



        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(messageReceiver
//                ,new IntentFilter("updateDatabaseStatus"));
        listViewUpdate();//update stock data
    }

    private void listViewUpdate() {
        try {
            allStocks = stock.getStocks();
        }catch (Exception e){
            e.printStackTrace();
        }
        adapter = new StockAdapter(getActivity(),allStocks);

        if (stock.haveAnyStock()){
            stockBinding.stockLv.setVisibility(View.VISIBLE);
            stockBinding.errorTv.setVisibility(View.GONE);

            stockBinding.stockLv.setAdapter(adapter);
        }else {
            stockBinding.stockLv.setVisibility(View.GONE);
            stockBinding.errorTv.setVisibility(View.VISIBLE);
        }
    }
//
//    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String status = intent.getStringExtra("status");
//            if (status.equals("stock")){
//                listViewUpdate();
//            }
//        }
//    };
}
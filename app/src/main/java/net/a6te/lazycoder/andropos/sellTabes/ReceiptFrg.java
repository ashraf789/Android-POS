package net.a6te.lazycoder.andropos.sellTabes;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import net.a6te.lazycoder.andropos.R;
import net.a6te.lazycoder.andropos.database.Customer;
import net.a6te.lazycoder.andropos.database.CustomerDue;
import net.a6te.lazycoder.andropos.database.Product;
import net.a6te.lazycoder.andropos.database.SellsInfo;
import net.a6te.lazycoder.andropos.database.SoldProductInfo;
import net.a6te.lazycoder.andropos.database.Stock;
import net.a6te.lazycoder.andropos.database.User;
import net.a6te.lazycoder.andropos.databinding.FragmentReceiptFrgBinding;
import net.a6te.lazycoder.andropos.modelClass.CustomerDuelDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.CustomerModel;
import net.a6te.lazycoder.andropos.modelClass.ProductListModel;
import net.a6te.lazycoder.andropos.modelClass.SellsDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.SoldProductModel;
import net.a6te.lazycoder.andropos.modelClass.UserDatabaseModel;
import net.londatiga.android.bluebamboo.MainActivityPrinter;
import net.londatiga.android.bluebamboo.PrintSellInfo;

import static net.a6te.lazycoder.andropos.database.Stock.TAG;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */

public class ReceiptFrg extends Fragment {

    private FragmentReceiptFrgBinding binding;
    private ArrayList<ProductListModel> products;
    private int totalBill=0;
    private int discount=0;
    private int totalPayAmount =0;
    private int customerPayment = 0;
    private int paymentStatus = 0;
    private String[] paymentType = new String []{"Cash","Card"};

    private ArrayList<CustomerModel> customers;
    private CustomerModel selectedCustomer;
    private UserDatabaseModel seller;
    private SellsInfo sellsInfo;
    private Stock stock;
    private PrintSellInfo printInfo;
    private Customer customerDatabase;
    private CustomerDue customerDue;
    private SoldProductInfo soldProductInfo;

    private String date = "170605";
    private Calendar calendar;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_receipt_frg, container, false);
        products = new ArrayList<>();
        customers = new Customer(getActivity()).getAllCustomer();//get all customer details from customer database
        seller = new User(getActivity()).getUserDetails();//this user method will return userDatavaseModelClassObject
        sellsInfo = new SellsInfo(getActivity());
        stock = new Stock(getActivity());
        printInfo = new PrintSellInfo();
        customerDatabase = new Customer(getActivity());
        customerDue = new CustomerDue(getActivity());
        soldProductInfo = new SoldProductInfo(getActivity());

        calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy");//example date 06-11-17
        date = dateFormat.format(calendar.getTime());
        Log.d(TAG, "onCreateView: -----------"+date);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(messageReceiver
                ,new IntentFilter("selectedProduct"));
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mRemoveProductMessageReceiver
                ,new IntentFilter("removeProduct"));


        try {
            if (validationCheck(seller.getUserPhone())
                    && validationCheck(seller.getUserEmail())
                    && validationCheck(String.valueOf(sellsInfo.getLastSellItemCode()))){
                binding.sellerPhoneRTv.setText("Phone : "+seller.getUserPhone());
                binding.sellerEmailRTv.setText("Email : "+seller.getUserEmail());
                binding.receiptInvoiceTv.setText("u-"+seller.getUserEmployeeId()+"-in-"+(sellsInfo.getLastSellItemCode()+1));

            }else {
                binding.sellerPhoneRTv.setText("Phone : Invalid Seller");
                binding.sellerEmailRTv.setText("Email : Invalid Seller");
            }



            ArrayAdapter<String > adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,paymentType);
            binding.receiptPaymentTypeSpinner.setAdapter(adapter);

            binding.receiptDiscountEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (!TextUtils.isEmpty(binding.receiptDiscountEt.getText())){
                        String temp = binding.receiptDiscountEt.getText().toString().trim();
                        discount = Integer.parseInt(temp);

                        if (discount < 0){
                            binding.receiptDiscountEt.setError("discount amount can,t be negative");
                            discount = 0;
                        }else {
                            updateData();
                        }
                    }
                    return true;
                }
            });

            final ArrayList<String > suggestionCustomer = new ArrayList<>();
            for (CustomerModel customer: customers){
                suggestionCustomer.add(customer.getCustomerName());
            }
            ArrayAdapter<String > suggestionAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,suggestionCustomer);
            binding.receiptCustomerSpinner.setAdapter(suggestionAdapter);
            binding.receiptSaveReceiptBtn.setOnClickListener(listener);
            Log.d(TAG, "receipt page");


        }catch (Exception e){

            e.printStackTrace();
            Toast.makeText(getActivity(),"Please Select Valid Data",Toast.LENGTH_SHORT).show();
        }


    }

    View.OnClickListener listener = new View.OnClickListener() {


        @Override
        public void onClick(View v) {

            String temp[] = binding.receiptTotalTv.getText().toString().trim().split("Tk");

            if (Integer.parseInt(temp[0]) < 1){
                Toast.makeText(getActivity(),"Select At lest one product",Toast.LENGTH_SHORT).show();

                return;
            }
            selectedCustomer = customers.get((int) binding.receiptCustomerSpinner.getSelectedItemId());
            try {
                customerPayment = Integer.parseInt(binding.receiptPayAmountEt.getText().toString());
                totalPayAmount = Integer.parseInt(temp[0]);
                discount = Integer.parseInt(binding.receiptDiscountEt.getText().toString().trim());

                totalBill = totalPayAmount - discount;

                if (!validationCheck(selectedCustomer.getCustomerName())){
                    Snackbar.make(v,"Select valid customer",Snackbar.LENGTH_SHORT).show();
                    return;
                }else if (totalPayAmount < 1){
                    Snackbar.make(v,"please select at lest one product",Snackbar.LENGTH_SHORT).show();
                    return;
                }else if (TextUtils.isEmpty(binding.receiptPayAmountEt.getText().toString())){
                    Snackbar.make(v,"Empty Customer payment ",Snackbar.LENGTH_SHORT).show();
                    return;
                }else if (Integer.parseInt(binding.receiptPayAmountEt.getText().toString()) < 1){
                    Snackbar.make(v,"Empty Customer payment ",Snackbar.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Snackbar.make(v,"Input field can,t be empty",Snackbar.LENGTH_SHORT).show();
                Log.d(TAG, "onClick: "+e);
                e.printStackTrace();
                return;
            }

//            stockAvailabilityCheck();
//
            if (customerPayment < totalBill){
                paymentStatus = 1;
            }

            boolean status = true;
            SellsDatabaseModel sellDatabase = new SellsDatabaseModel(
                    binding.receiptInvoiceTv.getText().toString(),
                    selectedCustomer.getCustomerCode(),
                    binding.receiptTotalTv.getText().toString(),
                    binding.receiptDiscountEt.getText().toString(),
                    binding.receiptPayAmountEt.getText().toString(),
                    binding.receiptPaymentTypeSpinner.getSelectedItem().toString(),
                    date,
                    String.valueOf(paymentStatus),
                    seller.getUserEmployeeId()
            );

            status = updateSellInfo(sellDatabase);


            if (status) {
                Toast.makeText(getActivity(),"Successfully Sold Product",Toast.LENGTH_LONG).show();
                int due = totalPayAmount - customerPayment - discount;
                String allProducts = "";
                String allProductsQuantity = "";
                String allProductPrice = "";
                for (ProductListModel product: products){
                    allProducts +=product.getpName()+"\n";
                    allProductsQuantity+=product.getpSelectQuantity()+" "+product.getpUnit()+"\n";
                    allProductPrice += product.getPriceTotal()+" Tk\n";
                }


//                private String sellerNameTv;
//                private String sellerPhoneTv;
//                private String sellerEmailTv;
//                private String customerNameTv;
//                private String customerPhoneTv;
//                private String customerEmialTv;
//                private String invoiceTv;
//                private String totalAmountTv;
//                private String dueTv;
//                private String productsName;
//                private String productQuantityTv;
//                private String productPriceTv;
//                private String totalBillTv;
//                private String discount;
//                private String payableTv;
//                private String depositTv;
//                private String currentDueTv;
//
                printInfo = new PrintSellInfo(
                        seller.getUserName(),
                        seller.getUserPhone(),
                        seller.getUserEmail(),
                        selectedCustomer.getCustomerName(),
                        selectedCustomer.getCustomerPhone(),
                        selectedCustomer.getCustomerEmail(),
                        binding.receiptInvoiceTv.getText().toString(),
                        String.valueOf(totalPayAmount),
                        String.valueOf(due),
                        allProducts,
                        allProductsQuantity,
                        allProductPrice,
                        String.valueOf(totalPayAmount),
                        String.valueOf(discount),
                        String.valueOf(totalBill),
                        String.valueOf(customerPayment),
                        String.valueOf(due));

                Intent intent = new Intent(getActivity(),MainActivityPrinter.class);
                intent.putExtra("printData",printInfo);
                startActivity(intent);

                updateStock();//remove soled product form stock
                updateCustomerInfo();//update customer due info
                updateDueList();//update due database
                updateSoldProductInfo();
                removePreviousData();//remove old data after product sold


            }else {
                Snackbar.make(v,"failed to store sold info",Snackbar.LENGTH_SHORT).show();
            }

        }


    };


    private void removePreviousData() {
        products = new ArrayList<>();
        customers = new Customer(getActivity()).getAllCustomer();//get all customer details from customer database
        seller = new User(getActivity()).getUserDetails();//this user method will return userDatavaseModelClassObject
        sellsInfo = new SellsInfo(getActivity());
        stock = new Stock(getActivity());
        printInfo = new PrintSellInfo();

        totalBill=0;
        discount=0;
        totalPayAmount =0;
        customerPayment = 0;
        paymentStatus = 0;
        try {
            binding.receiptInvoiceTv.setText("u-"+seller.getUserEmployeeId()+"-in-"+(sellsInfo.getLastSellItemCode()+1));
        }catch (Exception e){

        }

        sendBroadForClearOldData();
        updateData();
    }

    private void sendBroadForClearOldData() {
        Intent intent = new Intent("clearAll");
        intent.putExtra("flag",true);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
    }


    private boolean updateSellInfo(SellsDatabaseModel sellsDatabaseModel){
        return sellsInfo.storeSellDetails(sellsDatabaseModel);
    }

    private void updateSoldProductInfo() {
//    public static final String COL_SOLD_PRODUCT_CODE = "sells_code";
//    public static final String COL_SOLD_PRODUCT_SELL_ID = "sells_id";
//    public static final String COL_SOLD_PRODUCT_PRODUCT_ID = "sells_product_id";
//    public static final String COL_SOLD_PRODUCT_PRICE = "product_price";
//    public static final String COL_SOLD_PRODUCT_QUANTITY = "quantity";
//    public static final String COL_SOLD_PRODUCT_TOTAL_PRICE = "total_price";
//    public static final String COL_SOLD_PRODUCT_PENDING_STATUS = "pending_status";
//

        for (ProductListModel product : products){
            soldProductInfo.storeSoldProductInfo(new SoldProductModel(
                    printInfo.getInvoiceTv(),
                    printInfo.getInvoiceTv(),
                    product.getpCode(),
                    product.getpPrice(),
                    product.getpSelectQuantity(),
                    printInfo.getTotalAmountTv(),
                    paymentStatus+""
            ));

        }
    }

    private void updateDueList() {

//        private String customerId;
//        private String totalAmount;
//        private String totalPayAmount;
//        private String dueAmount;
//        private String sellsCode;
//        private String payDueDate;
//        private String payDeposit;

        //update due info
        boolean status = customerDue.storeSellsDetails(new CustomerDuelDatabaseModel(
                selectedCustomer.getCustomerCode(),
                printInfo.getTotalAmountTv(),
                printInfo.getPayableTv(),
                printInfo.getCurrentDueTv(),
                printInfo.getInvoiceTv(),
                date,
                printInfo.getDepositTv()
        ));

        if (status) Log.d(TAG, "updateDueList: --------------successful");
        else Log.d(TAG, "updateDueList: --------- failed to store due details");
    }

    private void updateCustomerInfo() {

        //customer info update
        boolean status = customerDatabase.updateCustomerDueAmount(selectedCustomer.getCustomerCode(), String.valueOf(getInteger(selectedCustomer.getCustomerDueAmount())+getInteger(printInfo.getDueTv())));
        if (status) Log.d(TAG, "updateCustomerInfo: --------------new due status updated");
        else Log.d(TAG, "updateCustomerInfo:------------- failed to update new customer due status");
    }

    private void updateStock() {

        for (ProductListModel product: products){
            int selectQuantity = Integer.parseInt(product.getpSelectQuantity());
            int stockLimit = Integer.parseInt(product.getpStockLimit());

            if (stockLimit > selectQuantity){
                stock.updateStock(product.getpCode(),String.valueOf(stockLimit-selectQuantity));
            }else{
                stock.deleteStock(product.getpCode());
            }

        }
    }

    BroadcastReceiver mRemoveProductMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                int temp = intent.getIntExtra("position",-1);
                int tempValue = Integer.parseInt(products.get(temp).getPriceTotal());
                if (tempValue > 0) totalBill -= tempValue;

                products.remove(temp);
                updateData();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    public void updateData(){
        if (discount > 0){
            totalPayAmount = totalBill - discount;
        }
        binding.receiptTotalTv.setText(String.valueOf(totalBill)+"Tk");
        binding.receiptPayAmountEt.setText(String.valueOf(totalPayAmount));
    }

    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ProductListModel temp = (ProductListModel) intent.getSerializableExtra("product");
            totalBill += Integer.parseInt(temp.getPriceTotal());
            products.add(temp);

            updateData();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(messageReceiver);
    }

    public boolean validationCheck(String  str){
        try{
            if (TextUtils.isEmpty(str)){
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            return false;
        }
    }
    public int getInteger(String str){
        str.trim();
        int temp=0;
        try {
            temp = Integer.parseInt(str);
        }catch (Exception e){

        }
        return temp;
    }
}

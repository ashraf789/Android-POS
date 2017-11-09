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
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import net.a6te.lazycoder.andropos.R;
import net.a6te.lazycoder.andropos.database.Product;
import net.a6te.lazycoder.andropos.databinding.FragmentAddProductFrgBinding;
import net.a6te.lazycoder.andropos.modelClass.ProductDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.ProductListModel;

import java.util.ArrayList;

import static net.a6te.lazycoder.andropos.database.Product.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFrg extends Fragment {

    private FragmentAddProductFrgBinding binding;
    public AddProductFrg() {
        // Required empty public constructor
    }
    private Product products;
    private ProductListModel selectedProduct;
    private ArrayList<ProductDatabaseModel> allProducts;
    private int selectedProductIndex = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_product_frg, container, false);
        products = new Product(getActivity());
        allProducts = products.getProducts();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(messageReceiver
                ,new IntentFilter("clearAll"));

        setSpinnerValue();
        binding.priceSellEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                updateTotalPrice();
                return true;
            }
        });
        binding.quantitySellEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                updateTotalPrice();
                return true;
            }
        });



        binding.addProductSellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if ((Integer.parseInt(selectedProduct.getpStockLimit()) >=
                            Integer.parseInt(binding.quantitySellEt.getText().toString()))){

                        sendMessage();

                    }else{
                        Snackbar.make(view,"Stock limit exist",Snackbar.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Snackbar.make(view,"No Product Selected ",Snackbar.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void setSpinnerValue(){

        final ArrayList<String > suggestionProduct = new ArrayList<>();
        suggestionProduct.add("Select Product");

        if (products.haveAnyProduct()){
            for (ProductDatabaseModel product: allProducts){
                suggestionProduct.add(product.getProductName()+"("+product.getProductBrand()+")");
            }
        }


        final ArrayAdapter<String > suggestionAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,suggestionProduct);
        binding.selectProductSellSpinner.setAdapter(suggestionAdapter);

        final Product selectedProductDetails = new Product(getActivity());

        binding.selectProductSellSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0){

                    selectedProductIndex = position-1;
                    ProductListModel product = selectedProductDetails.getProductDetails(products.getProducts().get((selectedProductIndex)).getProductCode());
                    selectedProduct = product;
                    selectedProduct.setpStockLimit(allProducts.get(selectedProductIndex).getProductStockLimit());
                    updateProductDetails();

                }else {
                    selectedProductIndex = -1;
                    selectedProduct = null;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void updateProductDetails(){
        binding.brandSellTv.setText("Size: "+ selectedProduct.getpSize());
        binding.uitSellTv.setText("Unit: "+ selectedProduct.getpUnit());
        binding.stockSellTv.setText("Stock: "+ selectedProduct.getpStockLimit());
        binding.quantitySellEt.setText("1");
        binding.priceSellEt.setText(selectedProduct.getpPrice());
        updateTotalPrice();
    }

    public void updateTotalPrice(){

        try {
            double totalPrice  = Double.parseDouble(binding.priceSellEt.getText().toString())*Double.parseDouble(binding.quantitySellEt.getText().toString());
            binding.totalPriceTv.setText(totalPrice+" Tk");
            selectedProduct.setpSelectQuantity(binding.quantitySellEt.getText().toString());
            selectedProduct.setPriceTotal(binding.totalPriceTv.getText().toString());
        }catch (Exception e){
            if (binding.quantitySellEt.getText().toString().isEmpty()){
                binding.quantitySellEt.setText("0");
            }
            if (binding.priceSellEt.getText().toString().isEmpty()){
                binding.priceSellEt.setText("0");
            }
        }

    }


    private void sendMessage() {
        selectedProduct.setpSelectQuantity(binding.quantitySellEt.getText().toString());
        try {
            selectedProduct.setpPrice(binding.priceSellEt.getText().toString());
            selectedProduct.setPriceTotal(
                    Integer.parseInt(selectedProduct.getpPrice()) * Integer.parseInt(selectedProduct.getpSelectQuantity())+""
            );
            binding.totalPriceTv.setText(selectedProduct.getPriceTotal());
            selectedProduct.setpSelectQuantity(binding.quantitySellEt.getText().toString());

            if (selectedProduct.getPriceTotal().isEmpty()
                    || Double.parseDouble(selectedProduct.getPriceTotal()) < 1){
                Toast.makeText(getActivity(),"Please select at lest one product",Toast.LENGTH_SHORT).show();
                return;
            }
        }catch (Exception e){
            Toast.makeText(getActivity(),"Please select at lest one product",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "sendMessage: ----- "+e);
            return;
        }

        //decrease stock limit after select a product
        ProductDatabaseModel temp;
        temp = allProducts.get(selectedProductIndex);
        temp.setProductStockLimit(String.valueOf((Integer.parseInt(allProducts.get(selectedProductIndex).getProductStockLimit()) - Integer.parseInt(binding.quantitySellEt.getText().toString()))));
        allProducts.set(selectedProductIndex,temp);

        binding.priceSellEt.setText("0Tk");
        binding.quantitySellEt.setText("0");
        binding.totalPriceTv.setText("0.0Tk");
        binding.stockSellTv.setText("Stock : 0");
        binding.selectProductSellSpinner.setSelection(0);
        // TODO: 5/23/2017 don,t know why when autocomplit text not chantge then brodcast send same value need to work in here
        //this not proper  solution i wll fixed it later





        Intent intent = new Intent("selectedProduct");
        // You can also include some extra data.
        intent.putExtra("product", selectedProduct);
        LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
        Toast.makeText(getActivity(),"New Product Added on sell list",Toast.LENGTH_SHORT).show();
    }

    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean flag = intent.getBooleanExtra("flag",false);
            if (flag){
                allProducts = products.getProducts();
                setSpinnerValue();
            }
        }
    };
}

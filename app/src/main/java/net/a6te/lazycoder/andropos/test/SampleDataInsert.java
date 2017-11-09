package net.a6te.lazycoder.andropos.test;

import android.content.Context;
import android.util.Log;

import net.a6te.lazycoder.andropos.database.Customer;
import net.a6te.lazycoder.andropos.database.Product;
import net.a6te.lazycoder.andropos.database.SellsInfo;
import net.a6te.lazycoder.andropos.database.Stock;
import net.a6te.lazycoder.andropos.database.User;
import net.a6te.lazycoder.andropos.modelClass.CustomerModel;
import net.a6te.lazycoder.andropos.modelClass.ProductDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.SellsDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.StockDatabaseModel;
import net.a6te.lazycoder.andropos.modelClass.UserDatabaseModel;

import static net.a6te.lazycoder.andropos.MainActivity.TAG;

/**
 * Created by Programmer on 5/21/2017.
 */

public class SampleDataInsert {


    private Stock stock;
    private Product product;
    private Customer customer;
    private User user;
    private SellsInfo sells;


    public SampleDataInsert(Context context){
        stock = new Stock(context);
        product = new Product(context);
        customer = new Customer(context);
        user = new User(context);
        sells = new SellsInfo(context);

        storeSomeSampleData();
    }

    private void storeSomeSampleData() {


        //insert data to stock page
        if (!stock.haveAnyStock()){

            //only first time send some temporary data
//        private String ProductId;
//        private String ProductType;
//        private String ProductQuantity;
//        private String ProductFor;


//            stock table

            stock.storeStock(new StockDatabaseModel("990","2","30","1"));
            stock.storeStock(new StockDatabaseModel("991","2","300","1"));
            stock.storeStock(new StockDatabaseModel("992","2","40","1"));
            stock.storeStock(new StockDatabaseModel("993","2","60","1"));
            stock.storeStock(new StockDatabaseModel("994","2","98","1"));
            stock.storeStock(new StockDatabaseModel("995","2","500","1"));
            stock.storeStock(new StockDatabaseModel("996","2","60","1"));


            stock.storeStock(new StockDatabaseModel("01","0","20","1"));
            stock.storeStock(new StockDatabaseModel("02","2","50","1"));
            stock.storeStock(new StockDatabaseModel("03","2","100","1"));
            stock.storeStock(new StockDatabaseModel("04","0","15","1"));

            //product table
//            private String productName;
//            private String productCode;
//            private String productPrice;
//            private String productSellPrice;
//            private String productUnit;
//            private String productBrand;
//            private String productSize;

            product.storeProductInfo(new ProductDatabaseModel("Laptop","01","50000","55000","Pcs","Dell","18.5 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Mobile","02","10000","15000","Pcs","Samsung","5.5 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Iphone","03","70000","80000","Pcs","Apple","5.0 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Desktop","04","50000","90000","Pcs","Acer","20 inch"));

            product.storeProductInfo(new ProductDatabaseModel("Tv DTE24BF","990","50000","55000","Pcs","DANSAT","24 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Tv DTD28BF","991","55000","60000","Pcs","DANSAT","28 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Tv HD LED","992","60000","62000","Pcs","Samsung","30 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Tv HD CURVE","993","70000","73000","Pcs","Samsung","32.5 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Receiver 991","994","75000","79000","Pcs","Samsung","38.5 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Receiver 993","995","90000","93000","Pcs","Dell","45 inch"));
            product.storeProductInfo(new ProductDatabaseModel("Al-Hidaya","996","100000","110000","Pcs","WALTON","50 inch"));



            //customer table


//            private String customerName;
//            private String customerCode;
//            private String customerType;
//            private String customerGender;
//            private String customerPhone;
//            private String customerEmail;
//            private String customerAddress;
//            private String customerDueAmount;
            customer.createNewCustomer(new CustomerModel("Walkthrough","CUS1","regular","male","88555320","WalkThrough@gmail.com","mirpur dohs","600"));
            customer.createNewCustomer(new CustomerModel("Mikel brane","CUS2","regular","male","54115415","Mikel@gmail.com","mirpur dohs","20"));
            customer.createNewCustomer(new CustomerModel("Anca","CUS3","regular","male","7881544","Anca@gmail.com","mirpur dohs","0"));
            customer.createNewCustomer(new CustomerModel("James","CUS4","regular","male","8718245","James@gmail.com","mirpur dohs","100"));
            customer.createNewCustomer(new CustomerModel("Jonson","CUS5","regular","male","3562565","Jonson@gmail.com","mirpur dohs","500"));
            customer.createNewCustomer(new CustomerModel("Miler","CUS6","regular","male","356255","Miler@gmail.com","mirpur dohs","600"));
            customer.createNewCustomer(new CustomerModel("Amla","CUS7","regular","male","985414","Amla@gmail.com","mirpur dohs","600"));
            customer.createNewCustomer(new CustomerModel("Maxual","CUS8","regular","male","987761","Maxual@gmail.com","mirpur dohs","0"));

            //user table

//            private String userName;
//            private String userEmail;
//            private String userPassword;
//            private String userPhone;
//            private String userEmployeeId;
//
            user.createNewUser(new UserDatabaseModel("admin@gmail.com","admin@gmail.com","123456","01932237742","1"));

            //sell table
//            private String sellsCode;
//            private String customerId;
//            private String totalAmount;
//            private String discount;
//            private String payAmount;
//            private String paymentType;
//            private String sellDate;
//            private String paymentStatus;
//            private String sellBy;

            sells.storeSellDetails(new SellsDatabaseModel("in-1","CUS1","10000","10","8000","0","06-06-17","1","1"));
            sells.storeSellDetails(new SellsDatabaseModel("in-2","CUS2","5000","300","5000","0","06-06-17","0","1"));

        }

    }
}

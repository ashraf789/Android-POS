package net.a6te.lazycoder.andropos.modelClass;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Programmer on 5/7/2017.
 */

public class ProductListModel implements Serializable{

    private String serialNo;
    private String pPrice;
    private String pName;
    private String pBrand;
    private String pSize;
    private String pUnit;
    private String pStockLimit;
    private String priceTotal;
    private String pSelectQuantity;
    private String pCode;

    public ProductListModel(String serialNo, String pPrice, String pName, String pBrand, String pSize, String pUnit, String pStockLimit, String priceTotal, String pSelectQuantity, String pCode) {
        this.serialNo = serialNo;
        this.pPrice = pPrice;
        this.pName = pName;
        this.pBrand = pBrand;
        this.pSize = pSize;
        this.pUnit = pUnit;
        this.pStockLimit = pStockLimit;
        this.priceTotal = priceTotal;
        this.pSelectQuantity = pSelectQuantity;
        this.pCode = pCode;
    }

    public ProductListModel(String serialNo, String pPrice, String pName, String pBrand, String pSize, String pUnit, String pQuantity, String priceTotal, String totalSelectedProduct) {

        this.serialNo = serialNo;
        this.pPrice = pPrice;
        this.pName = pName;
        this.pBrand = pBrand;
        this.pSize = pSize;
        this.pUnit = pUnit;
        this.pStockLimit = pQuantity;
        this.priceTotal = priceTotal;
        this.pSelectQuantity = totalSelectedProduct;
    }

    public ProductListModel(String pPrice, String pName, String pBrand, String pSize, String pUnit, String pStock) {
        this.pPrice = pPrice;
        this.pName = pName;
        this.pBrand = pBrand;
        this.pSize = pSize;
        this.pUnit = pUnit;
        this.pStockLimit = pStock;
    }
    public ProductListModel(String pPrice, String pName, String pBrand, String pSize, String pUnit, String pStock,String pCode) {
        this.pPrice = pPrice;
        this.pName = pName;
        this.pBrand = pBrand;
        this.pSize = pSize;
        this.pUnit = pUnit;
        this.pStockLimit = pStock;
        this.pCode = pCode;
    }


    public ProductListModel() {
    }

    public String getpCode() {
        return pCode;
    }

    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    public String getpSelectQuantity() {
        return pSelectQuantity;
    }

    public void setpSelectQuantity(String pSelectQuantity) {
        this.pSelectQuantity = pSelectQuantity;
    }

    public String getpStockLimit() {
        return pStockLimit;
    }

    public void setpStockLimit(String pStockLimit) {
        this.pStockLimit = pStockLimit;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpBrand() {
        return pBrand;
    }

    public void setpBrand(String pBrand) {
        this.pBrand = pBrand;
    }

    public String getpSize() {
        return pSize;
    }

    public void setpSize(String pSize) {
        this.pSize = pSize;
    }

    public String getpUnit() {
        return pUnit;
    }

    public void setpUnit(String pUnit) {
        this.pUnit = pUnit;
    }

    public String getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
    }

    public ArrayList<ProductListModel> getAllListedProduct(){
        ArrayList<ProductListModel> products = new ArrayList<>();
        products.add(new ProductListModel("01","2000","Laptop","Sony","25'","2pcs","4000"));
        products.add(new ProductListModel("02","3000","Tv","Sony","30'","1pcs","3000"));
        products.add(new ProductListModel("03","4000","Laptop","Sony","25'","2pcs","8000"));
        products.add(new ProductListModel("04","20000","Laptop","Dell","25'","2pcs","40000"));
        products.add(new ProductListModel("05","10000","Desktop","Sony","28'","3pcs","30000"));

        return products;
    }
}

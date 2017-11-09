package net.a6te.lazycoder.andropos.modelClass;

import java.io.Serializable;

/**
 * Created by Programmer on 5/21/2017.
 */

public class ProductDatabaseModel{

    private String productName;
    private String productCode;
    private String productPrice;
    private String productSellPrice;
    private String productUnit;
    private String productBrand;
    private String productSize;
    private String productStockLimit;


    public ProductDatabaseModel() {
    }

    public ProductDatabaseModel(String productName, String productCode, String productPrice, String productSellPrice, String productUnit, String productBrand, String productSize) {

        this.productName = productName;
        this.productCode = productCode;
        this.productPrice = productPrice;
        this.productSellPrice = productSellPrice;
        this.productUnit = productUnit;
        this.productBrand = productBrand;
        this.productSize = productSize;
    }

    public ProductDatabaseModel(String productName, String productCode, String productPrice, String productSellPrice, String productUnit, String productBrand, String productSize, String productStockLimit) {

        this.productName = productName;
        this.productCode = productCode;
        this.productPrice = productPrice;
        this.productSellPrice = productSellPrice;
        this.productUnit = productUnit;
        this.productBrand = productBrand;
        this.productSize = productSize;
        this.productStockLimit = productStockLimit;
    }

    public String getProductStockLimit() {
        return productStockLimit;
    }

    public void setProductStockLimit(String productStockLimit) {
        this.productStockLimit = productStockLimit;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductSellPrice() {
        return productSellPrice;
    }

    public void setProductSellPrice(String productSellPrice) {
        this.productSellPrice = productSellPrice;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }
}

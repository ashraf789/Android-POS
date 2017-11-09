package net.londatiga.android.bluebamboo;

import java.io.Serializable;

/**
 * Created by Programmer on 6/8/2017.
 */

public class PrintSellInfo implements Serializable{

    private String sellerNameTv;
    private String sellerPhoneTv;
    private String sellerEmailTv;
    private String customerNameTv;
    private String customerPhoneTv;
    private String customerEmialTv;
    private String invoiceTv;
    private String totalAmountTv;
    private String dueTv;
    private String productsName;
    private String productQuantityTv;
    private String productPriceTv;
    private String totalBillTv;
    private String discount;
    private String payableTv;
    private String depositTv;
    private String currentDueTv;

    public PrintSellInfo(){

    }

    public PrintSellInfo(String sellerNameTv, String sellerPhoneTv, String sellerEmailTv, String customerNameTv, String customerPhoneTv, String customerEmialTv, String invoiceTv, String totalAmountTv, String dueTv, String productsName, String productQuantityTv, String productPriceTv, String totalBillTv, String discount, String payableTv, String depositTv, String currentDueTv) {

        this.sellerNameTv = sellerNameTv;
        this.sellerPhoneTv = sellerPhoneTv;
        this.sellerEmailTv = sellerEmailTv;
        this.customerNameTv = customerNameTv;
        this.customerPhoneTv = customerPhoneTv;
        this.customerEmialTv = customerEmialTv;
        this.invoiceTv = invoiceTv;
        this.totalAmountTv = totalAmountTv;
        this.dueTv = dueTv;
        this.productsName = productsName;
        this.productQuantityTv = productQuantityTv;
        this.productPriceTv = productPriceTv;
        this.totalBillTv = totalBillTv;
        this.discount = discount;
        this.payableTv = payableTv;
        this.depositTv = depositTv;
        this.currentDueTv = currentDueTv;
    }


    public String getSellerNameTv() {
        return sellerNameTv;
    }

    public void setSellerNameTv(String sellerNameTv) {
        this.sellerNameTv = sellerNameTv;
    }

    public String getSellerPhoneTv() {
        return sellerPhoneTv;
    }

    public void setSellerPhoneTv(String sellerPhoneTv) {
        this.sellerPhoneTv = sellerPhoneTv;
    }

    public String getSellerEmailTv() {
        return sellerEmailTv;
    }

    public void setSellerEmailTv(String sellerEmailTv) {
        this.sellerEmailTv = sellerEmailTv;
    }

    public String getCustomerNameTv() {
        return customerNameTv;
    }

    public void setCustomerNameTv(String customerNameTv) {
        this.customerNameTv = customerNameTv;
    }

    public String getCustomerPhoneTv() {
        return customerPhoneTv;
    }

    public void setCustomerPhoneTv(String customerPhoneTv) {
        this.customerPhoneTv = customerPhoneTv;
    }

    public String getCustomerEmialTv() {
        return customerEmialTv;
    }

    public void setCustomerEmialTv(String customerEmialTv) {
        this.customerEmialTv = customerEmialTv;
    }

    public String getInvoiceTv() {
        return invoiceTv;
    }

    public void setInvoiceTv(String invoiceTv) {
        this.invoiceTv = invoiceTv;
    }

    public String getTotalAmountTv() {
        return totalAmountTv;
    }

    public void setTotalAmountTv(String totalAmountTv) {
        this.totalAmountTv = totalAmountTv;
    }

    public String getDueTv() {
        return dueTv;
    }

    public void setDueTv(String dueTv) {
        this.dueTv = dueTv;
    }

    public String getProductsName() {
        return productsName;
    }

    public void setProductsName(String productsName) {
        this.productsName = productsName;
    }

    public String getProductQuantityTv() {
        return productQuantityTv;
    }

    public void setProductQuantityTv(String productQuantityTv) {
        this.productQuantityTv = productQuantityTv;
    }

    public String getProductPriceTv() {
        return productPriceTv;
    }

    public void setProductPriceTv(String productPriceTv) {
        this.productPriceTv = productPriceTv;
    }

    public String getTotalBillTv() {
        return totalBillTv;
    }

    public void setTotalBillTv(String totalBillTv) {
        this.totalBillTv = totalBillTv;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPayableTv() {
        return payableTv;
    }

    public void setPayableTv(String payableTv) {
        this.payableTv = payableTv;
    }

    public String getDepositTv() {
        return depositTv;
    }

    public void setDepositTv(String depositTv) {
        this.depositTv = depositTv;
    }

    public String getCurrentDueTv() {
        return currentDueTv;
    }

    public void setCurrentDueTv(String currentDueTv) {
        this.currentDueTv = currentDueTv;
    }
}

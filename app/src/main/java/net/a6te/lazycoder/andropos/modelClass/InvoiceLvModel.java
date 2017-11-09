package net.a6te.lazycoder.andropos.modelClass;

import java.util.ArrayList;

/**
 * Created by Programmer on 5/9/2017.
 */

public class InvoiceLvModel {

    private String invoiceSerial;
    private String invoiceProduct;
    private String invoiceProductAmount;
    private String invoiceId;
    private String invoiceProductPrice;
    private String invoiceProductSellPrice;
    private String invoiceProfit;
    private String invoiceDate;
    private String invoiceTotalProfit;
    private String invoiceCustomer;

    public InvoiceLvModel(String invoiceSerial, String invoiceProduct, String invoiceProductAmount, String invoiceId, String invoiceProductPrice, String invoiceProductSellPrice, String invoiceProfit, String invoiceDate, String invoiceTotalProfit, String invoiceCustomer) {

        this.invoiceSerial = invoiceSerial;
        this.invoiceProduct = invoiceProduct;
        this.invoiceProductAmount = invoiceProductAmount;
        this.invoiceId = invoiceId;
        this.invoiceProductPrice = invoiceProductPrice;
        this.invoiceProductSellPrice = invoiceProductSellPrice;
        this.invoiceProfit = invoiceProfit;
        this.invoiceDate = invoiceDate;
        this.invoiceTotalProfit = invoiceTotalProfit;
        this.invoiceCustomer = invoiceCustomer;
    }

    public InvoiceLvModel() {
    }

    public String getInvoiceSerial() {
        return invoiceSerial;
    }

    public void setInvoiceSerial(String invoiceSerial) {
        this.invoiceSerial = invoiceSerial;
    }

    public String getInvoiceProduct() {
        return invoiceProduct;
    }

    public void setInvoiceProduct(String invoiceProduct) {
        this.invoiceProduct = invoiceProduct;
    }

    public String getInvoiceProductAmount() {
        return invoiceProductAmount;
    }

    public void setInvoiceProductAmount(String invoiceProductAmount) {
        this.invoiceProductAmount = invoiceProductAmount;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceProductPrice() {
        return invoiceProductPrice;
    }

    public void setInvoiceProductPrice(String invoiceProductPrice) {
        this.invoiceProductPrice = invoiceProductPrice;
    }

    public String getInvoiceProductSellPrice() {
        return invoiceProductSellPrice;
    }

    public void setInvoiceProductSellPrice(String invoiceProductSellPrice) {
        this.invoiceProductSellPrice = invoiceProductSellPrice;
    }

    public String getInvoiceProfit() {
        return invoiceProfit;
    }

    public void setInvoiceProfit(String invoiceProfit) {
        this.invoiceProfit = invoiceProfit;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceTotalProfit() {
        return invoiceTotalProfit;
    }

    public void setInvoiceTotalProfit(String invoiceTotalProfit) {
        this.invoiceTotalProfit = invoiceTotalProfit;
    }

    public String getInvoiceCustomer() {
        return invoiceCustomer;
    }

    public void setInvoiceCustomer(String invoiceCustomer) {
        this.invoiceCustomer = invoiceCustomer;
    }

    public ArrayList<InvoiceLvModel> getAllSampleInvoiceData(){
        ArrayList<InvoiceLvModel> invoiceList = new ArrayList<>();

        invoiceList.add(new InvoiceLvModel("01","Laptop","2pcs","AnIn-02","30000","35000","5000","09.06.17","70000","WalkThrough"));
        invoiceList.add(new InvoiceLvModel("02","Desktop","2pcs","AnIn-03","10000","20000","10000","09.08.17","10000","Atik"));
        invoiceList.add(new InvoiceLvModel("03","Tv","4pcs","AnIn-04","50000","55000","5000","20.06.17","90000","WalkThrough"));
        invoiceList.add(new InvoiceLvModel("04","Tv","1pcs","AnIn-010","20000","35000","5000","09.06.17","70000","Asif"));
        invoiceList.add(new InvoiceLvModel("05","Mobile","2pcs","AnIn-011","60000","70000","5000","09.12.17","100000","WalkThrough"));
        return invoiceList;
    }
}

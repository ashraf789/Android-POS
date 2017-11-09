package net.a6te.lazycoder.andropos.modelClass;

/**
 * Created by Programmer on 6/6/2017.
 */

public class SellsDatabaseModel {

    private int id;
    private String sellsCode;
    private String customerId;
    private String totalAmount;
    private String discount;
    private String payAmount;
    private String paymentType;
    private String sellDate;
    private String paymentStatus;
    private String sellBy;

    public SellsDatabaseModel() {
    }

    public SellsDatabaseModel(String sellsCode, String customerId, String totalAmount, String discount, String payAmount, String paymentType, String sellDate, String paymentStatus, String sellBy) {

        this.sellsCode = sellsCode;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.payAmount = payAmount;
        this.paymentType = paymentType;
        this.sellDate = sellDate;
        this.paymentStatus = paymentStatus;
        this.sellBy = sellBy;
    }

    public SellsDatabaseModel(int id, String sellsCode, String customerId, String totalAmount, String discount, String payAmount, String paymentType, String sellDate, String paymentStatus, String sellBy) {
        this.id = id;
        this.sellsCode = sellsCode;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.payAmount = payAmount;
        this.paymentType = paymentType;
        this.sellDate = sellDate;
        this.paymentStatus = paymentStatus;
        this.sellBy = sellBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSellsCode() {
        return sellsCode;
    }

    public void setSellsCode(String sellsCode) {
        this.sellsCode = sellsCode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getSellBy() {
        return sellBy;
    }

    public void setSellBy(String sellBy) {
        this.sellBy = sellBy;
    }
}

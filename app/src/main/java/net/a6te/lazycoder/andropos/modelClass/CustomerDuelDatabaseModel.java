package net.a6te.lazycoder.andropos.modelClass;

/**
 * Created by Programmer on 5/23/2017.
 */

public class CustomerDuelDatabaseModel {

    private int duseId;
    private String customerId;
    private String totalAmount;
    private String totalPayAmount;
    private String dueAmount;
    private String sellsCode;
    private String payDueDate;
    private String payDeposit;


    public CustomerDuelDatabaseModel() {
    }

    public CustomerDuelDatabaseModel(String customerId, String totalAmount, String totalPayAmount, String due, String sells_code, String payDueDate) {
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.totalPayAmount = totalPayAmount;
        this.dueAmount = due;
        this.sellsCode = sells_code;
        this.payDueDate = payDueDate;
    }

    public CustomerDuelDatabaseModel(int dueId, String customerId, String totalAmount, String totalPayAmount, String due, String sells_code, String payDueDate) {
        this.duseId = dueId;
        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.totalPayAmount = totalPayAmount;
        this.dueAmount = due;
        this.sellsCode = sells_code;
        this.payDueDate = payDueDate;
    }

    public CustomerDuelDatabaseModel(String customerId, String totalAmount, String totalPayAmount, String dueAmount, String sellsCode, String payDueDate, String payDeposit) {

        this.customerId = customerId;
        this.totalAmount = totalAmount;
        this.totalPayAmount = totalPayAmount;
        this.dueAmount = dueAmount;
        this.sellsCode = sellsCode;
        this.payDueDate = payDueDate;
        this.payDeposit = payDeposit;
    }

    public String getPayDeposit() {
        return payDeposit;
    }

    public void setPayDeposit(String payDeposit) {
        this.payDeposit = payDeposit;
    }

    public int getDuseId() {
        return duseId;
    }

    public void setDuseId(int duseId) {
        this.duseId = duseId;
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

    public String getTotalPayAmount() {
        return totalPayAmount;
    }

    public void setTotalPayAmount(String totalPayAmount) {
        this.totalPayAmount = totalPayAmount;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getSellsCode() {
        return sellsCode;
    }

    public void setSellsCode(String sellsCode) {
        this.sellsCode = sellsCode;
    }

    public String getPayDueDate() {
        return payDueDate;
    }

    public void setPayDueDate(String payDueDate) {
        this.payDueDate = payDueDate;
    }
}

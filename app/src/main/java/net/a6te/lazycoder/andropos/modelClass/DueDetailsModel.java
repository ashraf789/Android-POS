package net.a6te.lazycoder.andropos.modelClass;

/**
 * Created by Programmer on 6/16/2017.
 */

public class DueDetailsModel {

    private String name;
    private String phone;
    private String email;
    private String sellCode;
    private String totalAmount;
    private String paid;
    private String date;
    private String due;
    private String customerCode;

    public DueDetailsModel() {
    }

    public DueDetailsModel(String name, String phone, String email, String type, String amount, String pay, String date, String due) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.sellCode = type;
        this.totalAmount = amount;
        this.paid = pay;
        this.date = date;
        this.due = due;
    }

    public DueDetailsModel(String name, String phone, String email, String type, String totalAmount, String paid, String date, String due, String customerId) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.sellCode = type;
        this.totalAmount = totalAmount;
        this.paid = paid;
        this.date = date;
        this.due = due;
        this.customerCode = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSellCode() {
        return sellCode;
    }

    public void setSellCode(String sellCode) {
        this.sellCode = sellCode;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}

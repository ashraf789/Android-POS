package net.a6te.lazycoder.andropos.modelClass;

/**
 * Created by Programmer on 5/23/2017.
 */

public class CustomerModel {

    private int id;
    private String customerName;
    private String customerCode;
    private String customerType;
    private String customerGender;
    private String customerPhone;
    private String customerEmail;
    private String customerAddress;
    private String customerDueAmount;

    public CustomerModel() {
    }

    public CustomerModel(String customerName, String customerCode, String customerType, String customerGender, String customerPhone, String customerEmail, String customerAddress, String customerDueAmount) {

        this.customerName = customerName;
        this.customerCode = customerCode;
        this.customerType = customerType;
        this.customerGender = customerGender;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerDueAmount = customerDueAmount;
    }

    public CustomerModel(int id, String customerName, String customerCode, String customerType, String customerGender, String customerPhone, String customerEmail, String customerAddress, String customerDueAmount) {

        this.id = id;
        this.customerName = customerName;
        this.customerCode = customerCode;
        this.customerType = customerType;
        this.customerGender = customerGender;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerDueAmount = customerDueAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerDueAmount() {
        return customerDueAmount;
    }

    public void setCustomerDueAmount(String customerDueAmount) {
        this.customerDueAmount = customerDueAmount;
    }
}

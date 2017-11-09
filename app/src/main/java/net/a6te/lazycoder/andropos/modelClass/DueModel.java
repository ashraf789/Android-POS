package net.a6te.lazycoder.andropos.modelClass;

import java.util.ArrayList;

/**
 * Created by Programmer on 5/10/2017.
 */

public class DueModel {

    private String serialNo;
    private String dueAmount;
    private String customerName;
    private String customerPhone;
    private String dueId;

    public DueModel(String serialNo, String dueAmount, String customerName, String customerPhone) {
        this.serialNo = serialNo;
        this.dueAmount = dueAmount;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
    }

    public DueModel(String serialNo, String dueAmount, String customerName, String customerPhone, String dueId) {
        this.serialNo = serialNo;
        this.dueAmount = dueAmount;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.dueId = dueId;
    }

    public String getDueId() {
        return dueId;
    }

    public void setDueId(String dueId) {
        this.dueId = dueId;
    }

    public DueModel() {
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public ArrayList<DueModel> getAllDuesList(){
        ArrayList<DueModel> dues = new ArrayList<>();
        dues.add(new DueModel("01","6000tk","Atik","01772465146"));
        dues.add(new DueModel("02","10000tk","asif","01772465146"));
        dues.add(new DueModel("03","60000tk","fahim","01772465146"));
        dues.add(new DueModel("04","3000tk","dipto","01772465146"));

        return dues;
    }
}

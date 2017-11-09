package net.a6te.lazycoder.andropos.modelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Programmer on 6/19/2017.
 */

public class CustomerOnlineDataModel {

    @SerializedName("companyInfo")
    @Expose
    private List<CompanyInfo> companyInfo = null;
    @SerializedName("customerInfo")
    @Expose
    private List<CustomerInfo> customerInfo = null;

    public List<CompanyInfo> getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(List<CompanyInfo> companyInfo) {
        this.companyInfo = companyInfo;
    }

    public List<CustomerInfo> getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(List<CustomerInfo> customerInfo) {
        this.customerInfo = customerInfo;
    }

    public class CompanyInfo {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("contact_no")
        @Expose
        private String contactNo;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("logo")
        @Expose
        private String logo;
        @SerializedName("created_date")
        @Expose
        private String createdDate;
        @SerializedName("modified_date")
        @Expose
        private String modifiedDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContactNo() {
            return contactNo;
        }

        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(String modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

    }

    public class CustomerInfo {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("full_name")
        @Expose
        private String fullName;
        @SerializedName("customer_code")
        @Expose
        private String customerCode;
        @SerializedName("customer_type")
        @Expose
        private Object customerType;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("phone_no")
        @Expose
        private String phoneNo;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("due_amount")
        @Expose
        private Object dueAmount;
        @SerializedName("image")
        @Expose
        private Object image;
        @SerializedName("emp_id")
        @Expose
        private String empId;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getCustomerCode() {
            return customerCode;
        }

        public void setCustomerCode(String customerCode) {
            this.customerCode = customerCode;
        }

        public Object getCustomerType() {
            return customerType;
        }

        public void setCustomerType(Object customerType) {
            this.customerType = customerType;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Object getDueAmount() {
            return dueAmount;
        }

        public void setDueAmount(Object dueAmount) {
            this.dueAmount = dueAmount;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public String getEmpId() {
            return empId;
        }

        public void setEmpId(String empId) {
            this.empId = empId;
        }

    }
}

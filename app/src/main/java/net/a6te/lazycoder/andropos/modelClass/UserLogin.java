package net.a6te.lazycoder.andropos.modelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Programmer on 6/18/2017.
 */

public class UserLogin {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("employinfo")
    @Expose
    private Employinfo employinfo;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Employinfo getEmployinfo() {
        return employinfo;
    }

    public void setEmployinfo(Employinfo employinfo) {
        this.employinfo = employinfo;
    }

    public class Employinfo {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("full_name")
        @Expose
        private String fullName;
        @SerializedName("phone_no")
        @Expose
        private String phoneNo;
        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("position_id")
        @Expose
        private String positionId;
        @SerializedName("password")
        @Expose
        private String password;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPositionId() {
            return positionId;
        }

        public void setPositionId(String positionId) {
            this.positionId = positionId;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }
}

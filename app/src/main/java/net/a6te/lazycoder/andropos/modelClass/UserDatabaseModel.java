package net.a6te.lazycoder.andropos.modelClass;

/**
 * Created by Programmer on 5/23/2017.
 */

public class UserDatabaseModel {

    private int id;
    private String userName;
    private String userEmail;
    private String userPassword;
    private String userPhone;
    private String userEmployeeId;

    public UserDatabaseModel() {
    }

    public UserDatabaseModel(String userName, String userEmail, String userPassword, String userPhone, String userEmployeeId) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userEmployeeId = userEmployeeId;
    }

    public UserDatabaseModel(int id, String userName, String userEmail, String userPassword, String userPhone, String userEmployeeId) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userPhone = userPhone;
        this.userEmployeeId = userEmployeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmployeeId() {
        return userEmployeeId;
    }

    public void setUserEmployeeId(String userEmployeeId) {
        this.userEmployeeId = userEmployeeId;
    }
}

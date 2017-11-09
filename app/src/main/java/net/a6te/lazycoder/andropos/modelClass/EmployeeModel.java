package net.a6te.lazycoder.andropos.modelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Programmer on 6/19/2017.
 */

public class EmployeeModel {

    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("position_id")
    @Expose
    private String positionId;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("birth_date")
    @Expose
    private Object birthDate;
    @SerializedName("home_district")
    @Expose
    private Object homeDistrict;
    @SerializedName("current_district")
    @Expose
    private Object currentDistrict;
    @SerializedName("profession")
    @Expose
    private Object profession;
    @SerializedName("last_degree")
    @Expose
    private Object lastDegree;
    @SerializedName("last_institute_name")
    @Expose
    private Object lastInstituteName;
    @SerializedName("ref_name")
    @Expose
    private Object refName;
    @SerializedName("ref_phone_number")
    @Expose
    private Object refPhoneNumber;
    @SerializedName("active_inactive")
    @Expose
    private String activeInactive;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("country")
    @Expose
    private Object country;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Object getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Object birthDate) {
        this.birthDate = birthDate;
    }

    public Object getHomeDistrict() {
        return homeDistrict;
    }

    public void setHomeDistrict(Object homeDistrict) {
        this.homeDistrict = homeDistrict;
    }

    public Object getCurrentDistrict() {
        return currentDistrict;
    }

    public void setCurrentDistrict(Object currentDistrict) {
        this.currentDistrict = currentDistrict;
    }

    public Object getProfession() {
        return profession;
    }

    public void setProfession(Object profession) {
        this.profession = profession;
    }

    public Object getLastDegree() {
        return lastDegree;
    }

    public void setLastDegree(Object lastDegree) {
        this.lastDegree = lastDegree;
    }

    public Object getLastInstituteName() {
        return lastInstituteName;
    }

    public void setLastInstituteName(Object lastInstituteName) {
        this.lastInstituteName = lastInstituteName;
    }

    public Object getRefName() {
        return refName;
    }

    public void setRefName(Object refName) {
        this.refName = refName;
    }

    public Object getRefPhoneNumber() {
        return refPhoneNumber;
    }

    public void setRefPhoneNumber(Object refPhoneNumber) {
        this.refPhoneNumber = refPhoneNumber;
    }

    public String getActiveInactive() {
        return activeInactive;
    }

    public void setActiveInactive(String activeInactive) {
        this.activeInactive = activeInactive;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

}

package com.technocracy.app.aavartan.api.data_models;

import android.graphics.drawable.Drawable;

public class ContactsData {

    private Drawable image;
    private String name, email, mobileNumber, branch;
    private int semester;

    public ContactsData(Drawable image, String name, String branch, int semester) {
        this.image = image;
        this.name = name;
        this.branch = branch;
        this.semester = semester;
    }

    public ContactsData(Drawable image, String name, String email, String mobileNumber) {
        this.image = image;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}

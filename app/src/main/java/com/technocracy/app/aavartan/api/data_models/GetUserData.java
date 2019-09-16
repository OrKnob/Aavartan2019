package com.technocracy.app.aavartan.api.data_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetUserData implements Serializable {

    public GetUserData(int userID, String username) {
        this.userID = userID;
        this.username = username;
    }

    @SerializedName("detail")
    @Expose
    private String detail;

    @SerializedName("id")
    @Expose
    private int userID;

    @SerializedName("name")
    @Expose
    private String username;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}

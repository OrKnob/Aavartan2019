package com.technocracy.app.aavartan.api.data_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginData implements Serializable {

    public LoginData(String username, String email, String password, String userToken) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userToken = userToken;
    }

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("key")
    @Expose
    private String userToken;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String toJSONString() {
        return "{" +
                "\"username\" : \" " + username + '\"' +
                ", \"email \" : \" " + email + '\"' +
                ", \"password\" : \"" + password +
                "\"}";
    }
}

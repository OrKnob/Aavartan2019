package com.technocracy.app.aavartan.api.data_models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseAPI implements Serializable {

    public ResponseAPI(String message, String detail) {
        this.message = message;
        this.detail = detail;
    }

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("detail")
    @Expose
    private String detail;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}

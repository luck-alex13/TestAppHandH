package com.example.user.testapphandh.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseRequest {

    @SerializedName("code")
    @Expose
    private int code;

    public BaseRequest(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

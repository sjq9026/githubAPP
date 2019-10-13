package com.sjq.githubapp.javabean;

import java.io.Serializable;
import java.util.ArrayList;


public class WanAndroidResponse implements Serializable {

    /**
     * data : []
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    private ArrayList<WanAndroidEntity> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ArrayList<WanAndroidEntity> getData() {
        return data;
    }

    public void setData(ArrayList<WanAndroidEntity> data) {
        this.data = data;
    }
}

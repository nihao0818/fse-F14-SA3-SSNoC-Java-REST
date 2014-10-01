package edu.cmu.sv.ws.ssnoc.dto;

/**
 * Created by vignan on 9/28/14.
 */

import com.google.gson.Gson;

import java.sql.Timestamp;

/**
 * This object contains user status information that is responded as part of the REST
 * API request.
 *
 */
public class Status {
    private String userName;
    private String statusCode;
    private String createdAt;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getCreatedAt(){ return createdAt;}

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}

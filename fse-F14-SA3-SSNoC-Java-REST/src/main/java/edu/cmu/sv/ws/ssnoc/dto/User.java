package edu.cmu.sv.ws.ssnoc.dto;

import com.google.gson.Gson;

/**
 * This object contains user information that is responded as part of the REST
 * API request.
 * 
 */
public class User {
	private String userName;
	private String password;
    private String statusCode;
    //private String createdAt;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getStatusCode(){return statusCode;}

    public void setStatusCode(String statusCode){this.statusCode = statusCode;}

    //public String getCreatedAt(){return createdAt;}

    //public void setCreatedAt(String createdAt){this.createdAt = createdAt;}

    @Override
	public String toString() {
		return new Gson().toJson(this);
	}

}

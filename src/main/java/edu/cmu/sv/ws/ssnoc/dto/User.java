package edu.cmu.sv.ws.ssnoc.dto;

import com.google.gson.Gson;

/**
 * This object contains user information that is responded as part of the REST
 * API request.
 * 
 */
public class User {
    private long userid;
	private String userName;
	private String password;
    private String statusCode;
    private String statusDate;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

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

    public String getStatusDate(){return statusDate;}

    public void setStatusDate(String statusDate){this.statusDate = statusDate;}

    @Override
	public String toString() {
		return new Gson().toJson(this);
	}

}

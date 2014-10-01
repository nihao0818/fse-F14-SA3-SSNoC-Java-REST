package edu.cmu.sv.ws.ssnoc.data;

/**
 * This class contains all the SQL related code that is used by the project.
 * Note that queries are grouped by their purpose and table associations for
 * easy maintenance.
 * 
 */
public class SQL {
	/*
	 * List the USERS table name, and list all queries related to this table
	 * here.
	 */
	public static final String SSN_USERS = "SSN_USERS";
    public static final String SSN_STATUS_CRUMB="STATUS_CRUMB";

	/**
	 * Query to check if a given table exists in the H2 database.
	 */
	public static final String CHECK_TABLE_EXISTS_IN_DB = "SELECT count(1) as rowCount "
			+ " FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = SCHEMA() "
			+ " AND UPPER(TABLE_NAME) = UPPER(?)";

	// ****************************************************************
	// All queries related to USERS
	// ****************************************************************
	/**
	 * Query to create the USERS table.
	 */
	public static final String CREATE_USERS = "create table IF NOT EXISTS "
			+ SSN_USERS + " ( user_id IDENTITY PRIMARY KEY,"
			+ " user_name VARCHAR(100)," + " password VARCHAR(512),"
            +" createdAt VARCHAR(100)," + " modifiedAt VARCHAR(100),"
            +" last_status_code VARCHAR(100),"
			+ " salt VARCHAR(512) )";

	/**
	 * Query to load all users in the system.
	 */
	public static final String FIND_ALL_USERS = "select user_id, user_name, password, last_status_code, "
			+ " salt " + " from " + SSN_USERS + " order by user_name";

	/**
	 * Query to find a user details depending on his name. Note that this query
	 * does a case insensitive search with the user name.
	 */
	public static final String FIND_USER_BY_NAME = "select user_id, user_name, password, last_status_code,"
			+ " salt "
			+ " from "
			+ SSN_USERS
			+ " where UPPER(user_name) = UPPER(?)";

	/**
	 * Query to insert a row into the users table.
	 */
	public static final String INSERT_USER = "insert into " + SSN_USERS
			+ " (user_name, password , createdAt, salt) values (?, ?, ?, ?)";

    /**
     * Query to update status of a user in User table.
     */
    public static final String UPDATE_STATUS = "update "+SSN_USERS+
            " SET last_status_code = ? where UPPER(user_name) = UPPER(?)";

    //***********************************************************************
    // All queries related to USERS STATUS
    //***********************************************************************
    /**
     *Query to create the STATUS_CRUMB Table
     */
    public static final String CREATE_STATUS_CRUMB = "create table IF NOT EXISTS "
            + SSN_STATUS_CRUMB +" ( user_name VARCHAR(100) REFERENCES SSN_USERS(user_name),"
            +" status_code VARCHAR(15),"+" created_at VARCHAR(100),"
            +" crumb_ID IDENTITY PRIMARY KEY)";
    /**
     * Query to get user STATUS depending on his name. Note that this query
     * does a case insensitive search with the user name
     */
    public static final String FIND_STATUS_BY_CRUMB = "select user_name, status_code, created_at"
            +" from "
            + SSN_STATUS_CRUMB
            +" where UPPER(crumb_ID) = UPPER(?)";
    /**
     * Query to insert a row into the STATUS_CRUMB table
     */
    public static final String INSERT_STATUS = "insert into "+ SSN_STATUS_CRUMB
            +" (user_name,status_code,created_at) values (?,?,?)";


    public static final String FIND_ALL_USER_STATUSES = "select user_name, status_code, created_at,crumb_ID "
            + " from " + SSN_STATUS_CRUMB
            +" where UPPER(user_name) = UPPER(?)"
            + " order by created_at";
}

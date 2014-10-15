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
    public static final String SSN_STATUS_CRUMB="SSN_STATUS_CRUMB";
    public static final String SSN_CHAT="SSN_MESSAGE";
    public static final String SSN_MEMORY_CRUMB="SSN_MEMORY_CRUMB";
    public static final String SSN_PERFORMANCE_CRUMB="SSN_PERFORMANCE_CRUMB";
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
            +" created_date VARCHAR(100)," + " modifiedAt VARCHAR(100),"
            +" last_status_code VARCHAR(100),"+" last_status_date VARCHAR(100),"
			+ " salt VARCHAR(512) )";

	/**
	 * Query to load all users in the system.
	 */
	public static final String FIND_ALL_USERS = "select user_id, user_name, password, last_status_code, last_status_date, "
			+ " salt " + " from " + SSN_USERS + " order by user_name";

	/**
	 * Query to find a user details depending on his name. Note that this query
	 * does a case insensitive search with the user name.
	 */
	public static final String FIND_USER_BY_NAME = "select user_id, user_name, password, last_status_code, last_status_date, "
			+ " salt "
			+ " from "
			+ SSN_USERS
			+ " where UPPER(user_name) = UPPER(?)";

	/**
	 * Query to insert a row into the users table.
	 */
	public static final String INSERT_USER = "insert into " + SSN_USERS
			+ " (user_name, password , created_date, salt) values (?, ?, ?, ?)";

    /**
     * Query to update status of a user in User table.
     */
    public static final String UPDATE_STATUS = "update "+SSN_USERS+
            " SET last_status_code = ? , last_status_date =? where UPPER(user_name) = UPPER(?)";

    //***********************************************************************
    // All queries related to USERS STATUS
    //***********************************************************************
    /**
     *Query to create the STATUS_CRUMB Table
     */
    public static final String CREATE_STATUS_CRUMB = "create table IF NOT EXISTS "
            + SSN_STATUS_CRUMB +" ( user_name VARCHAR(100) REFERENCES SSN_USERS(user_name),"
            +" status_code VARCHAR(15),"+" created_date VARCHAR(100),"
            +" crumb_ID IDENTITY PRIMARY KEY)";
    /**
     * Query to get user STATUS depending on his name. Note that this query
     * does a case insensitive search with the user name
     */
    public static final String FIND_STATUS_BY_CRUMB = "select user_name, status_code, created_date"
            +" from "
            + SSN_STATUS_CRUMB
            +" where UPPER(crumb_ID) = UPPER(?)";
    /**
     * Query to insert a row into the STATUS_CRUMB table
     */
    public static final String INSERT_STATUS = "insert into "+ SSN_STATUS_CRUMB
            +" (user_name,status_code,created_Date) values (?,?,?)";


    public static final String FIND_ALL_USER_STATUSES = "select user_name, status_code, created_date,crumb_ID "
            + " from " + SSN_STATUS_CRUMB
            +" where UPPER(user_name) = UPPER(?)"
            + " order by created_date";

    // ****************************************************************
    // All queries related to CHAT
    // ****************************************************************
    /**
     * Query to create the CHAT table.
     */
    public static final String CREATE_CHAT = "create table IF NOT EXISTS "
            + SSN_CHAT +" ( messageID IDENTITY PRIMARY KEY, author VARCHAR(100), message_type VARCHAR(20),target varchar(100), " +
            "postedAt VARCHAR(100), location varchar(100), content varchar(1000))";

    public static final String INSERT_CHAT = "insert into "+ SSN_CHAT
            +" (author, message_type, target,postedAT,content) values (?,?,?,?,?)";

    public static final String FIND_ALL_WALL_MESSAGES = "select author, target, content, postedAt"
            +" from "
            + SSN_CHAT
            +" where UPPER(message_type)='WALL' "
            +"order by postedAt";

    public static final String FIND_CHAT_MESSAGES = "select author, target, content, postedAt"
            +" from "
            + SSN_CHAT
            +" where UPPER(message_type)='CHAT' "
            +"and ((UPPER(author) = UPPER(?) and UPPER(target) = UPPER(?)) OR (UPPER(target) = UPPER(?) and UPPER(author) = UPPER(?)))"
            +" order by postedAt";

    public static final String FIND_CHAT_BUDDIES = "select distinct target" +" from " + SSN_CHAT
            +" where UPPER(message_type)='CHAT' "
            +"and UPPER(author)=UPPER(?)";

    // ****************************************************************
    // All queries related to MemoryCrumb
    // ****************************************************************
    public static final String CREATE_MEMORY_CRUMB = "create table IF NOT EXISTS "
            + SSN_MEMORY_CRUMB +" ( crumbID IDENTITY PRIMARY KEY, usedVMemory long, remainingVMemory long,usedPersistent long, " +
            "remainingPersistent long, createdAt varchar(20))";

    public static final String INSERT_MEMORY_STATS = "insert into "+ SSN_MEMORY_CRUMB
            +" (usedVMemory, remainingVMemory, usedPersistent,remainingPersistent,createdAt) values (?,?,?,?,?)";

    public static final String DELETE_MEMORY_STATS = "Truncate table "+SSN_MEMORY_CRUMB;

    public static final String GET_MEMORY_STATS = "select usedVMemory, remainingVMemory, usedPersistent, remainingPersistent, " +
            "createdAt from "+SSN_MEMORY_CRUMB+" where createdAt between (?) and (?)"+" order by createdAt DESC";
    // ****************************************************************
    // All queries related to PerformanceCrumb
    // ****************************************************************
    public static final String CREATE_PERFORMANCE_CRUMB = "create table IF NOT EXISTS "
            + SSN_PERFORMANCE_CRUMB +" ( postsPerSecond long, getsPerSecond long)";

    public static final String INSERT_PERFORMANCE_STATS = "insert into "+SSN_PERFORMANCE_CRUMB
            +"( postsPerSecond, getsPerSecond) values (?,?)";

}
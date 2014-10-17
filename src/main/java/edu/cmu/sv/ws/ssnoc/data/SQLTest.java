package edu.cmu.sv.ws.ssnoc.data;

/**
 * Created by Vignan on 10/14/2014.
 */
public class SQLTest {

    public static final String SSN_CHAT_TEST="SSN_MESSAGE_TEST";
    public static final String SSN_USERS_TEST = "SSN_USERS";

    public static final String CREATE_CHAT_TEST = "create table IF NOT EXISTS "
            + SSN_CHAT_TEST +" ( messageID IDENTITY PRIMARY KEY, author VARCHAR(100), message_type VARCHAR(20),target varchar(100), " +
            "postedAt VARCHAR(100), location varchar(100), content varchar(1000))";

    public static final String INSERT_CHAT_TEST = "insert into "+ SSN_CHAT_TEST
            +" (author, message_type, target,postedAT,content) values (?,?,?,?,?)";

    public static final String FIND_ALL_WALL_MESSAGES_TEST = "select author, target, content, postedAt"
            +" from "
            + SSN_CHAT_TEST
            +" where UPPER(message_type)='WALL' "
            +"order by postedAt";

    public static final String DELETE_TABLE_TEST = "drop table "+SSN_CHAT_TEST;

 /*   public static final String FIND_CHAT_MESSAGES = "select author, target, content, postedAt"
            +" from "
            + SSN_CHAT_TEST
            +" where UPPER(message_type)='CHAT' "
            +"and ((UPPER(author) = UPPER(?) and UPPER(target) = UPPER(?)) OR (UPPER(target) = UPPER(?) and UPPER(author) = UPPER(?)))"
            +" order by postedAt";

    public static final String FIND_CHAT_BUDDIES = "select distinct target" +" from " + SSN_CHAT_TEST
            +" where UPPER(message_type)='CHAT' "
            +"and UPPER(author)=UPPER(?)";
  */
}

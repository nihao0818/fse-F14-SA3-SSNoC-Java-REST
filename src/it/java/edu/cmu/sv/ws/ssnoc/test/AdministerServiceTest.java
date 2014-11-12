package edu.cmu.sv.ws.ssnoc.test;

import edu.cmu.sv.ws.ssnoc.data.SQL;
import edu.cmu.sv.ws.ssnoc.data.dao.BaseDAOImpl;
import edu.cmu.sv.ws.ssnoc.dto.User;
import edu.cmu.sv.ws.ssnoc.rest.UserService;
import org.junit.*;


import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.Assert.assertEquals;

/**
 * Created by Tangent on 10/24/14.
 */
public class AdministerServiceTest extends BaseDAOImpl {
    User testUpdatedOne = new User();
    User testUpdatedTwo = new User();

    @Before
    public void setUpUserData() throws Exception {

        Connection conn = getConnection();
        PreparedStatement stmtInsertUser = conn.prepareStatement(SQL.INSERT_USER);
        stmtInsertUser.setString(1, "HelloBaby");
        stmtInsertUser.setString(2, null);
        stmtInsertUser.setString(3, null);
        stmtInsertUser.setString(4, null);
        stmtInsertUser.setString(5, null);
        stmtInsertUser.setString(6, null);
        stmtInsertUser.execute();

        testUpdatedOne.setUserName("ahaha");
        testUpdatedOne.setPassword("newPassword");
        testUpdatedOne.setAccountStatus("1");
        testUpdatedOne.setPrivilegeLevel("Administrator");

    }

    @Test
    public void administerUserProfileTest() throws Exception {
        Connection conn = getConnection();
        PreparedStatement stmtInsertUser = conn.prepareStatement(SQL.INSERT_USER);
        stmtInsertUser.setString(1, "HelloBaby");
        stmtInsertUser.setString(2, null);
        stmtInsertUser.setString(3, null);
        stmtInsertUser.setString(4, null);
        stmtInsertUser.setString(5, null);
        stmtInsertUser.setString(6, null);
        stmtInsertUser.execute();

        testUpdatedOne.setUserName("ahaha");
        testUpdatedOne.setPassword("newPassword");
        testUpdatedOne.setAccountStatus("1");
        testUpdatedOne.setPrivilegeLevel("Administrator");
    }

//    @Test
//    public void administerUserProfile(){
//
//        UserService administerTest = new UserService();
//        String result = administerTest.administerUserProfile("HelloBaby", testUpdatedOne);
//        assertTrue(result.equals("created"));
//
//    }

    @After
    public void recoverTestData() throws Exception {
        Connection conn = getConnection();
        String dropTable = "DROP table SSN_USERS";
        PreparedStatement stmtDrop = conn.prepareStatement(dropTable);
        stmtDrop.execute();

        PreparedStatement stmtCreateUsers = conn.prepareStatement(SQL.CREATE_USERS);
        stmtCreateUsers.execute();

        conn.close();
    }
}

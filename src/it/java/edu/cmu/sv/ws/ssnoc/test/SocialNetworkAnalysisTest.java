package edu.cmu.sv.ws.ssnoc.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.data.SQL;
import edu.cmu.sv.ws.ssnoc.data.dao.DAOFactory;
import edu.cmu.sv.ws.ssnoc.data.dao.IPerformanceDAO;
import edu.cmu.sv.ws.ssnoc.data.po.UserPO;
import edu.cmu.sv.ws.ssnoc.rest.SocialNetworkAnalysis;
import edu.cmu.sv.ws.ssnoc.dto.ExchangeInfo;
import edu.cmu.sv.ws.ssnoc.dto.User;
import edu.cmu.sv.ws.ssnoc.data.dao.BaseDAOImpl;
import edu.cmu.sv.ws.ssnoc.data.SQLTest;
import edu.cmu.sv.ws.ssnoc.data.dao.PerformanceDAOImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.lang.reflect.Method;
import java.lang.Class;


/**
 * Created by YHWH on 10/10/14.
 */
public class SocialNetworkAnalysisTest extends BaseDAOImpl{
    ExchangeInfo message1, message2, message3, message4;
    User userA, userB, userC,userD, userE;

    @Before
    public void setUpTestData() throws Exception{

        Connection conn= getConnection();
        PreparedStatement stmtCreateUsers = conn.prepareStatement(SQL.CREATE_USERS);
        stmtCreateUsers.execute();
        PreparedStatement stmtCreateChat = conn.prepareStatement(SQL.CREATE_CHAT);
        stmtCreateChat.execute();
        PreparedStatement stmtInsertUser = conn.prepareStatement(SQL.INSERT_USER);
        stmtInsertUser.setString(1, "A");
        stmtInsertUser.setString(2, null);
        stmtInsertUser.setString(3, null);
        stmtInsertUser.setString(4, null);
        stmtInsertUser.execute();
        stmtInsertUser.setString(1, "B");
        stmtInsertUser.setString(2, null);
        stmtInsertUser.setString(3, null);
        stmtInsertUser.setString(4, null);
        stmtInsertUser.execute();
        stmtInsertUser.setString(1, "C");
        stmtInsertUser.setString(2, null);
        stmtInsertUser.setString(3, null);
        stmtInsertUser.setString(4, null);
        stmtInsertUser.execute();
        stmtInsertUser.setString(1, "D");
        stmtInsertUser.setString(2, null);
        stmtInsertUser.setString(3, null);
        stmtInsertUser.setString(4, null);
        stmtInsertUser.execute();
        stmtInsertUser.setString(1, "E");
        stmtInsertUser.setString(2, null);
        stmtInsertUser.setString(3, null);
        stmtInsertUser.setString(4, null);
        stmtInsertUser.execute();

        PreparedStatement stmtInsertChat = conn.prepareStatement(SQL.INSERT_CHAT);
        stmtInsertChat.setString(1, "A");
        stmtInsertChat.setString(2, "CHAT");
        stmtInsertChat.setString(3, "B");
        stmtInsertChat.setString(4, "20141001143325");
        stmtInsertChat.setString(5, "test");
        stmtInsertChat.execute();
        stmtInsertChat.setString(1, "A");
        stmtInsertChat.setString(2, "CHAT");
        stmtInsertChat.setString(3, "C");
        stmtInsertChat.setString(4, "20141005143325");
        stmtInsertChat.setString(5, "test");
        stmtInsertChat.execute();
        stmtInsertChat.setString(1, "C");
        stmtInsertChat.setString(2, "CHAT");
        stmtInsertChat.setString(3, "D");
        stmtInsertChat.setString(4, "20141010143325");
        stmtInsertChat.setString(5, "test");
        stmtInsertChat.execute();
        stmtInsertChat.setString(1, "B");
        stmtInsertChat.setString(2, "CHAT");
        stmtInsertChat.setString(3, "A");
        stmtInsertChat.setString(4, "20141015143325");
        stmtInsertChat.setString(5, "test");
        stmtInsertChat.execute();



        //result = processResult(stmtCreateUser);



    }

    @Test
    public void loadAllUsersTest() {
        SocialNetworkAnalysis analysisTest = new SocialNetworkAnalysis();
        List<User> testData = new ArrayList<User>();
        User data1 = new User();
            data1.setUserName("A");
        User data2 = new User();
            data2.setUserName("B");
        User data3 = new User();
            data3.setUserName("C");
        User data4 = new User();
            data4.setUserName("D");
        User data5 = new User();
            data5.setUserName("E");

        testData.add(data1);
        testData.add(data2);
        testData.add(data3);
        testData.add(data4);
        testData.add(data5);
        //List<User> result = analysisTest.loadAllUsers();
        assertEquals(testData.size(), analysisTest.loadAllUsers().size());
        //assertTrue(testData.contains(analysisTest.loadAllUsers()));

    }

    @Test
    public void loadChatBuddiesTest() {
        SocialNetworkAnalysis analysisTest = new SocialNetworkAnalysis();
        String startTime = "20141001000000";
        String endTime = "20141031235959";

        List<List<User>> testData = new ArrayList<List<User>>();
        User userA = new User();
            userA.setUserName("A");
        User userB = new User();
            userA.setUserName("B");
        User userC = new User();
            userA.setUserName("C");
        User userD = new User();
            userA.setUserName("D");

        List<User> data1 = new ArrayList<User>();
            data1.add(userA);
            data1.add(userB);
        List<User> data2 = new ArrayList<User>();
            data2.add(userA);
            data2.add(userC);
        List<User> data3 = new ArrayList<User>();
            data3.add(userC);
            data3.add(userD);

        testData.add(data1);
        testData.add(data2);
        testData.add(data3);

        assertEquals(testData.size(), analysisTest.loadChatBuddies(startTime, endTime).size());

    }

    @Test
    public void analyzeSocialNetworkTest() {
        SocialNetworkAnalysis analysisTest = new SocialNetworkAnalysis();

        List<List<User>> clusters = new ArrayList<List<User>>();

        User userA = new User();
            userA.setUserName("A");
        User userB = new User();
            userB.setUserName("B");
        User userC = new User();
            userC.setUserName("C");
        User userD = new User();
            userD.setUserName("D");
        User userE = new User();
            userE.setUserName("E");

        List<User> cluster1 = new ArrayList<User>();
            cluster1.add(userA);
            cluster1.add(userD);
            cluster1.add(userE);
        List<User> cluster2 = new ArrayList<User>();
            cluster2.add(userC);
            cluster2.add(userE);
        List<User> cluster3 = new ArrayList<User>();
            cluster3.add(userD);
            cluster3.add(userE);
        List<User> cluster4 = new ArrayList<User>();
            cluster4.add(userB);
            cluster4.add(userC);
            cluster4.add(userE);
        List<User> cluster5 = new ArrayList<User>();
            cluster5.add(userB);
            cluster5.add(userD);
            cluster5.add(userE);

        clusters.add(cluster1);
        clusters.add(cluster2);
        clusters.add(cluster3);
        clusters.add(cluster4);
        clusters.add(cluster5);

        assertEquals(clusters.size(), analysisTest.loadAllUsers().size());
    }

    @After
    public void clearTestData() throws Exception{
        Connection conn= getConnection();
        String dropTable = "drop table SSN_MESSAGE, SSN_USERS";
        PreparedStatement stmtDrop = conn.prepareStatement(dropTable);
        stmtDrop.execute();
    }




}

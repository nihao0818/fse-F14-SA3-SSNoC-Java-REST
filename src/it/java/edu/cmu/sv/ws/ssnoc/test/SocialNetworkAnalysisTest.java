package edu.cmu.sv.ws.ssnoc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import edu.cmu.sv.ws.ssnoc.data.SQL;

import edu.cmu.sv.ws.ssnoc.rest.SocialNetworkAnalysis;
import edu.cmu.sv.ws.ssnoc.dto.ExchangeInfo;
import edu.cmu.sv.ws.ssnoc.dto.User;
import edu.cmu.sv.ws.ssnoc.data.dao.BaseDAOImpl;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;





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
        stmtInsertChat.setString(1, "4");
        stmtInsertChat.setString(2, "CHAT");
        stmtInsertChat.setString(3, "5");
        stmtInsertChat.setString(4, "2014-10-01 14:33");
        stmtInsertChat.setString(5, "test");
        stmtInsertChat.execute();
        stmtInsertChat.setString(1, "2");
        stmtInsertChat.setString(2, "CHAT");
        stmtInsertChat.setString(3, "4");
        stmtInsertChat.setString(4, "2014-10-05 14:33");
        stmtInsertChat.setString(5, "test");
        stmtInsertChat.execute();
        stmtInsertChat.setString(1, "1");
        stmtInsertChat.setString(2, "CHAT");
        stmtInsertChat.setString(3, "3");
        stmtInsertChat.setString(4, "2014-10-10 14:33");
        stmtInsertChat.setString(5, "test");
        stmtInsertChat.execute();
        stmtInsertChat.setString(1, "3");
        stmtInsertChat.setString(2, "CHAT");
        stmtInsertChat.setString(3, "1");
        stmtInsertChat.setString(4, "2014-10-15 14:33");
        stmtInsertChat.setString(5, "test");
        stmtInsertChat.execute();



        //result = processResult(stmtCreateUser);



    }

    @Test
    public void loadAllUsersTest() {
        SocialNetworkAnalysis analysisTest = new SocialNetworkAnalysis();
        List<String> testData = new ArrayList<String>();
        String data1 = "A";
        String data2 = "B";
        String data3 = "C";
        String data4 = "D";
        String data5 = "E";

        testData.add(data1);
        testData.add(data2);
        testData.add(data3);
        testData.add(data4);
        testData.add(data5);
        //List<User> result = analysisTest.loadAllUsers();
        assertEquals(testData, analysisTest.loadAllUsers());
        //assertTrue(testData.contains(analysisTest.loadAllUsers()));

    }

    @Test
    public void loadChatBuddiesTest() {
        SocialNetworkAnalysis analysisTest = new SocialNetworkAnalysis();
        String startTime = "2014-10-01 00:00";
        String endTime = "2014-10-31 23:59";

        List<List<String>> testData = new ArrayList<List<String>>();
        String userA = "A";
        String userB = "B";
        String userC = "C";
        String userD = "D";
        String userE = "E";

        List<String> data1 = new ArrayList<String>();
            data1.add(userD);
            data1.add(userE);
        List<String> data2 = new ArrayList<String>();
            data2.add(userB);
            data2.add(userD);
        List<String> data3 = new ArrayList<String>();
            data3.add(userA);
            data3.add(userC);

        testData.add(data1);
        testData.add(data2);
        testData.add(data3);

        List<List<String>> result = analysisTest.loadChatBuddies(startTime, endTime);

        assertEquals(testData, result);

    }

    @Test
    public void analyzeSocialNetworkTest() {
        SocialNetworkAnalysis analysisTest = new SocialNetworkAnalysis();
        String startTime = "2014-10-01 00:00";
        String endTime = "2014-10-31 23:59";

        List<List<String>> clusters = new ArrayList<List<String>>();

        String userA = "A";
        String userB = "B";
        String userC = "C";
        String userD = "D";
        String userE = "E";

        List<String> cluster1 = new ArrayList<String>();
            cluster1.add(userA);
            cluster1.add(userB);
        List<String> cluster2 = new ArrayList<String>();
            cluster2.add(userB);
            cluster2.add(userC);
        List<String> cluster3 = new ArrayList<String>();
            cluster3.add(userA);
            cluster3.add(userD);
        List<String> cluster4 = new ArrayList<String>();
            cluster4.add(userC);
            cluster4.add(userD);
        List<String> cluster5 = new ArrayList<String>();
            cluster5.add(userA);
            cluster5.add(userB);
            cluster5.add(userE);
        List<String> cluster6 = new ArrayList<String>();
            cluster6.add(userB);
            cluster6.add(userC);
            cluster6.add(userE);

        clusters.add(cluster1);
        clusters.add(cluster2);
        clusters.add(cluster3);
        clusters.add(cluster4);
        clusters.add(cluster5);
        clusters.add(cluster6);


        Response result = analysisTest.analyzeSocialNetwork(startTime, endTime);
        String res = result.getEntity().toString();
        System.out.println(res);

        String ans = "[";
        for(int i= 0; i < clusters.size();i++){
            ans += "[";
            for(int j=0; j < clusters.get(i).size(); j++){
                ans += "\"";
                ans += clusters.get(i).get(j);
                ans += "\", ";
            }
            ans.substring(0,ans.length()-2);
            ans += "], ";
        }
        ans.substring(0,ans.length()-2);
        ans += "]";


//        assertTrue(clusters.containsAll(result) && result.containsAll(clusters));
        assertEquals(res,ans);
    }

    @After
    public void clearTestData() throws Exception{
        Connection conn= getConnection();
        String dropTable = "drop table SSN_MESSAGE, SSN_USERS";
        PreparedStatement stmtDrop = conn.prepareStatement(dropTable);
        stmtDrop.execute();
    }




}

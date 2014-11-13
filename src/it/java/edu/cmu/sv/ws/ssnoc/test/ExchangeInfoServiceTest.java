package edu.cmu.sv.ws.ssnoc.test;

import edu.cmu.sv.ws.ssnoc.rest.ExchangeInfoService;
import edu.cmu.sv.ws.ssnoc.rest.ExchangeMessageService;
import org.junit.Test;

import static org.junit.Assert.*;


import edu.cmu.sv.ws.ssnoc.data.SQL;
import edu.cmu.sv.ws.ssnoc.data.util.DBUtils;
import edu.cmu.sv.ws.ssnoc.dto.ExchangeInfo;
import javax.ws.rs.core.Response;

import org.junit.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;


public class ExchangeInfoServiceTest {
    @BeforeClass
    public static void setUpTestData() throws Exception{

        DBUtils.setPerformaceRunning();
        Connection conn= DBUtils.getConnection();

        PreparedStatement stmtInsertUser = conn.prepareStatement(SQL.INSERT_USER);
        stmtInsertUser.setString(1, "A");
        stmtInsertUser.setString(2, null);
        stmtInsertUser.setString(3, null);
        stmtInsertUser.setString(4, null);
        stmtInsertUser.setString(5, null);
        stmtInsertUser.setString(6, null);
        stmtInsertUser.execute();
        stmtInsertUser.setString(1, "B");
        stmtInsertUser.setString(2, null);
        stmtInsertUser.setString(3, null);
        stmtInsertUser.setString(4, null);
        stmtInsertUser.setString(5, null);
        stmtInsertUser.setString(6, null);
        stmtInsertUser.execute();


    }



    @Test
    public void testloadWallMessages() {

        ExchangeMessageService EMS = new ExchangeMessageService();
        String username = "A";
        ExchangeInfo message = new ExchangeInfo();
        message.setAuthor("A");
        message.setContent("Wallfortest");
        Response res = EMS.addWallMessage(username, message);

        ExchangeInfoService EIS = new ExchangeInfoService();
        List<ExchangeInfo> list;
        list = EIS.loadWallMessages();

        assertEquals(list.get(0).getContent(),"Wallfortest");
    }

    @Test
    public void testloadVisibleWallMessages() {
        ExchangeMessageService EMS = new ExchangeMessageService();
        String username = "A";
        ExchangeInfo message = new ExchangeInfo();
        message.setAuthor("A");
        message.setContent("Wallfortest");
        Response res = EMS.addWallMessage(username, message);

        ExchangeInfoService EIS = new ExchangeInfoService();
        List<ExchangeInfo> list;
        list = EIS.loadWallMessages();

        assertEquals(list.get(0).getContent(),"Wallfortest");
    }

    @Test
    public void testloadChatMessages() {
        ExchangeMessageService EMS = new ExchangeMessageService();
        String username1 = "A";
        String username2 = "B";
        ExchangeInfo message = new ExchangeInfo();
        message.setAuthor("A");
        message.setContent("Wallfortest");
        Response res = EMS.addChatMessage(username1,username2, message);

        ExchangeInfoService EIS = new ExchangeInfoService();
        List<ExchangeInfo> list;
        list = EIS.loadChatMessages(username1,username2);

        assertEquals(list.get(0).getAuthor(),"A");
        assertEquals(list.get(0).getTarget(),"B");
        assertEquals(list.get(0).getContent(),"Wallfortest");
    }

    @Test
    public void testloadVisibleChatMessages() {
        ExchangeMessageService EMS = new ExchangeMessageService();
        String username1 = "A";
        String username2 = "B";
        ExchangeInfo message = new ExchangeInfo();
        message.setAuthor("A");
        message.setContent("Wallfortest");
        Response res = EMS.addChatMessage(username1,username2, message);

        ExchangeInfoService EIS = new ExchangeInfoService();
        List<ExchangeInfo> list;
        list = EIS.loadChatMessages(username1,username2);

        assertEquals(list.get(0).getAuthor(),"A");
        assertEquals(list.get(0).getTarget(),"B");
        assertEquals(list.get(0).getContent(),"Wallfortest");
    }

    @Test
    public void testloadAllAnnouncements() {
        ExchangeMessageService EMS = new ExchangeMessageService();
        String username = "A";
        ExchangeInfo message = new ExchangeInfo();
        message.setAuthor("A");
        message.setContent("Wallfortest");
        Response res = EMS.addAnnouncement(username, message);

        ExchangeInfoService EIS = new ExchangeInfoService();
        List<ExchangeInfo> list;
        list = EIS.loadAllAnnouncements();

        assertEquals(list.get(0).getContent(),"Wallfortest");
    }

    @Test
    public void testloadVisibleAnnouncements() {
        ExchangeMessageService EMS = new ExchangeMessageService();
        String username = "A";
        ExchangeInfo message = new ExchangeInfo();
        message.setAuthor("A");
        message.setContent("Wallfortest");
        Response res = EMS.addAnnouncement(username, message);

        ExchangeInfoService EIS = new ExchangeInfoService();
        List<ExchangeInfo> list;
        list = EIS.loadAllAnnouncements();

        assertEquals(list.get(0).getContent(),"Wallfortest");
    }




    @AfterClass
    public static void clearTestData() throws Exception{

        /*Connection conn= getConnection();
        String dropTable = "DROP table SSN_USERS; DROP table SSN_MESSAGE";
        PreparedStatement stmtDrop = conn.prepareStatement(dropTable);
        stmtDrop.execute();
        PreparedStatement stmtCreateUsers = conn.prepareStatement(SQL.CREATE_USERS);
        stmtCreateUsers.execute();
        PreparedStatement stmtCreateChat = conn.prepareStatement(SQL.CREATE_CHAT);
        stmtCreateChat.execute();*/
        DBUtils.stopPerformanceRunning();
    }

}
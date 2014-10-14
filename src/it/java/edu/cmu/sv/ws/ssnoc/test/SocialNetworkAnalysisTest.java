package edu.cmu.sv.ws.ssnoc.test;

import static org.junit.Assert.*;

import edu.cmu.sv.ws.ssnoc.rest.SocialNetworkAnalysis;
import edu.cmu.sv.ws.ssnoc.dto.ExchangeInfo;
import edu.cmu.sv.ws.ssnoc.dto.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by YHWH on 10/10/14.
 */
public class SocialNetworkAnalysisTest {
    ExchangeInfo message1, message2, message3, message4;
    User userA, userB, userC,userD, userE;

    @Before
    public void setUp() throws Exception{
        message1 = new ExchangeInfo();
            message1.setAuthor("A");
            message1.setTarget("C");
            message1.setPostedAt("20141001");
        message2 = new ExchangeInfo();
            message2.setAuthor("B");
            message2.setTarget("C");
            message2.setPostedAt("20141005");
        message3 = new ExchangeInfo();
            message3.setAuthor("C");
            message3.setTarget("D");
            message3.setPostedAt("20141010");
        message4 = new ExchangeInfo();
            message4.setAuthor("B");
            message4.setTarget("A");
            message4.setPostedAt("20141015");
        userA = new User();
            userA.setUserName("A");
        userB = new User();
            userB.setUserName("B");
        userC = new User();
            userC.setUserName("C");
        userD = new User();
            userD.setUserName("D");
        userE = new User();
            userE.setUserName("E");
    }

    @Test
    public void test() {
        SocialNetworkAnalysis analysisTest = new SocialNetworkAnalysis();
        List<User> users = new ArrayList<User>();
        users.add(userA);
        users.add(userB);
        users.add(userC);
        users.add(userD);
        users.add(userE);

        List<ExchangeInfo> messages = new ArrayList<ExchangeInfo>();
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);

        String startTime = "20141001";
        String endTime = "20141005";

        //List<List<User>> result = analysisTest.loadMessagesAndUsers(users, messages);
        //Set<List<User>> analysisResult = analysisTest.analyzeSocialNetwork(startTime, endTime);

        //assertTrue(analysisTest.loadMessagesAndUsers(users, messages).contains(userB));

    }

    @Test
    public void loadAllMessagesOfTimePeriodTest(){}




}

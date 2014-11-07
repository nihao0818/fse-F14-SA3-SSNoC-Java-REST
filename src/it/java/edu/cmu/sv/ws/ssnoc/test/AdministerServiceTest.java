package edu.cmu.sv.ws.ssnoc.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.sv.ws.ssnoc.rest.UserService;
import edu.cmu.sv.ws.ssnoc.dto.User;

import static org.junit.Assert.assertTrue;



/**
 * Created by Tangent on 10/24/14.
 */
public class AdministerServiceTest {
    User testUpdatedOne = new User();

    @Before
    public void setUpUserData(){

        //testUpdatedOne.setUserid(1);
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

   /*@After
    public void clearTestData(){}*/
}

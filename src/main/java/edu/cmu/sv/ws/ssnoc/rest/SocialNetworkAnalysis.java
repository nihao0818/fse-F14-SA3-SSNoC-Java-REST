package edu.cmu.sv.ws.ssnoc.rest;

import javax.crypto.SecretKey;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.*;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import edu.cmu.sv.ws.ssnoc.data.po.ExchangeInfoPO;
import edu.cmu.sv.ws.ssnoc.dto.ExchangeInfo;
import org.h2.util.StringUtils;

import edu.cmu.sv.ws.ssnoc.common.exceptions.ServiceException;
import edu.cmu.sv.ws.ssnoc.common.exceptions.UnauthorizedUserException;
import edu.cmu.sv.ws.ssnoc.common.exceptions.ValidationException;
import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.common.utils.ConverterUtils;
import edu.cmu.sv.ws.ssnoc.common.utils.SSNCipher;
import edu.cmu.sv.ws.ssnoc.data.dao.MessageDAOImpl;
import edu.cmu.sv.ws.ssnoc.data.dao.DAOFactory;
import edu.cmu.sv.ws.ssnoc.data.dao.IUserDAO;
import edu.cmu.sv.ws.ssnoc.data.po.UserPO;
import edu.cmu.sv.ws.ssnoc.dto.User;

/**
 * created by Tangent on Oct.6 2014
 * 
 */

@Path("/analysis")
public class  SocialNetworkAnalysis extends BaseService{
	/**
	 * This method analyze the social network of specific time period.
	 * @return - cluster list 
	 */
	@GET
	/*@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })*/ //no need of this
	/*@Path("/{timePeriod}")*/
    //@XmlElementWrapper(name = "analysis") not always used, but I don't know when should use it.
	
	public String thisIsTest(){

		return "test";
	}




    public List<List<User>> loadMessagesAndUsers(List<User> users, List<ExchangeInfo> messages){
        Log.enter();
        List<User> cluster = new ArrayList<User>();
        List<List<User>> clusters = new ArrayList<List<User>>();

        try {
            for(int j = 1; j >=0; j--) {

                for (int i = 2; i >= 0; i--) {
                    cluster.add(users.get(i));
                }
                clusters.add(cluster);
            }
        }
        catch (Exception e){
            handleException(e);
        }
        finally {
            Log.exit();
        }
        return clusters;
    }

    public List<List<User>> analyzeSocialNetwork(String startTime, String endTime){
        Log.enter();


        List<List<User>> clusters = new ArrayList<List<User>>();

        List<User> allUsers = null;
        List<User> pair = null;
        List<List<User>> buddies = null;

        try {
            List<List<UserPO>> buddiesPOs = DAOFactory.getInstance().getUserDAO().loadChatBuddiesByTime(startTime,endTime);
            pair = new ArrayList<User>();
            buddies = new ArrayList<List<User>>();
            for (List<UserPO> pairPO : buddiesPOs ){
                pair.clear();
                for (UserPO userPO : pairPO){
                    User dto = ConverterUtils.convert(userPO);
                    pair.add(dto);
                }
                buddies.add(pair);
            }

            List<UserPO> userPOs = DAOFactory.getInstance().getUserDAO().loadUsers();
            allUsers = new ArrayList<User>();
            for (UserPO po : userPOs) {
                User dto = ConverterUtils.convert(po);
                allUsers.add(dto);
            }

            clusters.add(allUsers);
            List<List<User>> temp = new ArrayList<List<User>>();
            for(List<User> eachPair : buddies) {
                for(List<User> eachCluster : clusters){
                    if (eachCluster.containsAll(eachPair)) {
                        List<User> cluster1= new ArrayList<User>(eachCluster);
                        List<User> cluster2= new ArrayList<User>(eachCluster);
                        cluster1.remove(eachPair.get(0));
                        cluster2.remove(eachPair.get(1));
                        temp.add(cluster1);
                        temp.add(cluster2);
                        clusters.remove(eachCluster);
                    }
                }
                clusters.addAll(temp);
                temp.clear();
            }

        }
        catch (Exception e){
            handleException(e);
        }
        finally {
            Log.exit(clusters);
        }

        return clusters;
    }
}
	
package edu.cmu.sv.ws.ssnoc.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;


import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.common.utils.ConverterUtils;
import edu.cmu.sv.ws.ssnoc.data.dao.DAOFactory;
import edu.cmu.sv.ws.ssnoc.data.po.UserPO;
import edu.cmu.sv.ws.ssnoc.dto.User;

/**
 * created by Tangent on Oct.6 2014
 * 
 */

@Path("/usergroups")
public class  SocialNetworkAnalysis extends BaseService{
	/**
	 * This method analyze the social network of specific time period.
	 * @return - clusters list
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON }) //no need of this
    @Path("/unconnected/{startTime}/{endTime}")
    public Response analyzeSocialNetwork(@PathParam("startTime") String startTime, @PathParam("endTime") String endTime){
        Log.enter();

        List<List<User>> clusters = new ArrayList<List<User>>();
        List<User> allUsers = loadAllUsers();
        List<List<User>> buddies = loadChatBuddies(startTime, endTime);

        try {
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

        return ok(new Gson().toJson(clusters));
    }

    public List<List<User>>loadChatBuddies(String startTime, String endTime) {
        List<User> pair = null;
        List<List<User>> buddies = null;

        try{
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
        }
        catch (Exception e) {
            handleException(e);
        }

        return buddies;
    }
    public List<User>loadAllUsers() {
        List<User> allUsers = null;

        try{
            List<UserPO> userPOs = DAOFactory.getInstance().getUserDAO().loadUsers();
            allUsers = new ArrayList<User>();
            for (UserPO po : userPOs) {
                User dto = ConverterUtils.convert(po);
                allUsers.add(dto);
            }
        }
        catch (Exception e) {
            handleException(e);
        }
        return allUsers;
    }
}
	
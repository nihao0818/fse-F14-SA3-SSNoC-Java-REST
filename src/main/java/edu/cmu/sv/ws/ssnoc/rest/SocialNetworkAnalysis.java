package edu.cmu.sv.ws.ssnoc.rest;

import javax.crypto.SecretKey;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

import org.h2.util.StringUtils;

import edu.cmu.sv.ws.ssnoc.common.exceptions.ServiceException;
import edu.cmu.sv.ws.ssnoc.common.exceptions.UnauthorizedUserException;
import edu.cmu.sv.ws.ssnoc.common.exceptions.ValidationException;
import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.common.utils.ConverterUtils;
import edu.cmu.sv.ws.ssnoc.common.utils.SSNCipher;
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

    public List<User> loadMessage(){
        Log.enter();
        List<User> cluster = new ArrayList<User>();

        try {}
        catch (Exception e){
            handleException(e);
        }
        finally {
            Log.exit();
        }
        return cluster;
    }
}
	
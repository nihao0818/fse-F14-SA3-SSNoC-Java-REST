package edu.cmu.sv.ws.ssnoc.rest;

import com.google.gson.Gson;
import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.data.dao.DAOFactory;
import edu.cmu.sv.ws.ssnoc.data.dao.IPerformanceDAO;
import edu.cmu.sv.ws.ssnoc.dto.User;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.StringTokenizer;

/**
 * Created by Vignan on 10/14/2014.
 */

@Path("/performance")
public class MeasurePerformanceService extends BaseService {

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/setup")
    public void startPerformanceMeasure(String testTime){
        IPerformanceDAO pdao = DAOFactory.getInstance().getPerformanceDAO();
        pdao.createTestTable();
        Log.info(testTime);
        Gson gson = new Gson();
        StringTokenizer st = new StringTokenizer(testTime, "\"");
        System.out.println(st.toString());
        /*String testDurationString = gson.fromJson(testTime);
        Log.info(testDurationString);
        long testDuration = Long.parseLong(testDurationString);

        /*long testDurationSeconds=testDuration*1000;
        long startTime = System.currentTimeMillis();
        long endTime = startTime+testDurationSeconds;
            while(System.currentTimeMillis()<endTime){

            }*/
    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/teardown")
    public void stopPerformanceMeasure(){

        DAOFactory.getInstance().getPerformanceDAO().deleteTestTable();

    }

}

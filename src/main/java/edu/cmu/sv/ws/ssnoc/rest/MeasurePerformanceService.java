package edu.cmu.sv.ws.ssnoc.rest;

import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.common.utils.ConverterUtils;
import edu.cmu.sv.ws.ssnoc.data.dao.DAOFactory;
import edu.cmu.sv.ws.ssnoc.data.dao.IMessageDAO;
import edu.cmu.sv.ws.ssnoc.data.dao.IPerformanceDAO;
import edu.cmu.sv.ws.ssnoc.data.po.PerformancePO;
import edu.cmu.sv.ws.ssnoc.data.util.DBUtils;
import edu.cmu.sv.ws.ssnoc.dto.Performance;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.sql.SQLException;

/**
 * Created by Vignan on 10/14/2014.
 */

@Path("/performance")
public class MeasurePerformanceService extends BaseService {
    private int duration;

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/setup")
    public Response startPerformanceMeasure(int durationInSec ){
        duration = durationInSec;
        IMessageDAO mdao = DAOFactory.getInstance().getMessageDAO();
        IPerformanceDAO pdao = DAOFactory.getInstance().getPerformanceDA0();
        pdao.resetPerformanceStats();
        try {

            mdao.resetRequestsCount();
            DBUtils.setPerformaceRunning();

        } catch (SQLException e) {
            Log.error("Oops :( We ran into an error when trying to intialize "
                    + "Perf Test database. Please check the trace for more details.", e);
        }

        return ok("Database Created");

    }

    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/teardown")
    public void stopPerformanceMeasure(){
        IMessageDAO mdao = DAOFactory.getInstance().getMessageDAO();
        double postWallRequests = mdao.getPostWallRequestCount()/duration;
        double getWallRequests = mdao.getGetWallRequestsCount()/duration;
        PerformancePO po = new PerformancePO();
        po.setPostsPerSecond(postWallRequests);
        po.setGetPerSecond(getWallRequests);

        try {
            DBUtils.stopPerformanceRunning();
        }
        catch (SQLException e){
            Log.error("Oops :( We ran into an error when trying to shutdown and delete "
                    + "Perf Test database. Please check the trace for more details.", e);
        }
        mdao.resetRequestsCount();

        IPerformanceDAO pdao = DAOFactory.getInstance().getPerformanceDA0();

        pdao.saveWallRequestsCount(po);

    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @XmlElementWrapper(name = "performanceStats")
    @Path("/")
    public Performance getPerformanceStats(){
        Performance performanceStats = null;
        try {
            IPerformanceDAO pdao = DAOFactory.getInstance().getPerformanceDA0();
            PerformancePO PPO = pdao.getWallRequestsCount();
            performanceStats = ConverterUtils.convert(PPO);

        } catch (Exception e){
            handleException(e);
        } finally {
            Log.exit(performanceStats);
        }

        return performanceStats;


    }

}

package edu.cmu.sv.ws.ssnoc.rest;

import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.common.utils.ConverterUtils;
import edu.cmu.sv.ws.ssnoc.data.dao.DAOFactory;
import edu.cmu.sv.ws.ssnoc.data.dao.IMemoryDAO;
import edu.cmu.sv.ws.ssnoc.data.po.MemoryPO;
import edu.cmu.sv.ws.ssnoc.dto.Memory;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Created by Vignan on 10/14/2014.
 */

@Path("/memory")
public class MeasureMemoryService extends BaseService{
    static Timer timeMonitor = new Timer();
    @POST
    //@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/start")
    public void startMemoryMonitor(){
            timeMonitor = null;
            timeMonitor = new Timer();
         TimerTask scanTask = new TimerTask() {
             IMemoryDAO mdao = DAOFactory.getInstance().getMemoryDAO();
             MemoryPO memDetails = new MemoryPO();


             DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

             @Override
             public void run() {
                 Runtime rt=Runtime.getRuntime();
                 long freeVMemory = rt.freeMemory()/1024;
                 long totalVMemory = rt.totalMemory()/1024;
                 long usedVMemory = totalVMemory-freeVMemory;

                 long freeSpace = 0;
                 long usedSpace = 0;

                 File[] roots = File.listRoots();
                 for (File root : roots){
                     freeSpace += root.getFreeSpace()/1024;
                     usedSpace += ((root.getTotalSpace()/1024)-(root.getFreeSpace()/1024));
                 }
                 memDetails.setUsedVolatile(usedVMemory);
                 memDetails.setRemainingVolatile(freeVMemory);
                 memDetails.setUsedPersistent(usedSpace);
                 memDetails.setRemainingPersistent(freeSpace);
                 Calendar calendar = Calendar.getInstance();
                 memDetails.setCreatedAt(df.format(calendar.getTime()));
                mdao.insertMemoryStats(memDetails);
             }
         };
        timeMonitor.schedule(scanTask,0,60000);
    }

    @POST
    //@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Path("/stop")
    public void stopMemoryMonitor(){
            timeMonitor.cancel();
        Log.trace("Memory Monitor Stopped");
    }

    @DELETE
    @Path("/")
    public void deleteMemoryCrumb(){
        IMemoryDAO mdao = DAOFactory.getInstance().getMemoryDAO();
        mdao.deleteMemoryCrumbData();
        Log.trace("Memory Crumb cleared");
    }

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @XmlElementWrapper(name = "memorystats")
    @Path("/")
    public List<Memory> loadMemoryStats(){
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String toDate = df.format(calendar.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        String fromDate = df.format(calendar.getTime());
        Log.trace(toDate,fromDate);
        List<Memory> memorystats = null;
        try{
            IMemoryDAO mdao = DAOFactory.getInstance().getMemoryDAO();
            List<MemoryPO> memPOs = mdao.getMemoryStats(toDate,fromDate);

            memorystats = new ArrayList<Memory>();
            for(MemoryPO memPO : memPOs){
                Memory memdto = ConverterUtils.convert(memPO);
                memorystats.add(memdto);
            }
        }catch (Exception e){
            handleException(e);
        }finally {
            Log.exit(memorystats);
        }
        return memorystats;
    }


    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @XmlElementWrapper(name = "memorystats")
    @Path("/interval/{timeWindowsInHours}")
    public List<Memory> loadMemoryStatusInInterval(@PathParam("timeWindowsInHours") int timeWindowsInHours){
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String toDate = df.format(calendar.getTime());
        calendar.add(Calendar.HOUR_OF_DAY, -timeWindowsInHours);
        String fromDate = df.format(calendar.getTime());
        Log.trace(toDate,fromDate);
        List<Memory> memorystats = null;
        try{
            IMemoryDAO mdao = DAOFactory.getInstance().getMemoryDAO();
            List<MemoryPO> memPOs = mdao.getMemoryStats(toDate,fromDate );

            memorystats = new ArrayList<Memory>();
            for(MemoryPO memPO : memPOs){
                Memory memdto = ConverterUtils.convert(memPO);
                memorystats.add(memdto);
            }
        }catch (Exception e){
            handleException(e);
        }finally {
            Log.exit(memorystats);
        }
        return memorystats;
    }

}

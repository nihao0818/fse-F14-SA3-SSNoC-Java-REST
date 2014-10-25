package edu.cmu.sv.ws.ssnoc.rest;

import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.common.utils.ConverterUtils;
import edu.cmu.sv.ws.ssnoc.data.dao.DAOFactory;
import edu.cmu.sv.ws.ssnoc.data.dao.IAnnouncementDAO;
import edu.cmu.sv.ws.ssnoc.data.po.AnnouncementPO;
import edu.cmu.sv.ws.ssnoc.data.po.UserPO;
import edu.cmu.sv.ws.ssnoc.dto.Announcement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignan on 10/22/2014.
 */

@Path("/announcement")
public class AnnouncementService extends BaseService {

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{userName}")
    public Response addAnnouncement(@PathParam("userName") String userName, Announcement announcement){
        Log.enter(userName, announcement);
        Announcement resp = new Announcement();
        try {
            UserPO po = loadExistingUser(userName);
            IAnnouncementDAO adao = DAOFactory.getInstance().getAnnouncementDAO();
            AnnouncementPO apo = ConverterUtils.convert(announcement);
            adao.saveAnnoucement(po,apo);
            resp = ConverterUtils.convert(apo);
        }catch (Exception e ){
            handleException(e);
        }finally {
            Log.exit();
        }
        return created(resp);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @XmlElementWrapper(name = "announcements")
    public List<Announcement> loadAnnouncements(){
        Log.enter();
        List<Announcement> announcements = null;
        try{
            List<AnnouncementPO> apo = DAOFactory.getInstance().getAnnouncementDAO().getAnnouncements();
            announcements = new ArrayList<>();
            for(AnnouncementPO each : apo){
                Announcement adto = ConverterUtils.convert(each);
                announcements.add(adto);
            }
        } catch (Exception e){
            handleException(e);
        } finally {
            Log.exit(announcements);
        }
        return announcements;
    }
}

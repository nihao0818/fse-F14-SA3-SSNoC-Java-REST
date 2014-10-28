package edu.cmu.sv.ws.ssnoc.rest;

import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.common.utils.ConverterUtils;
import edu.cmu.sv.ws.ssnoc.data.dao.DAOFactory;
import edu.cmu.sv.ws.ssnoc.data.po.ExchangeInfoPO;
import edu.cmu.sv.ws.ssnoc.data.po.UserPO;
import edu.cmu.sv.ws.ssnoc.dto.ExchangeInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vignan on 10/8/14.
 */
@Path("/messages")
public class ExchangeInfoService extends BaseService{

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @XmlElementWrapper(name = "wallMessages")
    @Path("/wall")
    public List<ExchangeInfo> loadWallMessages(){
        Log.enter();

        List<ExchangeInfo> wallMessages= null;
        try{
            List<ExchangeInfoPO> eInfoPO = DAOFactory.getInstance().getMessageDAO().loadWallMessages();

            wallMessages = new ArrayList<>();
            for(ExchangeInfoPO epo : eInfoPO){
                ExchangeInfo einfodto = ConverterUtils.convert(epo);
                wallMessages.add(einfodto);
            }
        } catch (Exception e){
            handleException(e);
        } finally {
            Log.exit(wallMessages);
        }
        return wallMessages;

    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @XmlElementWrapper(name = "chatMessages")
    @Path("/{userName1}/{userName2}")
    public List<ExchangeInfo> loadChatMessages(@PathParam("userName1") String userName1, @PathParam("userName2") String userName2){
        Log.enter();

        List<ExchangeInfo> chatMessages = null;
        UserPO po1 = loadExistingUser(userName1);
        UserPO po2 = loadExistingUser(userName2);


        try{
            List<ExchangeInfoPO> eInfoPO = DAOFactory.getInstance().getMessageDAO().loadChatMessages(po1, po2);

            chatMessages = new ArrayList<>();
            for (ExchangeInfoPO epo: eInfoPO){
                ExchangeInfo einfodto = ConverterUtils.convert(epo);
                chatMessages.add(einfodto);
            }

        } catch (Exception e){
            handleException(e);
        } finally {
            Log.exit(chatMessages);
        }

        return chatMessages;
    }

    @GET
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @XmlElementWrapper(name = "announcements")
    @Path("/announcement")
    public List<ExchangeInfo> loadAllAnnouncements(){
        Log.enter();

        List<ExchangeInfo> announcements= null;
        try{
            List<ExchangeInfoPO> eInfoPO = DAOFactory.getInstance().getMessageDAO().loadAllAnnouncements();

            announcements = new ArrayList<>();
            for(ExchangeInfoPO epo : eInfoPO){
                ExchangeInfo einfodto = ConverterUtils.convert(epo);
                announcements.add(einfodto);
            }
        } catch (Exception e){
            handleException(e);
        } finally {
            Log.exit(announcements);
        }
        return announcements;

    }





}

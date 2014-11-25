package edu.cmu.sv.ws.ssnoc.rest;
/**
 * Created by vignan on 10/8/14.
 */
import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.common.utils.ConverterUtils;
import edu.cmu.sv.ws.ssnoc.data.dao.DAOFactory;
import edu.cmu.sv.ws.ssnoc.data.dao.IMessageDAO;
import edu.cmu.sv.ws.ssnoc.data.dao.IUserDAO;
import edu.cmu.sv.ws.ssnoc.data.po.ExchangeInfoPO;
import edu.cmu.sv.ws.ssnoc.data.po.UserPO;
import edu.cmu.sv.ws.ssnoc.data.util.DBUtils;
import edu.cmu.sv.ws.ssnoc.dto.ExchangeInfo;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;


@Path("/message")
public class ExchangeMessageService extends BaseService {
    /**
     * Exchange Information on to Wall.
     *
     * @param userName
     *            - User Name
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/{userName}")
    public Response addWallMessage(@PathParam("userName") String userName, ExchangeInfo message ){
        Log.enter(userName, message);
        final int memoryCheckValue = 2048;//in kb
        String returnMessage = null;
      //  ExchangeInfo resp = new ExchangeInfo();
        try {
            UserPO po = null;

            IUserDAO udao = DAOFactory.getInstance().getUserDAO();
            po = udao.findByName(userName);

            IMessageDAO mdao = DAOFactory.getInstance().getMessageDAO();

            ExchangeInfoPO einfopo = ConverterUtils.convert(message);

            Log.trace("Inserting message on public wall from.....:"+userName);

            mdao.saveWallMessage(po, einfopo);

        }
        catch (Exception e){
            handleException(e);
        }
        finally {
            Log.exit();
        }
        Log.trace("Checking Memory Space after the message insertion");

        returnMessage="wall message saved";
        return ok(returnMessage);
    }

    /**
     *Exchange Information service to add the Private chat message to the database
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("/{sendingUserName}/{receivingUserName}")
    public Response addChatMessage(@PathParam("sendingUserName") String sendingUserName, @PathParam("receivingUserName") String receivingUserName,ExchangeInfo message ){
    Log.enter(sendingUserName,receivingUserName,message);
        ExchangeInfo resp = new ExchangeInfo();
        try {
            UserPO po1 = loadExistingUser(sendingUserName);
            UserPO po2 = loadExistingUser(receivingUserName);

            IMessageDAO mdao = DAOFactory.getInstance().getMessageDAO();

            ExchangeInfoPO einfopo = ConverterUtils.convert(message);

            Log.trace("Inserting chat message from.....:"+sendingUserName + "to.."+receivingUserName);

            mdao.saveChatMessage(po1,po2, einfopo);
            resp = ConverterUtils.convert(einfopo);
        }
        catch (Exception e){
            handleException(e);
        }
        finally {
            Log.exit();
        }

        return created(resp);
    }

    /**
     * Exchange information service to add announcement to database
     * @param userName
     * @param message
     * @return ok(announcement saved)
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/announcement/{userName}")
    public Response addAnnouncement(@PathParam("userName") String userName, ExchangeInfo message){
        Log.enter(userName, message);

        //ExchangeInfo resp = new ExchangeInfo();
        try {
            UserPO po = loadExistingUser(userName);
            IMessageDAO mdao = DAOFactory.getInstance().getMessageDAO();

            ExchangeInfoPO einfopo = ConverterUtils.convert(message);

            Log.trace("Inserting announcement from.....:"+userName);

            mdao.saveAnnouncement(po, einfopo);
            //resp = ConverterUtils.convert(einfopo);
        }catch (Exception e ){
            handleException(e);
        }finally {
            Log.exit();
        }
        return ok("Announcement saved");
    }

    /**
     * This method checks the validity of the user name and if it is valid, adds
     * it to the database
     *
     * @FormDataParam file
     *            - the file element in html
     * @return - An result message with the status of the request
     * added by Tangent on 11/24/2014.
     */
    @POST
    @Path("/{userName}/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addWallImg( @PathParam("userName") String userName,
                                @FormDataParam("thumbnail") InputStream uploadedInputStream,
                                @FormDataParam("thumbnail") FormDataContentDisposition fileDetail) {

        Log.enter(userName, "upload photo");
        final int memoryCheckValue = 2048;//in kb
        try {

            IUserDAO udao = DAOFactory.getInstance().getUserDAO();
            UserPO po = udao.findByName(userName);

            IMessageDAO mdao = DAOFactory.getInstance().getMessageDAO();

            ExchangeInfoPO einfopo = new ExchangeInfoPO();

            Log.trace("Inserting message on public wall from.....:"+userName);

            // Path format //10.217.14.97/Installables/uploaded/
            //System.out.println("path::"+path);
            String path = "\\tmp\\";
            String uploadedFileLocation = path + fileDetail.getFileName();

            // save it
            writeToFile(uploadedInputStream, uploadedFileLocation);
            einfopo.setAuthor(userName);
            einfopo.setImgPath(uploadedFileLocation);

            mdao.saveWallMessage(po, einfopo);

        }
        catch (Exception e){
            handleException(e);
        }
        finally {
            Log.exit();
        }

        //String output = "File uploaded to : " + uploadedFileLocation;

        Log.trace("Checking Memory Space after the message insertion");

        String returnMessage="wall message saved";
        return ok(returnMessage);
        //return created(fileDetail.getFileName());
        //return Response.status(200).entity("uploadFile is called, Uploaded file name : " + fileName).build();

    }

    // save uploaded file
    private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

        try {
            OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }


}

package edu.cmu.sv.ws.ssnoc.data.dao;

import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.data.SQL;
import edu.cmu.sv.ws.ssnoc.data.po.AnnouncementPO;
import edu.cmu.sv.ws.ssnoc.data.po.UserPO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vignan on 10/22/2014.
 */
public class AnnouncementDAOImpl extends BaseDAOImpl implements IAnnouncementDAO{

    @Override
    public void saveAnnoucement(UserPO user, AnnouncementPO ancmnt){
        if (ancmnt == null){
            Log.warn("Inside save Announcement method with announcementPO == NULL");
            return;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date dateobj = new Date();
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(SQL.INSERT_ANNOUNCEMENT)){
            stmt.setString(1,user.getUserName());
            stmt.setString(2,df.format(dateobj));
            stmt.setString(3,ancmnt.getLocation());
            stmt.setString(4,ancmnt.getTitle());
            stmt.setString(5, ancmnt.getContent());

            int rowCount = stmt.executeUpdate();
            Log.trace("Statement executed, and " + rowCount + " rows inserted.");
        } catch (SQLException e){
            handleException(e);
        } finally {
            Log.exit();
        }
    }

    @Override
    public List<AnnouncementPO> getAnnouncements(){
        Log.enter();
        String query = SQL.GET_ANNOUNCEMENTS;
        List<AnnouncementPO> announcements = new ArrayList<>();
        try(Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(query)){
            announcements = processAnnouncement(stmt);
        } catch (SQLException e){
            handleException(e);
            Log.exit(announcements);
        }
        return announcements;
    }

    private List<AnnouncementPO> processAnnouncement(PreparedStatement stmt) {
        Log.enter(stmt);
        if (stmt == null){
            Log.warn("Inside process Announcements method with NULL statement object");
            return null;
        }
        Log.debug("Executing stmt = " + stmt);
        List<AnnouncementPO> announcements =  new ArrayList<>();
        try(ResultSet rs = stmt.executeQuery()){
            while (rs.next()){
                AnnouncementPO apo = new AnnouncementPO();
                apo.setAuthor(rs.getString(1));
                apo.setPostedAt(rs.getString(2));
                apo.setTitle(rs.getString(3));
                apo.setContent(rs.getString(4));
                announcements.add(apo);
            }
        } catch (SQLException e){
            handleException(e);
        } finally {
            Log.exit(announcements);
        }
        return announcements;
    }
}

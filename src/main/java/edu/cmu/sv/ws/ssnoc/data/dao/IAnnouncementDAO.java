package edu.cmu.sv.ws.ssnoc.data.dao;

import edu.cmu.sv.ws.ssnoc.data.po.AnnouncementPO;
import edu.cmu.sv.ws.ssnoc.data.po.UserPO;

import java.util.List;


/**
 * Created by Vignan on 10/22/2014.
 */
public interface IAnnouncementDAO {

    List<AnnouncementPO> getAnnouncements();

    void saveAnnoucement(UserPO po, AnnouncementPO apo);
}

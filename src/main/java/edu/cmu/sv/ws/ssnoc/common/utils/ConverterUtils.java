package edu.cmu.sv.ws.ssnoc.common.utils;

import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.data.dao.IAnnouncementDAO;
import edu.cmu.sv.ws.ssnoc.data.po.*;
import edu.cmu.sv.ws.ssnoc.dto.*;
import edu.cmu.sv.ws.ssnoc.dto.Announcement;

/**
 * This is a utility class used to convert PO (Persistent Objects) and View
 * Objects into DTO (Data Transfer Objects) objects, and vice versa. <br/>
 * Rather than having the conversion code in all classes in the rest package,
 * they are maintained here for code re-usability and modularity.
 * 
 */
public class ConverterUtils {
	/**
	 * Convert UserPO to User DTO object.
	 * 
	 * @param po
	 *            - User PO object
	 * 
	 * @return - User DTO Object
	 */
	public static final User convert(UserPO po) {
		if (po == null) {
			return null;
		}

		User dto = new User();
        dto.setUserid(po.getUserId());
		dto.setUserName(po.getUserName());
        dto.setStatusCode(po.getStatusCode());
        dto.setStatusDate(po.getStatusDate());

		return dto;
	}

	/**
	 * Convert User DTO to UserPO object
	 * 
	 * @param dto
	 *            - User DTO object
	 * 
	 * @return - UserPO object
	 */
	public static final UserPO convert(User dto) {
		if (dto == null) {
			return null;
		}

		UserPO po = new UserPO();
		po.setUserName(dto.getUserName());
		po.setPassword(dto.getPassword());

		return po;
	}

    /** Convert StatusPO to Status DTO object.
    *
    * @param spo
    *            - User PO object
    *
    * @return - User DTO Object
    */
    public static final Status convert(StatusPO spo) {
        if (spo == null) {
            return null;
        }

        Status sdto = new Status();
        sdto.setCrumbID(spo.getCrumbID());
        sdto.setUserName(spo.getUserName());
        sdto.setStatusCode(spo.getStatusCode());
        sdto.setCreatedDate(spo.getCreatedDate());

        return sdto;
    }

    /**
     * Convert Status DTO to StatusPO object
     *
     * @param sdto
     *            - User DTO object
     *
     * @return - StatusPO object
     */
    public static final StatusPO convert(Status sdto){
        if (sdto == null){
            return null;
        }

        StatusPO spo = new StatusPO();
        spo.setUserName(sdto.getUserName());
        spo.setStatusCode(sdto.getStatusCode());
        spo.setCreatedDate(sdto.getCreatedDate());

        return spo;
    }

    public static final ExchangeInfo convert(ExchangeInfoPO einfopo){
        if (einfopo == null){
            return null;
        }

        ExchangeInfo einfodto = new ExchangeInfo();
        einfodto.setAuthor(einfopo.getAuthor());
        einfodto.setTarget(einfopo.getTarget());
        einfodto.setPostedAt(einfopo.getPostedAt());
        einfodto.setContent(einfopo.getContent());

        return einfodto;
    }

    public static final ExchangeInfoPO convert(ExchangeInfo einfodto){
        if (einfodto == null){
            return null;
        }

        ExchangeInfoPO einfopo = new ExchangeInfoPO();
      //  einfopo.setAuthor(einfodto.getAuthor());
      //  einfopo.setTarget(einfodto.getTarget());
        einfopo.setPostedAt(einfodto.getPostedAt());
        einfopo.setContent(einfodto.getContent());

        return einfopo;
    }

    public static final Memory convert(MemoryPO memdto){
            if(memdto == null){
                return null;
            }
        Memory memstats = new Memory();
        memstats.setUsedPersistent(memdto.getUsedPersistent());
        memstats.setRemainingPersistent(memdto.getRemainingPersistent());
        memstats.setUsedVolatile(memdto.getUsedVolatile());
        memstats.setRemainingVolatile(memdto.getRemainingVolatile());
        memstats.setCreatedAt(memdto.getCreatedAt());

        return memstats;
    }

    public static final AnnouncementPO convert(Announcement ancmnt) {
        if (ancmnt==null){
            return null;
        }
        AnnouncementPO apo = new AnnouncementPO();
        apo.setLocation(ancmnt.getLocation());
        apo.setTitle(ancmnt.getTitle());
        apo.setContent(ancmnt.getContent());

        return apo;
    }

    public static final Announcement convert(AnnouncementPO apo) {
        if (apo == null){
            return null;
        }
        Announcement ancmnt = new Announcement();
        ancmnt.setAuthor(apo.getAuthor());
        ancmnt.setPostedAt(apo.getPostedAt());
        ancmnt.setLocation(apo.getLocation());
        ancmnt.setTitle(apo.getTitle());
        ancmnt.setContent(apo.getContent());

        return ancmnt;
    }
}

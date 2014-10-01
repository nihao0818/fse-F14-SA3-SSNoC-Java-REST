package edu.cmu.sv.ws.ssnoc.common.utils;

import edu.cmu.sv.ws.ssnoc.data.po.StatusPO;
import edu.cmu.sv.ws.ssnoc.data.po.UserPO;
import edu.cmu.sv.ws.ssnoc.dto.Status;
import edu.cmu.sv.ws.ssnoc.dto.User;

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
		dto.setUserName(po.getUserName());
        dto.setStatusCode(po.getStatusCode());
       // dto.setCreatedAt(po.getCreatedAt());

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
        sdto.setUserName(spo.getUserName());
        sdto.setStatusCode(spo.getStatusCode());
        sdto.setCreatedAt(spo.getCreatedAt());

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
        spo.setCreatedAt(sdto.getCreatedAt());

        return spo;
    }
}

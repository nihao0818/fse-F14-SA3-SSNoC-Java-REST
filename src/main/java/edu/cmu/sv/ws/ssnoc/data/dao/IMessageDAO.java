package edu.cmu.sv.ws.ssnoc.data.dao;

/**
 * Created by vignan on 10/8/14.
<<<<<<< HEAD
 * Added loadChatBuddiesByTime by YHWH on 10/12/14;
=======
>>>>>>> 60c170a9c4f9385970810eb2de335d7e11211afb
 */

import edu.cmu.sv.ws.ssnoc.data.po.ExchangeInfoPO;
import edu.cmu.sv.ws.ssnoc.data.po.UserPO;

import java.util.List;

/**
 * Interface specifying the contract that all implementations will implement to
 * provide persistence of User Chat information in the system.
 *
 */
public interface IMessageDAO {

    /**
     * This method will save the information of the user into the database.
     *
     * @param userPO,einfoPO
     *            - User information to be saved.
     */
    void saveWallMessage(UserPO userPO, ExchangeInfoPO einfoPO);

    void saveChatMessage(UserPO po1, UserPO po2, ExchangeInfoPO einfopo);

    List<ExchangeInfoPO> loadWallMessages();

    List<ExchangeInfoPO> loadChatMessages(String userName1, String userName2);

    List<ExchangeInfoPO> loadChatBuddies(String userName);
<<<<<<< HEAD

    List<ExchangeInfoPO> loadChatBuddiesByTime(String startTime, String endTime);
=======
>>>>>>> 60c170a9c4f9385970810eb2de335d7e11211afb
}

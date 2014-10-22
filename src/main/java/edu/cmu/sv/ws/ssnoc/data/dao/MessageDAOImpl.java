package edu.cmu.sv.ws.ssnoc.data.dao;

import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.data.SQL;
import edu.cmu.sv.ws.ssnoc.data.po.ExchangeInfoPO;
import edu.cmu.sv.ws.ssnoc.data.po.UserPO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vignan on 10/8/14.
 *
 */
public class MessageDAOImpl extends BaseDAOImpl implements IMessageDAO{
    static long postWallRequests = 0;
    static long getWallRequests = 0;



    @Override
    public void resetRequestsCount(){
        postWallRequests = 0;
        getWallRequests = 0;
    }


    @Override
    public List<ExchangeInfoPO> loadWallMessages(){
        Log.enter();

        String query = SQL.FIND_ALL_WALL_MESSAGES;

        List<ExchangeInfoPO> wallMessages = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);) {
            wallMessages = processWallMessages(stmt);
        } catch (SQLException e) {
            handleException(e);
            Log.exit(wallMessages);
        }
        getWallRequests+=1;
        return wallMessages;
    }

    private List<ExchangeInfoPO> processWallMessages(PreparedStatement stmt) {
        Log.enter(stmt);

        if (stmt == null) {
            Log.warn("Inside process wall messages method with NULL statement object.");
            return null;
        }

        Log.debug("Executing stmt = " + stmt);
        List<ExchangeInfoPO> wallMessages = new ArrayList<ExchangeInfoPO>();
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ExchangeInfoPO epo = new ExchangeInfoPO();
                epo.setAuthor(rs.getString(1));
                epo.setTarget(rs.getString(2));
                epo.setContent(rs.getString(3));
                epo.setPostedAt(rs.getString(4));

                wallMessages.add(epo);
            }
        } catch (SQLException e) {
            handleException(e);
        } finally {
            Log.exit(wallMessages);
        }
        return wallMessages;
    }


    @Override
    public void saveWallMessage(UserPO userPO, ExchangeInfoPO einfoPO){
        Log.enter(einfoPO);
        if (einfoPO == null) {
            Log.warn("Inside save method for wall message with einfoPO == NULL");
            return;
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL.INSERT_CHAT)) {
            stmt.setString(1, userPO.getUserName());
            stmt.setString(2, "Wall");
            stmt.setString(3,null);
            stmt.setString(4,einfoPO.getPostedAt());
            stmt.setString(5, einfoPO.getContent());

            int rowCount = stmt.executeUpdate();
            Log.trace("Statement executed, and " + rowCount + " rows inserted.");
        } catch (SQLException e) {
            handleException(e);
        } finally {
            Log.exit();
        }
        postWallRequests+=1;
    }

    @Override
    public List<ExchangeInfoPO> loadChatMessages(String userName1, String userName2){
        Log.enter();

        List<ExchangeInfoPO> chatMessages = new ArrayList<>();
        try(Connection conn= getConnection();
            PreparedStatement stmt = conn
                .prepareStatement(SQL.FIND_CHAT_MESSAGES)) {
            stmt.setString(1, userName1.toUpperCase());
            stmt.setString(2, userName2.toUpperCase());
            stmt.setString(3, userName1.toUpperCase());
            stmt.setString(4, userName2.toUpperCase());
            chatMessages = processChatMessages(stmt);
        } catch (SQLException e) {
            handleException(e);
            Log.exit(chatMessages);
        }
        return chatMessages;
    }

    private List<ExchangeInfoPO> processChatMessages(PreparedStatement stmt) {
        Log.enter(stmt);

        if (stmt == null) {
            Log.warn("Inside process chat messages method with NULL statement object.");
            return null;
        }

        Log.debug("Executing stmt = " + stmt);
        List<ExchangeInfoPO> chatMessages = new ArrayList<ExchangeInfoPO>();

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ExchangeInfoPO eipo = new ExchangeInfoPO();
                eipo.setAuthor(rs.getString(1));
                eipo.setTarget(rs.getString(2));
                eipo.setContent(rs.getString(3));
                eipo.setPostedAt(rs.getString(4));

                chatMessages.add(eipo);
            }
        } catch (SQLException e) {
            handleException(e);
        } finally {
            Log.exit(chatMessages);
        }
    return chatMessages;
    }

    @Override
    public void saveChatMessage(UserPO userPO1,UserPO userPO2, ExchangeInfoPO einfoPO){
        Log.enter(einfoPO);
        if (einfoPO == null) {
            Log.warn("Inside save method for wall message with einfoPO == NULL");
            return;
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL.INSERT_CHAT)) {
            stmt.setString(1, userPO1.getUserName());
            stmt.setString(2, "Chat");
            stmt.setString(3,userPO2.getUserName());
            stmt.setString(4,einfoPO.getPostedAt());
            stmt.setString(5, einfoPO.getContent());

            int rowCount = stmt.executeUpdate();
            Log.trace("Statement executed, and " + rowCount + " rows inserted.");
        } catch (SQLException e) {
            handleException(e);
        } finally {
            Log.exit();
        }
    }

    @Override
    public List<ExchangeInfoPO> loadChatBuddies(String userName){
        Log.enter();

        List<ExchangeInfoPO> chatBuddies = new ArrayList<>();
        try(Connection conn= getConnection();
            PreparedStatement stmt = conn
                    .prepareStatement(SQL.FIND_CHAT_BUDDIES)) {
            stmt.setString(1, userName.toUpperCase());
            chatBuddies = processChatBuddies(stmt);
        } catch (SQLException e) {
            handleException(e);
            Log.exit(chatBuddies);
        }
        return chatBuddies;
    }

    private List<ExchangeInfoPO> processChatBuddies(PreparedStatement stmt) {
        Log.enter(stmt);

        if (stmt == null) {
            Log.warn("Inside processStatuses method with NULL statement object.");
            return null;
        }

        Log.debug("Executing stmt = " + stmt);
        List<ExchangeInfoPO> chatBuddies = new ArrayList<ExchangeInfoPO>();

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ExchangeInfoPO eipo = new ExchangeInfoPO();
                eipo.setTarget(rs.getString(1));
                chatBuddies.add(eipo);
            }
        } catch (SQLException e) {
            handleException(e);
        } finally {
            Log.exit(chatBuddies);
        }
        return chatBuddies;
    }

    @Override
    public long getGetWallRequestsCount(){
        return getWallRequests;
    }

    @Override
    public long getPostWallRequestCount(){
        return postWallRequests;
    }


}

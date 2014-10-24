package edu.cmu.sv.ws.ssnoc.data.dao;


import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.data.SQL;
import edu.cmu.sv.ws.ssnoc.data.po.PerformancePO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vignan on 10/20/2014.
 */
public class PerformanceDAOImpl extends BaseDAOImpl implements IPerformanceDAO{
    int timeduration;

    @Override
    public void saveWallRequestsCount(PerformancePO po){
        Log.enter(po);
        if (po==null){
            Log.warn("Inside save method for wall message with Performance count == NULL");
            return;
        }
        try(Connection conn =getConnection();
            PreparedStatement stmt =conn.prepareStatement(SQL.INSERT_PERFORMANCE_STATS)){
            stmt.setDouble(1,po.getPostsPerSecond());
            stmt.setDouble(2,po.getGetPerSecond());

            int rowCount = stmt.executeUpdate();
            Log.trace("Statement Executed and "+ rowCount+" rows inserted");
        }catch (SQLException e){
            handleException(e);
        }finally {
            Log.exit();
        }
    }

    @Override
    public PerformancePO getWallRequestsCount() {
        Log.enter();
        String query = SQL.GET_PERFORMANCE_STATS;

        PerformancePO po = null;

        List<PerformancePO> performanceStats = new ArrayList<>();

        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(query);){
            performanceStats = processPerformanceStats(stmt);

            if (performanceStats.size() == 0) {
                Log.debug("No performance stats exist");
            } else {
                po = performanceStats.get(0);
            }
        }catch (SQLException e){
            handleException(e);
            Log.exit(po);
        }

        return po;
    }

    private List<PerformancePO> processPerformanceStats(PreparedStatement stmt) {
        Log.enter(stmt);

        if (stmt == null) {
            Log.warn("Inside process wall messages method with NULL statement object.");
            return null;
        }

        Log.debug("Executing stmt = " + stmt);
        List<PerformancePO> performanceStats = new ArrayList<PerformancePO>();
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                PerformancePO epo = new PerformancePO();
                epo.setPostsPerSecond(rs.getInt(1));
                epo.setGetPerSecond(rs.getInt(2));

                performanceStats.add(epo);
            }
        } catch (SQLException e) {
            handleException(e);
        } finally {
            Log.exit(performanceStats);
        }
        return performanceStats;
    }

    @Override
    public void resetPerformanceStats(){

        try(Connection conn =getConnection();
            PreparedStatement stmt =conn.prepareStatement(SQL.RESET_PERFORMANCE_STATS)){
            stmt.execute();
            Log.trace("Performance Crumb Truncated");
        }catch (SQLException e){
            handleException(e);
        }finally {
            Log.exit();
        }

    }


}

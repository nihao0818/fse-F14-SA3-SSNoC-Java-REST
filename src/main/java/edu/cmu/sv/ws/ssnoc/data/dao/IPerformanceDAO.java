package edu.cmu.sv.ws.ssnoc.data.dao;

import edu.cmu.sv.ws.ssnoc.data.po.PerformancePO;

import java.util.List;

/**
 * Created by Vignan on 10/20/2014.
 */
public interface IPerformanceDAO {

    void saveWallRequestsCount(PerformancePO po);
    PerformancePO getWallRequestsCount();

    void resetPerformanceStats();
}

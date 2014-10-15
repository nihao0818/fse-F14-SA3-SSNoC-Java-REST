package edu.cmu.sv.ws.ssnoc.data.dao;


import edu.cmu.sv.ws.ssnoc.common.logging.Log;
import edu.cmu.sv.ws.ssnoc.data.SQL;
import edu.cmu.sv.ws.ssnoc.data.SQLTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Vignan on 10/14/2014.
 */
public class PerformanceDAOImpl extends BaseDAOImpl implements IPerformanceDAO{

    @Override
    public void createTestTable(){
        boolean status = false;

        String createTable = SQLTest.CREATE_CHAT_TEST;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();) {
            Log.debug("Executing query: " + stmt);
            status = stmt.execute(createTable);
            Log.debug("Query execution completed with status: "
                    + status);
            Log.info("Tables created successfully");
        } catch (SQLException e) {
            handleException(e);
            Log.exit(status);
        }
    }

    @Override
    public void deleteTestTable(){
        boolean status = false;

        String deleteTable = SQLTest.DELETE_TABLE_TEST;
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();) {
            Log.debug("Executing query: " + stmt);
            status = stmt.execute(deleteTable);
            Log.debug("Query execution completed with status: "
                    + status);
            Log.info("Tables deleted successfully");
        } catch (SQLException e) {
            handleException(e);
            Log.exit(status);
        }
    }
}

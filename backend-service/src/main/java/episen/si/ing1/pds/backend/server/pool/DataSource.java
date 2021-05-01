package episen.si.ing1.pds.backend.server.pool;

import episen.si.ing1.pds.backend.server.config.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;

public class DataSource {
    private static JDBCConnectionPool jdbcConnectionPool ;
    private static final Logger logger = LoggerFactory.getLogger(DataSource.class.getName());
    //private static final int nbConnection = 1;
    private static DataSource INSTANCE = new DataSource();

    /**
     * initializes the jdbcConnectionPool object
     */
    private DataSource(){
        jdbcConnectionPool = PoolSingleton.Instance.getInstance();
    }

    public Connection receiveConnection() {
        Connection c = null;
            if (jdbcConnectionPool.isEmpty()) {
                try {
                    logger.info("There is no more connection");
                } catch (Exception e) {
                    e.printStackTrace();

                }
            } else {
                c = jdbcConnectionPool.getConnection();
            }
            return c;
    }

    public boolean putConnection(Connection connection) { return jdbcConnectionPool.addConnection(connection); }

    public void closePool() { jdbcConnectionPool.closeConnection(); }

    public int getNbConnection(){
        return jdbcConnectionPool.getSizeArrayConnection();
    }

    public JDBCConnectionPool getJdbcConnectionPool (){
        return jdbcConnectionPool;
    }

    public static DataSource getInstance(){
        return INSTANCE;
    }
}

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
    private DatabaseConfig databaseConfig;
    /**
     * initializes the jdbcConnectionPool object
     */
    private DataSource(){
        try {
            databaseConfig = new DatabaseConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jdbcConnectionPool = PoolSingleton.Instance.getInstance(databaseConfig.getConfig().getMAX_CONNECTION());
        System.out.println("MAXCONNECTION "+databaseConfig.getConfig().getMAX_CONNECTION());
    }

    public Connection receiveConnection() {
        while (true) {
            synchronized (jdbcConnectionPool) {
                if (jdbcConnectionPool.isEmpty()) {
                    try {
                        jdbcConnectionPool.wait(2000);
                        logger.info("There is no more connection");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    return jdbcConnectionPool.getConnection();
                }
            }
        }
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

package episen.si.ing1.pds.backend.server.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

public class DataSource {
    private static JDBCConnectionPool jdbcConnectionPool ;
    private static final Logger logger = LoggerFactory.getLogger(DataSource.class.getName());
    /**
     * initializes the jdbcConnectionPool object
     * @param nbConnection
     */
    public DataSource(int nbConnection){
        jdbcConnectionPool = PoolSingleton.Instance.getInstance(nbConnection);
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
    public boolean putConnection(Connection connection) {
        return jdbcConnectionPool.addConnection(connection);
    }

    public void closePool() {
        jdbcConnectionPool.closeConnection();
    }

    public int getNbConnection(){
        return jdbcConnectionPool.getSizeArrayConnection();
    }

    public JDBCConnectionPool getJdbcConnectionPool (){
        return jdbcConnectionPool;
    }
}

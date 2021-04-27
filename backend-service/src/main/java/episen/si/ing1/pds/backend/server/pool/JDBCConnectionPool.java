package episen.si.ing1.pds.backend.server.pool;


import episen.si.ing1.pds.backend.server.config.DatabaseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;


public class JDBCConnectionPool {
    private ArrayList<Connection> physicalConnections;
    private String driverName;
    private String dataBaseUrl;
    private String user;
    private String password;
    private int max_Connection;
    private static boolean isInit = false;
    private static final Logger logger = LoggerFactory.getLogger(JDBCConnectionPool.class.getName());
    private DatabaseConfig databaseConfig;
    /**
     * This constructor uses PropertiesReader to retrieve database parameters
     */

    public JDBCConnectionPool() {
        try {
            databaseConfig = new DatabaseConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driverName = databaseConfig.getConfig().getDRIVER_NAME();
        dataBaseUrl = databaseConfig.getConfig().getDATABASE_URL();
        user = databaseConfig.getConfig().getUSER();
        password = databaseConfig.getConfig().getPASSWORD();
        max_Connection = databaseConfig.getConfig().getMAX_CONNECTION();
        physicalConnections = new ArrayList<>();
    }

    /**
     * this method initializes the list by adding Connection in the list
     */
    public void init() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < max_Connection; i++) {
            Connection connect = null;
            try {
                connect = DriverManager.getConnection(dataBaseUrl, user, password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            physicalConnections.add(connect);
        }
    }

    /**
     * if a client want a connection and there are no more, he will wait, and if after that there is still no connection, we create a connection and give it to the client.
     * Synchronisation is done at the level of the connection list.
     * @return a Connection for the Client
     */
    public Connection getConnection() {


                    return physicalConnections.remove(0);


    }

    /**
     * Close every connection of the list of connections
     */
    public void closeConnection() {
        for (Connection connect : physicalConnections) {
            try {
                connect.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    /**
     *
     * @param connection the client used
     * @return
     */
    public boolean addConnection(Connection connection) {
//        synchronized (physicalConnections){
//            physicalConnections.notifyAll();
//
//        }
        return physicalConnections.add(connection);
    }

    /**
     *
     * @return number of remaining connections
     */
    public int getSizeArrayConnection() {
        return physicalConnections.size();
    }

    /**
     *
     * @return true if there is no more connection in the pool
     */
    public boolean isEmpty(){
        return physicalConnections.size()==0;
    }

}

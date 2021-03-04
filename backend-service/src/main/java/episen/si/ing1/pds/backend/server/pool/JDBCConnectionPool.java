package episen.si.ing1.pds.backend.server.pool;


import episen.si.ing1.pds.backend.server.BackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger(JDBCConnectionPool.class.getName());

    /**
     * This constructor uses PropertiesReader to retrieve database parameters
     */
    public JDBCConnectionPool() {
        driverName = PropertiesReader.Instance.DRIVERNAME ;
        dataBaseUrl = PropertiesReader.Instance.DATABASEURL;
        user = PropertiesReader.Instance.USER;
        password = PropertiesReader.Instance.PASSWORD;
        physicalConnections = new ArrayList<>();
    }

    /**
     * this method initializes the list by adding Connection in the list
     * @param nbConnection is the maximum number of connections you can have in the list. This number is chosen at runtime with the maxConnection argument.
     */
    public void init(int nbConnection) {
        max_Connection = nbConnection;
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
     * if a client want a connection and there are no more, he must wait for another client to close his connection
     * Synchronisation is done at the level of the connection list.
     * @return a Connection for the Client
     */
    public Connection getConnection() {
        while (true) {
            synchronized (physicalConnections) {
                if (physicalConnections.size() == 0) {
                    try {
                        physicalConnections.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    return physicalConnections.remove(0);
                }
            }
        }
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
        if (physicalConnections.size() == max_Connection) {
            logger.info("Cant add connection");
            return false;
        }
        return physicalConnections.add(connection);
    }


    /**
     *
     * @return number of remaining connections
     */
    public int getSizeArrayConnection() {
        return physicalConnections.size();
    }


}

package episen.si.ing1.pds.backend.server.pool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCConnectionPool {
    private ArrayList<Connection> physicalConnections;
    private String driverName;
    private String dataBaseUrl;
    private String user;
    private String password;
    private int max_Connection;

    public JDBCConnectionPool() {
        driverName = PropertiesReader.Instance.DRIVERNAME;
        dataBaseUrl = PropertiesReader.Instance.DATABASEURL;
        user = PropertiesReader.Instance.USER;
        password = PropertiesReader.Instance.PASSWORD;
        physicalConnections = new ArrayList<>();
    }

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

    // if a client want a connction and there are no more, he must wait for another client to close his connection
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

    public void closeConnection() {
        for (Connection connect : physicalConnections) {
            try {
                connect.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    public boolean addConnection(Connection connection) {
        if (physicalConnections.size() == max_Connection) {
            System.out.println("Can't add connection");
            return false;
        }
        return physicalConnections.add(connection);
    }


    // return number of remaining connections
    public int getSizeArrayConnection() {
        return physicalConnections.size();
    }


}

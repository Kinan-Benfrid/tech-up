package episen.si.ing1.pds.backend.server.pool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCConnectionPool {
    private ArrayList<Connection> physicalConnections;
    private static String driverName;
    private static String dataBaseUrl;
    private static String user;
    private static String password;
    private int max_Connection = 3;

    private JDBCConnectionPool(int nbConnection) {
        driverName = PropertiesReader.Instance.DRIVERNAME;
        dataBaseUrl = PropertiesReader.Instance.DATABASEURL;
        user = PropertiesReader.Instance.USER;
        password = PropertiesReader.Instance.PASSWORD;
        physicalConnections = new ArrayList<>();
        max_Connection = nbConnection;
        init();
    }

    public void init() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(max_Connection + "maxconnection");
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

    // if a client
    public Connection getConnection() {
        System.out.println("df" + physicalConnections.size());
        while (true) {
            synchronized (physicalConnections) {
                if (physicalConnections.size() == 0) {
                    try {
                        wait();
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


    public static JDBCConnectionPool getInstance(int nbConnection) {
        return new JDBCConnectionPool(nbConnection);
    }

    public boolean addConnection(Connection connection) {
        if (physicalConnections.size() == max_Connection) {
            System.out.println("Can't add connection");
            return false;
        }
        synchronized (physicalConnections) {
            return physicalConnections.add(connection);
        }
    }


    // return number of remaining connections
    public int getSizeArrayConnection() {
        return physicalConnections.size();
    }


}

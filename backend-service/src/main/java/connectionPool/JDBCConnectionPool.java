package connectionPool;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionPoolDataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCConnectionPool {
    private ArrayList<Connection> phyicalConnections = new ArrayList<>();
    private static final int nbConnection = 100;

    public Connection getConnection(){
        Connection connect = phyicalConnections.get(phyicalConnections.size()-1);
        phyicalConnections.remove(phyicalConnections.get(phyicalConnections.size()-1));
        return connect;
    }

}
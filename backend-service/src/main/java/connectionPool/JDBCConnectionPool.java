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
    private static final int nbConnection = 20;

    public void init() throws ClassNotFoundException, SQLException {
        for (int i =0; i<nbConnection; i++){
            Connection connect = null;
            Properties properties = new Properties();
            try {
                properties.load(JDBCConnectionPool.class.getClassLoader().getResourceAsStream("Connection.properties")); //load connection.properties file to retreive DataBase parameters
            } catch (IOException e) {
                e.printStackTrace();
            }
            Class.forName(properties.getProperty("DRIVER_NAME"));
            connect= DriverManager.getConnection(properties.getProperty("DATABASE_URL"), properties.getProperty("user"),properties.getProperty("password"));
            phyicalConnections.add(connect);
        }
    }

    public Connection getConnection(){
        Connection connect = phyicalConnections.get(phyicalConnections.size()-1);
        phyicalConnections.remove(phyicalConnections.get(phyicalConnections.size()-1));
        return connect;
    }

    public void closeConnection(){
        for (Connection connect : phyicalConnections){
            try {
                connect.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public boolean addConnection(Connection connection){
        return phyicalConnections.add(connection);
    }

    public static void main(String[] args) {

    }


}

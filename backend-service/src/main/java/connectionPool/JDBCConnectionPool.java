package connectionPool;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCConnectionPool {
    private ArrayList<Connection> physicalConnections = new ArrayList<>();
    private int nbConnection;
    private static JDBCConnectionPool jdbcConnectionPool = new JDBCConnectionPool(10);

    private JDBCConnectionPool(int nbConnection) {
        this.nbConnection = nbConnection;
        try {
            init(nbConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void init(int nbConnection) throws SQLException, ClassNotFoundException {
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
            physicalConnections.add(connect);
        }
    }

    protected Connection getConnection(){
        Connection connect = physicalConnections.get(physicalConnections.size()-1);
        synchronized (connect){
            return connect;
        }
    }

    public void closeConnection(){
        for (Connection connect : physicalConnections){
            try {
                connect.close();
                connect.notifyAll();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static JDBCConnectionPool getInstance(){
        return  jdbcConnectionPool;
    }

    /*public boolean addConnection(Connection connection){
        return phyicalConnections.add(connection);
    }*/



    // return number of remaining connections
    public int getSizeArrayConnection(){
        return physicalConnections.size();
    }


}

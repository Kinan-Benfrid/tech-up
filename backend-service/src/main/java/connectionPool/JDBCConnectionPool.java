package connectionPool;




import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class JDBCConnectionPool {
    private ArrayList<Connection> physicalConnections = new ArrayList<>();
    private static JDBCConnectionPool jdbcConnectionPool = new JDBCConnectionPool() ;
    private static String driverName;
    private static String dataBaseUrl;
    private static String user;
    private static String password;
    private static int max_Connection;


    private JDBCConnectionPool() {
        Properties properties = new Properties();
        try {
            properties.load(JDBCConnectionPool.class.getClassLoader().getResourceAsStream("Connection.properties")); //load connection.properties file to retreive DataBase parameters
            driverName = properties.getProperty("DRIVER_NAME");
            dataBaseUrl = properties.getProperty("DATABASE_URL");
            user = properties.getProperty("USER");
            password = properties.getProperty("PASSWORD");
            max_Connection = Integer.valueOf(properties.getProperty("MAX_CONNECTION"));
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
        }
        for (int i =0; i<max_Connection; i++){
            Connection connect = null;
            try {
                connect = DriverManager.getConnection(dataBaseUrl, user,password);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            physicalConnections.add(connect);
        }
    }

    public Connection getConnection(){
        if (physicalConnections.size()==0){
            System.out.println("No more connections available");
        }
        Connection connect = physicalConnections.get(physicalConnections.size()-1);
        physicalConnections.remove(physicalConnections.get(physicalConnections.size()-1));
        return connect;
    }

    public void closeConnection(){
        for (Connection connect : physicalConnections){
            try {
                connect.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    public static JDBCConnectionPool getInstance(){
        return jdbcConnectionPool;
    }

    public boolean addConnection(Connection connection){
        if (physicalConnections.size()==max_Connection){
            System.out.println("Can't add connection");
            return false;
        }
        return physicalConnections.add(connection);
    }



    // return number of remaining connections
    public int getSizeArrayConnection(){
        return physicalConnections.size();
    }


}

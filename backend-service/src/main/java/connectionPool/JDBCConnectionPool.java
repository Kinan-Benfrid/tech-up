package connectionPool;
import java.util.*;
import java.sql .*;

public class JDBCConnectionPool extends Connection {
    private List<Connection> availableConnections =  new ArrayList<Connection>();
    private List<Connection>usedConnections = new ArrayList<Connection>();
    private final int MAX_CONNECTIONS = 15;

    private String URL;
    private String USERID;
    private String PASSWORD;


    /** Initialize all 5 Connections and put them in the Pool **/
    public void Collection(String Url, String UserId, String password)  throws SQLException {
        this.URL = Url;
        this.USERID = UserId;
        this.PASSWORD = password;

        for (int count = 0; count <MAX_CONNECTIONS; count++) {
            availableConnections.add(this.createConnection());
        }

    }



    /** Private function,
     used by the Pool to create new connection internally **/

    Connection createConnection() throws SQLException {
        return (Connection) DriverManager .getConnection(this.URL, this.USERID, this.PASSWORD);
    }




    /** Public function, used by us to get connection from Pool **/
    public Connection getConnection() {
        if (availableConnections.size() == 0) {
            System.out.println("All connections are Used !!");
            return null;
        } else {
            Connection con =
                    availableConnections.remove(
                            availableConnections.size() - 1);
            usedConnections.add(con);
            return con;
        }
    }



    /** Public function, to return connection back to the Pool **/
    public boolean releaseConnection(Connection con) {
        if (null != con) {
            usedConnections.remove(con);
            availableConnections.add(con);
            return true;
        }
        return false;
    }





    /** Utility function to check the number of Available Connections **/
    public int getFreeConnectionCount() {
        return availableConnections.size();
    }

    public void addConnection(Connection c) {
    }

    public static void main(String[] args) {

    }
}
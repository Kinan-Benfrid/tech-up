package connectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static JDBCConnectionPool jdbcConnectionPool;

    public DataSource(int nbConnection) throws SQLException, ClassNotFoundException {
        jdbcConnectionPool = JDBCConnectionPool.getInstance();
    }

    public static Connection receiveConnection() {
        return jdbcConnectionPool.getConnection();
    }

    /*public static boolean putConnection(Connection connection) {
        return jdbcConnectionPool.addConnection(connection);
    }*/

    public static void closePool() {
        jdbcConnectionPool.closeConnection();
    }

    public static int getNbConnection(){
        return jdbcConnectionPool.getSizeArrayConnection();
    }
}

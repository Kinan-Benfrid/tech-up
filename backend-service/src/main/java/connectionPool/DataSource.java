package connectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static JDBCConnectionPool jdbcConnectionPool = new JDBCConnectionPool();

    public DataSource() throws SQLException, ClassNotFoundException {
        jdbcConnectionPool.init();
    }

    public static Connection receiveConnection() {
        synchronized (jdbcConnectionPool) {
            return jdbcConnectionPool.getConnection();
        }
    }
    public static boolean putConnection(Connection connection) {
        synchronized (jdbcConnectionPool) {
            return jdbcConnectionPool.addConnection(connection);
        }
    }

    public static void closePool() {
        synchronized (jdbcConnectionPool) {
            jdbcConnectionPool.closeConnection();
        }
    }
}

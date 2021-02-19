package connectionPool;

import java.sql.SQLException;

public class DataSource {
    private static JDBCConnectionPool conn;

    public static Connection getConnection() throws SQLException {
        conn.createConnection();
        return conn.getConnection();
    }

    public static void addConnection(Connection c) {
        conn.addConnection(c);
    }

    public static void closeConnection() throws SQLException {
        conn.closeAll();
    }
}
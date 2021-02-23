package connectionPool;

import java.sql.SQLException;

public class DataSource {
    private static JDBCConnectionPool conn;

    public static java.sql.Connection getConnection() throws SQLException {
        conn.createConnection();
        return conn.getConnection();
    }

    public static void addConnection(Connection c) {
        conn.addConnection((java.sql.Connection) c);
    }

    public static void closeConnection() throws SQLException {
        conn.closeAll();
    }
}
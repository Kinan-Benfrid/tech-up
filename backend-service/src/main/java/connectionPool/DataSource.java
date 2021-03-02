package connectionPool;

import java.sql.Connection;

public class DataSource {
    private JDBCConnectionPool jdbcConnectionPool = JDBCConnectionPool.getInstance();

    public Connection receiveConnection() {
        return jdbcConnectionPool.getConnection();
    }

    public boolean putConnection(Connection connection) {
        return jdbcConnectionPool.addConnection(connection);
    }

    public void closePool() {
        jdbcConnectionPool.closeConnection();
    }

    public int getNbConnection(){
        return jdbcConnectionPool.getSizeArrayConnection();
    }

    public JDBCConnectionPool getJdbcConnectionPool (){
        return  jdbcConnectionPool;
    }
}

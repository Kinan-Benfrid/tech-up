package connectionPool;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
    private JDBCConnectionPool jdbcConnectionPool = JDBCConnectionPool.getInstance();

    public DataSource() throws SQLException, ClassNotFoundException {
        Properties properties = new Properties();
        try {
            properties.load(JDBCConnectionPool.class.getClassLoader().getResourceAsStream("Connection.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jdbcConnectionPool.init();
    }

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

package episen.si.ing1.pds.backend.server.pool;

import java.io.IOException;
import java.util.Properties;

/**
 * There is no need to read many times Connection.properties file.
 */
public enum PropertiesReader {
    Instance;
    String DRIVERNAME;
    String DATABASEURL;
    String USER;
    String PASSWORD;
    PropertiesReader(){
        Properties properties = new Properties();
        try {
            //load connection.properties file to retrieve DataBase parameters
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("Connection.properties"));
            DRIVERNAME = properties.getProperty("DRIVER_NAME");
            DATABASEURL = properties.getProperty("DATABASE_URL");
            USER = properties.getProperty("USER");
            PASSWORD = properties.getProperty("PASSWORD");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

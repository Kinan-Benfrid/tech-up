package episen.si.ing1.pds.backend.server.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class DatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class.getName());
    private static final String databaseConfigEnvVar = "DATABASE_CONFIG";
    private String databaseConfigFileLocation;
    private DatabaseCoreConfig config;

    public DatabaseConfig() throws IOException {
       databaseConfigFileLocation = System.getenv(databaseConfigEnvVar);
        logger.info("Config file = {}",databaseConfigFileLocation);
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        config = mapper.readValue(new File(databaseConfigFileLocation), DatabaseCoreConfig.class);
        logger.info("Config = {}",config.toString());
    }
    public DatabaseCoreConfig getConfig(){
        return config;
    }
}
